package cn.softfocus.xkzy.module.bpm.service.task.trigger.http;

import cn.softfocus.xkzy.framework.common.util.json.JsonUtils;
import cn.softfocus.xkzy.module.bpm.controller.admin.definition.vo.model.simple.BpmSimpleModelNodeVO;
import cn.softfocus.xkzy.module.bpm.enums.definition.BpmHttpRequestParamTypeEnum;
import cn.softfocus.xkzy.module.bpm.enums.definition.BpmTriggerTypeEnum;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.util.BpmHttpRequestUtils;
import cn.softfocus.xkzy.module.bpm.service.task.BpmProcessInstanceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * BPM HTTP 回调触发器
 *
 * 三
 */
@Component
@Slf4j
public class BpmHttpCallbackTrigger extends BpmAbstractHttpRequestTrigger {

    @Resource
    private BpmProcessInstanceService processInstanceService;

    @Override
    public BpmTriggerTypeEnum getType() {
        return BpmTriggerTypeEnum.HTTP_CALLBACK;
    }

    @Override
    public void execute(String processInstanceId, String param) {
        // 1. 解析 http 请求配置
        BpmSimpleModelNodeVO.TriggerSetting.HttpRequestTriggerSetting setting = JsonUtils.parseObject(param,
                BpmSimpleModelNodeVO.TriggerSetting.HttpRequestTriggerSetting.class);
        if (setting == null) {
            log.error("[execute][流程({}) HTTP 回调触发器配置为空]", processInstanceId);
            return;
        }

        // 2. 发起请求
        ProcessInstance processInstance = processInstanceService.getProcessInstance(processInstanceId);
        setting.getBody().add(new BpmSimpleModelNodeVO.HttpRequestParam()
                .setKey("taskDefineKey") // 重要：回调请求 taskDefineKey 需要传给被调用方，用于回调执行
                .setType(BpmHttpRequestParamTypeEnum.FIXED_VALUE.getType()).setValue(setting.getCallbackTaskDefineKey()));
        BpmHttpRequestUtils.executeBpmHttpRequest(processInstance,
                setting.getUrl(), setting.getHeader(), setting.getBody(), false, null);
    }

}
