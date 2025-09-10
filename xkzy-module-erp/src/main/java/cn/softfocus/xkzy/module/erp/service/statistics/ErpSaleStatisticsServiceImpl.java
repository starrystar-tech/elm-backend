package cn.softfocus.xkzy.module.erp.service.statistics;

import cn.softfocus.xkzy.module.erp.dal.mysql.statistics.ErpSaleStatisticsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ERP 销售统计 Service 实现类
 *
 *
 */
@Service
public class ErpSaleStatisticsServiceImpl implements ErpSaleStatisticsService {

    @Resource
    private ErpSaleStatisticsMapper saleStatisticsMapper;

    @Override
    public BigDecimal getSalePrice(LocalDateTime beginTime, LocalDateTime endTime) {
        return saleStatisticsMapper.getSalePrice(beginTime, endTime);
    }

}
