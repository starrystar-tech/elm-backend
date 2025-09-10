package cn.softfocus.xkzy.module.iot.service.rule.data;

import cn.hutool.core.collection.CollUtil;
import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.common.util.object.BeanUtils;
import cn.softfocus.xkzy.module.iot.controller.admin.rule.vo.data.sink.IotDataSinkPageReqVO;
import cn.softfocus.xkzy.module.iot.controller.admin.rule.vo.data.sink.IotDataSinkSaveReqVO;
import cn.softfocus.xkzy.module.iot.dal.dataobject.rule.IotDataSinkDO;
import cn.softfocus.xkzy.module.iot.dal.mysql.rule.IotDataSinkMapper;
import cn.softfocus.xkzy.module.iot.dal.redis.RedisKeyConstants;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.softfocus.xkzy.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.softfocus.xkzy.module.iot.enums.ErrorCodeConstants.DATA_SINK_DELETE_FAIL_USED_BY_RULE;
import static cn.softfocus.xkzy.module.iot.enums.ErrorCodeConstants.DATA_SINK_NOT_EXISTS;

/**
 * IoT 数据流转目的 Service 实现类
 *
 * 三
 */
@Service
@Validated
public class IotDataSinkServiceImpl implements IotDataSinkService {

    @Resource
    private IotDataSinkMapper dataSinkMapper;

    @Resource
    @Lazy // 延迟，避免循环依赖报错
    private IotDataRuleService dataRuleService;

    @Override
    public Long createDataSink(IotDataSinkSaveReqVO createReqVO) {
        IotDataSinkDO dataBridge = BeanUtils.toBean(createReqVO, IotDataSinkDO.class);
        dataSinkMapper.insert(dataBridge);
        return dataBridge.getId();
    }

    @Override
    public void updateDataSink(IotDataSinkSaveReqVO updateReqVO) {
        // 校验存在
        validateDataBridgeExists(updateReqVO.getId());
        // 更新
        IotDataSinkDO updateObj = BeanUtils.toBean(updateReqVO, IotDataSinkDO.class);
        dataSinkMapper.updateById(updateObj);
    }

    @Override
    public void deleteDataSink(Long id) {
        // 校验存在
        validateDataBridgeExists(id);
        // 校验是否被数据流转规则使用
        if (CollUtil.isNotEmpty(dataRuleService.getDataRuleListBySinkId(id))) {
            throw exception(DATA_SINK_DELETE_FAIL_USED_BY_RULE);
        }
        // 删除
        dataSinkMapper.deleteById(id);
    }

    private void validateDataBridgeExists(Long id) {
        if (dataSinkMapper.selectById(id) == null) {
            throw exception(DATA_SINK_NOT_EXISTS);
        }
    }

    @Override
    public IotDataSinkDO getDataSink(Long id) {
        return dataSinkMapper.selectById(id);
    }

    @Override
    @Cacheable(value = RedisKeyConstants.DATA_SINK, key = "#id")
    public IotDataSinkDO getDataSinkFromCache(Long id) {
        return dataSinkMapper.selectById(id);
    }

    @Override
    public PageResult<IotDataSinkDO> getDataSinkPage(IotDataSinkPageReqVO pageReqVO) {
        return dataSinkMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IotDataSinkDO> getDataSinkListByStatus(Integer status) {
        return dataSinkMapper.selectListByStatus(status);
    }

    @Override
    public void validateDataSinksExist(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        List<IotDataSinkDO> sinks = dataSinkMapper.selectByIds(ids);
        if (sinks.size() != ids.size()) {
            throw exception(DATA_SINK_NOT_EXISTS);
        }
    }

}
