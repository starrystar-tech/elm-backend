package cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.strategy.dept;

import cn.softfocus.xkzy.framework.common.util.string.StrUtils;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.softfocus.xkzy.module.system.api.dept.DeptApi;
import cn.softfocus.xkzy.module.system.api.user.AdminUserApi;
import cn.softfocus.xkzy.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static cn.softfocus.xkzy.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 部门的成员 {@link BpmTaskCandidateStrategy} 实现类
 *
 * 
 */
@Component
public class BpmTaskCandidateDeptMemberStrategy implements BpmTaskCandidateStrategy {

    @Resource
    private DeptApi deptApi;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.DEPT_MEMBER;
    }

    @Override
    public void validateParam(String param) {
        Set<Long> deptIds = StrUtils.splitToLongSet(param);
        deptApi.validateDeptList(deptIds);
    }

    @Override
    public Set<Long> calculateUsers(String param) {
        Set<Long> deptIds = StrUtils.splitToLongSet(param);
        List<AdminUserRespDTO> users = adminUserApi.getUserListByDeptIds(deptIds);
        return convertSet(users, AdminUserRespDTO::getId);
    }

}