package cn.softfocus.xkzy.module.pay.enums.wallet;

import cn.softfocus.xkzy.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 钱包交易业务分类
 *
 * 三
 */
@AllArgsConstructor
@Getter
public enum PayWalletBizTypeEnum implements ArrayValuable<Integer> {

    RECHARGE(1, "充值"),
    RECHARGE_REFUND(2, "充值退款"),
    PAYMENT(3, "支付"),
    PAYMENT_REFUND(4, "支付退款"),
    UPDATE_BALANCE(5, "更新余额"),
    TRANSFER(6, "转账");

    /**
     * 业务分类
     */
    private final Integer type;
    /**
     * 说明
     */
    private final String description;

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(PayWalletBizTypeEnum::getType).toArray(Integer[]::new);

    @Override
    public Integer[] array() {
         return ARRAYS;
    }

    public static PayWalletBizTypeEnum valueOf(Integer type) {
        return Arrays.stream(values()).filter(item -> item.getType().equals(type)).findFirst().orElse(null);
    }

}
