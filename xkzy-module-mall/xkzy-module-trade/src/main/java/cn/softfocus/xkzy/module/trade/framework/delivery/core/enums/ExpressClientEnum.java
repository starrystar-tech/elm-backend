package cn.softfocus.xkzy.module.trade.framework.delivery.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 快递客户端枚举
 *
 * 三
 */
@Getter
@AllArgsConstructor
public enum ExpressClientEnum {

    NOT_PROVIDE("not_provide","未提供"),
    KD_NIAO("kd_niao", "快递鸟"),
    KD_100("kd_100", "快递100");

    /**
     * 快递服务商唯一编码
     */
    private final String code;
    /**
     * 快递服务商名称
     */
    private final String name;

}
