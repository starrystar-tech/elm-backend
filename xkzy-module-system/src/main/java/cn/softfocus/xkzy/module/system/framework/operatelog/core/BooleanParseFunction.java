package cn.softfocus.xkzy.module.system.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.softfocus.xkzy.framework.dict.core.DictFrameworkUtils;
import cn.softfocus.xkzy.module.infra.enums.DictTypeConstants;
import com.mzt.logapi.service.IParseFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 是否类型的 {@link IParseFunction} 实现类
 *
 * 三
 */
@Component
@Slf4j
public class BooleanParseFunction implements IParseFunction {

    public static final String NAME = "getBoolean";

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
        return DictFrameworkUtils.parseDictDataLabel(DictTypeConstants.BOOLEAN_STRING, value.toString());
    }

}
