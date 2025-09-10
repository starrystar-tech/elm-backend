package cn.softfocus.xkzy.framework.desensitize.core.slider.handler;

import cn.softfocus.xkzy.framework.desensitize.core.slider.annotation.FixedPhoneDesensitize;

/**
 * {@link FixedPhoneDesensitize} 的脱敏处理器
 *
 * 三
 */
public class FixedPhoneDesensitization extends AbstractSliderDesensitizationHandler<FixedPhoneDesensitize> {

    @Override
    Integer getPrefixKeep(FixedPhoneDesensitize annotation) {
        return annotation.prefixKeep();
    }

    @Override
    Integer getSuffixKeep(FixedPhoneDesensitize annotation) {
        return annotation.suffixKeep();
    }

    @Override
    String getReplacer(FixedPhoneDesensitize annotation) {
        return annotation.replacer();
    }

}
