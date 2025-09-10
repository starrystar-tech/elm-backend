package cn.softfocus.xkzy.module.bpm.dal.mysql.definition;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.bpm.controller.admin.definition.vo.listener.BpmProcessListenerPageReqVO;
import cn.softfocus.xkzy.module.bpm.dal.dataobject.definition.BpmProcessListenerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BPM 流程监听器 Mapper
 *
 *
 */
@Mapper
public interface BpmProcessListenerMapper extends BaseMapperX<BpmProcessListenerDO> {

    default PageResult<BpmProcessListenerDO> selectPage(BpmProcessListenerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BpmProcessListenerDO>()
                .likeIfPresent(BpmProcessListenerDO::getName, reqVO.getName())
                .eqIfPresent(BpmProcessListenerDO::getType, reqVO.getType())
                .eqIfPresent(BpmProcessListenerDO::getEvent, reqVO.getEvent())
                .eqIfPresent(BpmProcessListenerDO::getStatus, reqVO.getStatus())
                .orderByDesc(BpmProcessListenerDO::getId));
    }

}