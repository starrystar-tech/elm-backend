package cn.softfocus.xkzy.module.bpm.service.message;

import cn.softfocus.xkzy.module.bpm.service.message.dto.BpmMessageSendWhenProcessInstanceApproveReqDTO;
import cn.softfocus.xkzy.module.bpm.service.message.dto.BpmMessageSendWhenProcessInstanceRejectReqDTO;
import cn.softfocus.xkzy.module.bpm.service.message.dto.BpmMessageSendWhenTaskCreatedReqDTO;
import cn.softfocus.xkzy.module.bpm.service.message.dto.BpmMessageSendWhenTaskTimeoutReqDTO;
import jakarta.validation.Valid;

/**
 * BPM 消息 Service 接口
 */
public interface BpmMessageService {

    /**
     * 发送流程实例被通过的消息
     *
     * @param reqDTO 发送信息
     */
    void sendMessageWhenProcessInstanceApprove(@Valid BpmMessageSendWhenProcessInstanceApproveReqDTO reqDTO);

    /**
     * 发送流程实例被不通过的消息
     *
     * @param reqDTO 发送信息
     */
    void sendMessageWhenProcessInstanceReject(@Valid BpmMessageSendWhenProcessInstanceRejectReqDTO reqDTO);

    /**
     * 发送任务被分配的消息
     *
     * @param reqDTO 发送信息
     */
    void sendMessageWhenTaskAssigned(@Valid BpmMessageSendWhenTaskCreatedReqDTO reqDTO);

    /**
     * 发送任务审批超时的消息
     *
     * @param reqDTO 发送信息
     */
    void sendMessageWhenTaskTimeout(@Valid BpmMessageSendWhenTaskTimeoutReqDTO reqDTO);

}
