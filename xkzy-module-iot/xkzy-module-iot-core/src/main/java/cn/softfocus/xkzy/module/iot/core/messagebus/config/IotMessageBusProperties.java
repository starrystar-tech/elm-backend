package cn.softfocus.xkzy.module.iot.core.messagebus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * IoT 消息总线配置属性
 *
 *
 */
@ConfigurationProperties("xkzy.iot.message-bus")
@Data
@Validated
public class IotMessageBusProperties {

    /**
     * 消息总线类型
     *
     * 可选值：local、redis、rocketmq、rabbitmq
     */
    @NotNull(message = "IoT 消息总线类型不能为空")
    private String type = "local";

}