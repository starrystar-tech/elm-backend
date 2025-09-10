package cn.softfocus.xkzy.module.iot.dal.mysql.rule;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.iot.controller.admin.rule.vo.scene.IotSceneRulePageReqVO;
import cn.softfocus.xkzy.module.iot.dal.dataobject.rule.IotSceneRuleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * IoT 场景联动 Mapper
 *
 * 三
 */
@Mapper
public interface IotSceneRuleMapper extends BaseMapperX<IotSceneRuleDO> {

    default PageResult<IotSceneRuleDO> selectPage(IotSceneRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IotSceneRuleDO>()
                .likeIfPresent(IotSceneRuleDO::getName, reqVO.getName())
                .likeIfPresent(IotSceneRuleDO::getDescription, reqVO.getDescription())
                .eqIfPresent(IotSceneRuleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(IotSceneRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IotSceneRuleDO::getId));
    }

    default List<IotSceneRuleDO> selectListByStatus(Integer status) {
        return selectList(IotSceneRuleDO::getStatus, status);
    }

}
