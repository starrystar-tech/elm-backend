package cn.softfocus.xkzy.module.crm.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.softfocus.xkzy.framework.dict.core.DictFrameworkUtils;
import com.mzt.logapi.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static cn.softfocus.xkzy.module.crm.enums.DictTypeConstants.CRM_CUSTOMER_LEVEL;

/**
 * 客户等级的 {@link IParseFunction} 实现类
 *
 * 三
 */
@Component
@Slf4j
public class CrmCustomerLevelParseFunction implements IParseFunction {

    public static final String NAME = "getCustomerLevel";

    @Override
    public boolean executeBefore() {
        return true; // 先转换值后对比
    }

    @Override
    public String functionName() {
        return NAME;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }
        return DictFrameworkUtils.parseDictDataLabel(CRM_CUSTOMER_LEVEL, value.toString());
    }

}
