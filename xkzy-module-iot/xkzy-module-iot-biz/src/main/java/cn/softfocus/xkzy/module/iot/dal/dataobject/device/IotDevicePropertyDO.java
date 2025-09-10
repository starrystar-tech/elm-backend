package cn.softfocus.xkzy.module.iot.dal.dataobject.device;

import cn.softfocus.xkzy.module.iot.dal.redis.device.DevicePropertyRedisDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IoT 设备属性项 Redis DO
 *
 * @see cn.softfocus.xkzy.module.iot.dal.redis.RedisKeyConstants#DEVICE_PROPERTY
 * @see DevicePropertyRedisDAO
 *
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotDevicePropertyDO {

    /**
     * 属性值（最新）
     */
    private Object value;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}