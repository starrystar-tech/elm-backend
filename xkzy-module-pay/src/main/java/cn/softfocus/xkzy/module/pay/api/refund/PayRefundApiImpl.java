package cn.softfocus.xkzy.module.pay.api.refund;

import cn.softfocus.xkzy.framework.common.util.object.BeanUtils;
import cn.softfocus.xkzy.module.pay.api.refund.dto.PayRefundCreateReqDTO;
import cn.softfocus.xkzy.module.pay.api.refund.dto.PayRefundRespDTO;
import cn.softfocus.xkzy.module.pay.dal.dataobject.refund.PayRefundDO;
import cn.softfocus.xkzy.module.pay.service.refund.PayRefundService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 退款单 API 实现类
 *
 *
 */
@Service
@Validated
public class PayRefundApiImpl implements PayRefundApi {

    @Resource
    private PayRefundService payRefundService;

    @Override
    public Long createRefund(PayRefundCreateReqDTO reqDTO) {
        return payRefundService.createRefund(reqDTO);
    }

    @Override
    public PayRefundRespDTO getRefund(Long id) {
        PayRefundDO refund = payRefundService.getRefund(id);
        return BeanUtils.toBean(refund, PayRefundRespDTO.class);
    }

}
