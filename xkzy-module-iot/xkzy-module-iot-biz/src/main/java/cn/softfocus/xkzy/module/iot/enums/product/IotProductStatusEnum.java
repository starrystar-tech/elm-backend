package cn.softfocus.xkzy.module.iot.enums.product;

import cn.softfocus.xkzy.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * IoT 产品的状态枚举类
 *
 * 
 */
@AllArgsConstructor
@Getter
public enum IotProductStatusEnum implements ArrayValuable<Integer> {

    UNPUBLISHED(0, "开发中"),
    PUBLISHED(1, "已发布");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(IotProductStatusEnum::getStatus).toArray(Integer[]::new);

    /**
     * 类型
     */
    private final Integer status;
    /**
     * 描述
     */
    private final String description;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
