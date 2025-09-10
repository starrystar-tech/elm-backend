package cn.softfocus.xkzy.module.iot.dal.dataobject.rule.config;

import lombok.Data;

/**
 * IoT MQTT 配置 {@link IotAbstractDataSinkConfig} 实现类
 *
 * 三
 */
@Data
public class IotDataSinkMqttConfig extends IotAbstractDataSinkConfig {

    /**
     * MQTT 服务器地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 客户端编号
     */
    private String clientId;
    /**
     * 主题
     */
    private String topic;

}