package cn.softfocus.xkzy.module.trade.service.message;

import cn.softfocus.xkzy.module.system.api.notify.NotifyMessageSendApi;
import cn.softfocus.xkzy.module.system.api.notify.dto.NotifySendSingleToUserReqDTO;
import cn.softfocus.xkzy.module.trade.enums.MessageTemplateConstants;
import cn.softfocus.xkzy.module.trade.service.message.bo.TradeOrderMessageWhenDeliveryOrderReqBO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Trade 消息 service 实现类
 *
 * 三
 */
@Service
@Validated
public class TradeMessageServiceImpl implements TradeMessageService {

    @Resource
    private NotifyMessageSendApi notifyMessageSendApi;

    @Override
    public void sendMessageWhenDeliveryOrder(TradeOrderMessageWhenDeliveryOrderReqBO reqBO) {
        if (true) {
            return;
        }
        // 1、构造消息
        Map<String, Object> msgMap = new HashMap<>(2);
        msgMap.put("orderId", reqBO.getOrderId());
        msgMap.put("deliveryMessage", reqBO.getMessage());
        // 2、发送站内信
        notifyMessageSendApi.sendSingleMessageToMember(
                new NotifySendSingleToUserReqDTO()
                        .setUserId(reqBO.getUserId())
                        .setTemplateCode(MessageTemplateConstants.SMS_ORDER_DELIVERY)
                        .setTemplateParams(msgMap));
    }

}
