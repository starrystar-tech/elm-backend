package cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.strategy.dept;

import cn.softfocus.xkzy.framework.common.util.string.StrUtils;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.softfocus.xkzy.module.system.api.dept.DeptApi;
import cn.softfocus.xkzy.module.system.api.dept.dto.DeptRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static cn.softfocus.xkzy.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 部门的负责人 {@link BpmTaskCandidateStrategy} 实现类
 *
 * 
 */
@Component
public class BpmTaskCandidateDeptLeaderStrategy implements BpmTaskCandidateStrategy {

    @Resource
    private DeptApi deptApi;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.DEPT_LEADER;
    }

    @Override
    public void validateParam(String param) {
        Set<Long> deptIds = StrUtils.splitToLongSet(param);
        deptApi.validateDeptList(deptIds);
    }

    @Override
    public Set<Long> calculateUsers(String param) {
        Set<Long> deptIds = StrUtils.splitToLongSet(param);
        List<DeptRespDTO> depts = deptApi.getDeptList(deptIds);
        return convertSet(depts, DeptRespDTO::getLeaderUserId);
    }

}