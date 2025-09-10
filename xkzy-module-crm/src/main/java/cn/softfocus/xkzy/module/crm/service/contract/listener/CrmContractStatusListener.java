package cn.softfocus.xkzy.module.crm.service.contract.listener;

import cn.softfocus.xkzy.module.bpm.api.event.BpmProcessInstanceStatusEvent;
import cn.softfocus.xkzy.module.bpm.api.event.BpmProcessInstanceStatusEventListener;
import cn.softfocus.xkzy.module.crm.service.contract.CrmContractService;
import cn.softfocus.xkzy.module.crm.service.contract.CrmContractServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 合同审批的结果的监听器实现类
 *
 * 三
 */
@Component
public class CrmContractStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private CrmContractService contractService;

    @Override
    public String getProcessDefinitionKey() {
        return CrmContractServiceImpl.BPM_PROCESS_DEFINITION_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        contractService.updateContractAuditStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
