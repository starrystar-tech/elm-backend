package cn.softfocus.xkzy.module.promotion.job.combination;

import cn.hutool.core.util.StrUtil;
import cn.softfocus.xkzy.framework.common.core.KeyValue;
import cn.softfocus.xkzy.framework.quartz.core.handler.JobHandler;
import cn.softfocus.xkzy.framework.tenant.core.job.TenantJob;
import cn.softfocus.xkzy.module.promotion.service.combination.CombinationRecordService;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 拼团过期 Job
 *
 * 三
 */
@Component
public class CombinationRecordExpireJob implements JobHandler {

    @Resource
    private CombinationRecordService combinationRecordService;

    @Override
    @TenantJob
    public String execute(String param) {
        KeyValue<Integer, Integer> keyValue = combinationRecordService.expireCombinationRecord();
        return StrUtil.format("过期拼团 {} 个, 虚拟成团 {} 个", keyValue.getKey(), keyValue.getValue());
    }

}
