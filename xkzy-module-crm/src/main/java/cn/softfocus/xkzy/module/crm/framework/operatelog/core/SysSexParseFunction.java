package cn.softfocus.xkzy.module.crm.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.softfocus.xkzy.framework.dict.core.DictFrameworkUtils;
import cn.softfocus.xkzy.module.system.enums.DictTypeConstants;
import com.mzt.logapi.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 行业的 {@link IParseFunction} 实现类
 *
 * 三
 */
@Component
@Slf4j
public class SysSexParseFunction implements IParseFunction {

    public static final String NAME = "getSex";

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
        return DictFrameworkUtils.parseDictDataLabel(DictTypeConstants.USER_SEX, value.toString());
    }

}
