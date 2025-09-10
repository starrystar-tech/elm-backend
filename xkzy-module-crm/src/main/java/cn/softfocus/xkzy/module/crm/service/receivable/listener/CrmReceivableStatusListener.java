package cn.softfocus.xkzy.module.crm.service.receivable.listener;

import cn.softfocus.xkzy.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.softfocus.xkzy.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.softfocus.xkzy.module.crm.service.receivable.CrmReceivableService;
import cn.softfocus.xkzy.module.crm.service.receivable.CrmReceivableServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 回款审批的结果的监听器实现类
 *
 * 三
 */
@Component
public class CrmReceivableStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private CrmReceivableService receivableService;

    @Override
    public String getProcessDefinitionKey() {
        return CrmReceivableServiceImpl.BPM_PROCESS_DEFINITION_KEY;
    }

    @Override
    public void onEvent(BpmProcessInstanceStatusEvent event) {
        receivableService.updateReceivableAuditStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
