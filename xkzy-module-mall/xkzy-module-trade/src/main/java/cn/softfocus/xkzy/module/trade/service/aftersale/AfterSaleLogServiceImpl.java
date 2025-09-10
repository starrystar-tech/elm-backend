package cn.softfocus.xkzy.module.trade.service.aftersale;

import cn.softfocus.xkzy.module.trade.convert.aftersale.AfterSaleLogConvert;
import cn.softfocus.xkzy.module.trade.dal.dataobject.aftersale.AfterSaleLogDO;
import cn.softfocus.xkzy.module.trade.dal.mysql.aftersale.AfterSaleLogMapper;
import cn.softfocus.xkzy.module.trade.service.aftersale.bo.AfterSaleLogCreateReqBO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 交易售后日志 Service 实现类
 *
 *
 */
@Service
@Validated
public class AfterSaleLogServiceImpl implements AfterSaleLogService {

    @Resource
    private AfterSaleLogMapper afterSaleLogMapper;

    @Override
    public void createAfterSaleLog(AfterSaleLogCreateReqBO createReqBO) {
        AfterSaleLogDO afterSaleLog = AfterSaleLogConvert.INSTANCE.convert(createReqBO);
        afterSaleLogMapper.insert(afterSaleLog);
    }

    @Override
    public List<AfterSaleLogDO> getAfterSaleLogList(Long afterSaleId) {
        return afterSaleLogMapper.selectListByAfterSaleId(afterSaleId);
    }

}
