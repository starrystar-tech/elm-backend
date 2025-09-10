package cn.softfocus.xkzy.module.trade.service.order.handler;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.softfocus.xkzy.framework.common.enums.UserTypeEnum;
import cn.softfocus.xkzy.module.pay.api.order.PayOrderApi;
import cn.softfocus.xkzy.module.pay.api.order.dto.PayOrderRespDTO;
import cn.softfocus.xkzy.module.pay.enums.PayChannelEnum;
import cn.softfocus.xkzy.module.system.api.social.SocialClientApi;
import cn.softfocus.xkzy.module.system.api.social.dto.SocialWxaOrderNotifyConfirmReceiveReqDTO;
import cn.softfocus.xkzy.module.system.api.social.dto.SocialWxaOrderUploadShippingInfoReqDTO;
import cn.softfocus.xkzy.module.trade.dal.dataobject.order.TradeOrderDO;
import cn.softfocus.xkzy.module.trade.enums.delivery.DeliveryTypeEnum;
import cn.softfocus.xkzy.module.trade.service.delivery.DeliveryExpressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 同步订单状态到微信小程序的 {@link TradeOrderHandler} 实现类
 *
 * 背景：电商类目的微信小程序需要上传发货信息，不然微信支付会被封 = =！
 * 注意：微信小程序开发环境下的订单不能用来发货。只有小程序正式版才会有发货，所以体验版无法调通，提示订单不存在。注意别踩坑。
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "xkzy.trade.order", value = "status-sync-to-wxa-enable")
public class TradeStatusSyncToWxaOrderHandler implements TradeOrderHandler {

    @Resource
    private PayOrderApi payOrderApi;
    @Resource
    private SocialClientApi socialClientApi;

    @Resource
    private DeliveryExpressService expressService;

    @Override
    public void afterDeliveryOrder(TradeOrderDO order) {
        // 注意：只有微信小程序支付的订单，才需要同步
        if (ObjUtil.notEqual(order.getPayChannelCode(), PayChannelEnum.WX_LITE.getCode())) {
            return;
        }

        // 上传订单物流信息到微信小程序
        uploadWxaOrderShippingInfo(order);
    }

    @Override
    public void afterReceiveOrder(TradeOrderDO order) {
        // 注意：只有微信小程序支付的订单，才需要同步
        if (ObjUtil.notEqual(order.getPayChannelCode(), PayChannelEnum.WX_LITE.getCode())) {
            return;
        }
        PayOrderRespDTO payOrder = payOrderApi.getOrder(order.getPayOrderId());
        SocialWxaOrderNotifyConfirmReceiveReqDTO reqDTO = new SocialWxaOrderNotifyConfirmReceiveReqDTO()
                .setTransactionId(payOrder.getChannelOrderNo())
                .setReceivedTime(order.getReceiveTime());
        try {
            socialClientApi.notifyWxaOrderConfirmReceive(UserTypeEnum.MEMBER.getValue(), reqDTO);
        } catch (Exception ex) {
            log.error("[afterReceiveOrder][订单({}) 通知订单收货到微信小程序失败]", order, ex);
        }

        // 如果是门店自提订单，上传订单物流信息到微信小程序
        // 原因是，门店自提订单没有 “afterDeliveryOrder” 阶段。可见 https://t.zsxq.com/KWD3u 反馈
        if (DeliveryTypeEnum.PICK_UP.getType().equals(order.getDeliveryType())) {
            uploadWxaOrderShippingInfo(order);
        }
    }

    /**
     * 上传订单物流信息到微信小程序
     *
     * @param order 订单
     */
    private void uploadWxaOrderShippingInfo(TradeOrderDO order) {
        PayOrderRespDTO payOrder = payOrderApi.getOrder(order.getPayOrderId());
        SocialWxaOrderUploadShippingInfoReqDTO reqDTO = new SocialWxaOrderUploadShippingInfoReqDTO()
                .setTransactionId(payOrder.getChannelOrderNo())
                .setOpenid(payOrder.getChannelUserId())
                .setItemDesc(payOrder.getSubject())
                .setReceiverContact(order.getReceiverMobile());
        if (DeliveryTypeEnum.EXPRESS.getType().equals(order.getDeliveryType()) && StrUtil.isNotEmpty(order.getLogisticsNo())) {
            reqDTO.setLogisticsType(SocialWxaOrderUploadShippingInfoReqDTO.LOGISTICS_TYPE_EXPRESS)
                    .setExpressCompany(expressService.getDeliveryExpress(order.getLogisticsId()).getCode())
                    .setLogisticsNo(order.getLogisticsNo());
        } else if (DeliveryTypeEnum.PICK_UP.getType().equals(order.getDeliveryType())) {
            reqDTO.setLogisticsType(SocialWxaOrderUploadShippingInfoReqDTO.LOGISTICS_TYPE_PICK_UP);
        } else {
            reqDTO.setLogisticsType(SocialWxaOrderUploadShippingInfoReqDTO.LOGISTICS_TYPE_VIRTUAL);
        }
        try {
            socialClientApi.uploadWxaOrderShippingInfo(UserTypeEnum.MEMBER.getValue(), reqDTO);
        } catch (Exception ex) {
            log.error("[afterDeliveryOrder][订单({}) 上传订单物流信息到微信小程序失败]", order, ex);
        }
    }
}
