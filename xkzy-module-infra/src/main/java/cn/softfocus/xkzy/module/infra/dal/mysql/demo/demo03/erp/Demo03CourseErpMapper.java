package cn.softfocus.xkzy.module.infra.dal.mysql.demo.demo03.erp;

import cn.softfocus.xkzy.framework.common.pojo.PageParam;
import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.infra.dal.dataobject.demo.demo03.Demo03CourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生课程 Mapper
 *
 *
 */
@Mapper
public interface Demo03CourseErpMapper extends BaseMapperX<Demo03CourseDO> {

    default PageResult<Demo03CourseDO> selectPage(PageParam reqVO, Long studentId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Demo03CourseDO>()
                .eq(Demo03CourseDO::getStudentId, studentId)
                .orderByDesc(Demo03CourseDO::getId));
    }

    default int deleteByStudentId(Long studentId) {
        return delete(Demo03CourseDO::getStudentId, studentId);
    }

    default int deleteByStudentIds(List<Long> studentIds) {
        return deleteBatch(Demo03CourseDO::getStudentId, studentIds);
    }

}
