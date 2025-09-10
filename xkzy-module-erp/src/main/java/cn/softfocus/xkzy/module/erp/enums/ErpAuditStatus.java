package cn.softfocus.xkzy.module.erp.enums;

import cn.softfocus.xkzy.framework.common.core.ArrayValuable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * ERP 审核状态枚举
 */
@RequiredArgsConstructor
@Getter
public enum ErpAuditStatus implements ArrayValuable<Integer> {

    PROCESS(10, "未审核"), // 审核中
    APPROVE(20, "已审核"); // 审核通过

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(ErpAuditStatus::getStatus).toArray(Integer[]::new);

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }

}
