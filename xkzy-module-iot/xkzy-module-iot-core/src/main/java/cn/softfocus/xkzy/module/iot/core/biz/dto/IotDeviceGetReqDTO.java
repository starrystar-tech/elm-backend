package cn.softfocus.xkzy.module.iot.core.biz.dto;

import lombok.Data;

/**
 * IoT 设备信息查询 Request DTO
 *
 *
 */
@Data
public class IotDeviceGetReqDTO {

    /**
     * 设备编号
     */
    private Long id;

    /**
     * 产品标识
     */
    private String productKey;
    /**
     * 设备名称
     */
    private String deviceName;

}