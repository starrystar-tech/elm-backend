package cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.strategy.user;

import cn.softfocus.xkzy.framework.common.util.string.StrUtils;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.softfocus.xkzy.module.system.api.permission.PermissionApi;
import cn.softfocus.xkzy.module.system.api.permission.RoleApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 角色 {@link BpmTaskCandidateStrategy} 实现类
 *
 * 
 */
@Component
public class BpmTaskCandidateRoleStrategy implements BpmTaskCandidateStrategy {

    @Resource
    private RoleApi roleApi;
    @Resource
    private PermissionApi permissionApi;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.ROLE;
    }

    @Override
    public void validateParam(String param) {
        Set<Long> roleIds = StrUtils.splitToLongSet(param);
        roleApi.validRoleList(roleIds);
    }

    @Override
    public Set<Long> calculateUsers(String param) {
        Set<Long> roleIds = StrUtils.splitToLongSet(param);
        return permissionApi.getUserRoleIdListByRoleIds(roleIds);
    }

}