package cn.softfocus.xkzy.module.trade.service.order;

import cn.softfocus.xkzy.module.trade.convert.order.TradeOrderLogConvert;
import cn.softfocus.xkzy.module.trade.dal.dataobject.order.TradeOrderLogDO;
import cn.softfocus.xkzy.module.trade.dal.mysql.order.TradeOrderLogMapper;
import cn.softfocus.xkzy.module.trade.service.order.bo.TradeOrderLogCreateReqBO;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 交易下单日志 Service 实现类
 *
 * 
 * @since 2023/7/6 15:44
 */
@Service
public class TradeOrderLogServiceImpl implements TradeOrderLogService {

    @Resource
    private TradeOrderLogMapper tradeOrderLogMapper;

    @Override
    public void createOrderLog(TradeOrderLogCreateReqBO createReqBO) {
        tradeOrderLogMapper.insert(TradeOrderLogConvert.INSTANCE.convert(createReqBO));
    }

    @Override
    public List<TradeOrderLogDO> getOrderLogListByOrderId(Long orderId) {
        return tradeOrderLogMapper.selectListByOrderId(orderId);
    }

}
