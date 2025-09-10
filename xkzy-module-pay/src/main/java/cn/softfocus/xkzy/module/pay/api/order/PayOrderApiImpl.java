package cn.softfocus.xkzy.module.pay.api.order;

import cn.softfocus.xkzy.module.pay.api.order.dto.PayOrderCreateReqDTO;
import cn.softfocus.xkzy.module.pay.api.order.dto.PayOrderRespDTO;
import cn.softfocus.xkzy.module.pay.convert.order.PayOrderConvert;
import cn.softfocus.xkzy.module.pay.dal.dataobject.order.PayOrderDO;
import cn.softfocus.xkzy.module.pay.service.order.PayOrderService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 支付单 API 实现类
 *
 *
 */
@Service
public class PayOrderApiImpl implements PayOrderApi {

    @Resource
    private PayOrderService payOrderService;

    @Override
    public Long createOrder(PayOrderCreateReqDTO reqDTO) {
        return payOrderService.createOrder(reqDTO);
    }

    @Override
    public PayOrderRespDTO getOrder(Long id) {
        PayOrderDO order = payOrderService.getOrder(id);
        return PayOrderConvert.INSTANCE.convert2(order);
    }

    @Override
    public void updatePayOrderPrice(Long id, Integer payPrice) {
        payOrderService.updatePayOrderPrice(id, payPrice);
    }

}
