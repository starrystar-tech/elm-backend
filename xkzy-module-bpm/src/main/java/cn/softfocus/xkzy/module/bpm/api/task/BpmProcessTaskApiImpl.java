package cn.softfocus.xkzy.module.bpm.api.task;

import cn.softfocus.xkzy.module.bpm.service.task.BpmTaskService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 流程任务 Api 实现类
 *
 * 三
 */
@Service
@Validated
public class BpmProcessTaskApiImpl implements BpmProcessTaskApi {

    @Resource
    private BpmTaskService bpmTaskService;

    @Override
    public void triggerTask(String processInstanceId, String taskDefineKey) {
        bpmTaskService.triggerTask(processInstanceId, taskDefineKey);
    }

}
