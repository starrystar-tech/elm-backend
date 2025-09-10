package cn.softfocus.xkzy.module.bpm.service.task.listener;

import cn.softfocus.xkzy.module.bpm.controller.admin.definition.vo.model.simple.BpmSimpleModelNodeVO;
import cn.softfocus.xkzy.module.bpm.enums.definition.BpmHttpRequestParamTypeEnum;
import cn.softfocus.xkzy.module.bpm.framework.flowable.core.util.BpmHttpRequestUtils;
import cn.softfocus.xkzy.module.bpm.service.task.BpmProcessInstanceService;
import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.impl.el.FixedValue;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static cn.softfocus.xkzy.module.bpm.framework.flowable.core.util.BpmnModelUtils.parseListenerConfig;

/**
 * BPM 用户任务通用监听器
 */
@Component
@Slf4j
@Scope("prototype")
public class BpmUserTaskListener implements TaskListener {

    public static final String DELEGATE_EXPRESSION = "${bpmUserTaskListener}";

    @Resource
    private BpmProcessInstanceService processInstanceService;

    @Setter
    private FixedValue listenerConfig;

    @Override
    public void notify(DelegateTask delegateTask) {
        // 1. 获取所需基础信息
        ProcessInstance processInstance = processInstanceService.getProcessInstance(delegateTask.getProcessInstanceId());
        BpmSimpleModelNodeVO.ListenerHandler listenerHandler = parseListenerConfig(listenerConfig);

        // 2. 发起请求
        listenerHandler.getBody().add(new BpmSimpleModelNodeVO.HttpRequestParam().setKey("processInstanceId")
                .setType(BpmHttpRequestParamTypeEnum.FIXED_VALUE.getType()).setValue(delegateTask.getProcessInstanceId()));
        listenerHandler.getBody().add(new BpmSimpleModelNodeVO.HttpRequestParam().setKey("assignee")
                .setType(BpmHttpRequestParamTypeEnum.FIXED_VALUE.getType()).setValue(delegateTask.getAssignee()));
        listenerHandler.getBody().add(new BpmSimpleModelNodeVO.HttpRequestParam().setKey("taskDefinitionKey")
                .setType(BpmHttpRequestParamTypeEnum.FIXED_VALUE.getType()).setValue(delegateTask.getTaskDefinitionKey()));
        listenerHandler.getBody().add(new BpmSimpleModelNodeVO.HttpRequestParam().setKey("taskId")
                .setType(BpmHttpRequestParamTypeEnum.FIXED_VALUE.getType()).setValue(delegateTask.getId()));
        BpmHttpRequestUtils.executeBpmHttpRequest(processInstance,
                listenerHandler.getPath(), listenerHandler.getHeader(), listenerHandler.getBody(), false, null);
    }
}