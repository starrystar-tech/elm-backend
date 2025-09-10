package cn.softfocus.xkzy.module.iot.enums.product;

import cn.softfocus.xkzy.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * IoT 联网方式枚举类
 *
 * 
 */
@AllArgsConstructor
@Getter
public enum IotNetTypeEnum implements ArrayValuable<Integer> {

    WIFI(0, "Wi-Fi"),
    CELLULAR(1, "Cellular"),
    ETHERNET(2, "Ethernet"),
    OTHER(3, "其他");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(IotNetTypeEnum::getType).toArray(Integer[]::new);

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 描述
     */
    private final String description;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
