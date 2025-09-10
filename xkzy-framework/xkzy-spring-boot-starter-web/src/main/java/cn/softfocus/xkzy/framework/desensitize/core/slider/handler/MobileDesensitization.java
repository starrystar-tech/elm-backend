package cn.softfocus.xkzy.framework.desensitize.core.slider.handler;

import cn.softfocus.xkzy.framework.desensitize.core.slider.annotation.MobileDesensitize;

/**
 * {@link MobileDesensitize} 的脱敏处理器
 *
 * 三
 */
public class MobileDesensitization extends AbstractSliderDesensitizationHandler<MobileDesensitize> {

    @Override
    Integer getPrefixKeep(MobileDesensitize annotation) {
        return annotation.prefixKeep();
    }

    @Override
    Integer getSuffixKeep(MobileDesensitize annotation) {
        return annotation.suffixKeep();
    }

    @Override
    String getReplacer(MobileDesensitize annotation) {
        return annotation.replacer();
    }

}
