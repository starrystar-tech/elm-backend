package cn.softfocus.xkzy.module.bpm.dal.mysql.definition;


import cn.softfocus.xkzy.module.bpm.controller.admin.definition.vo.form.BpmFormPageReqVO;
import cn.softfocus.xkzy.module.bpm.dal.dataobject.definition.BpmFormDO;
import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.QueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 动态表单 Mapper
 */
@Mapper
public interface BpmFormMapper extends BaseMapperX<BpmFormDO> {

    default PageResult<BpmFormDO> selectPage(BpmFormPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<BpmFormDO>()
                .likeIfPresent("name", reqVO.getName())
                .orderByDesc("id"));
    }

}
