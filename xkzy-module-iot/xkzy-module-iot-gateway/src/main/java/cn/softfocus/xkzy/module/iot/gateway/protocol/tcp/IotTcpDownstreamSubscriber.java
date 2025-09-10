package cn.softfocus.xkzy.module.iot.gateway.protocol.tcp;

import cn.softfocus.xkzy.module.iot.core.messagebus.core.IotMessageBus;
import cn.softfocus.xkzy.module.iot.core.messagebus.core.IotMessageSubscriber;
import cn.softfocus.xkzy.module.iot.core.mq.message.IotDeviceMessage;
import cn.softfocus.xkzy.module.iot.core.util.IotDeviceMessageUtils;
import cn.softfocus.xkzy.module.iot.gateway.protocol.tcp.manager.IotTcpConnectionManager;
import cn.softfocus.xkzy.module.iot.gateway.protocol.tcp.router.IotTcpDownstreamHandler;
import cn.softfocus.xkzy.module.iot.gateway.service.device.IotDeviceService;
import cn.softfocus.xkzy.module.iot.gateway.service.device.message.IotDeviceMessageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * IoT 网关 TCP 下游订阅者：接收下行给设备的消息
 *
 *
 */
@Slf4j
@RequiredArgsConstructor
public class IotTcpDownstreamSubscriber implements IotMessageSubscriber<IotDeviceMessage> {

    private final IotTcpUpstreamProtocol protocol;

    private final IotDeviceMessageService messageService;

    private final IotDeviceService deviceService;

    private final IotTcpConnectionManager connectionManager;

    private final IotMessageBus messageBus;

    private IotTcpDownstreamHandler downstreamHandler;

    @PostConstruct
    public void init() {
        // 初始化下游处理器
        this.downstreamHandler = new IotTcpDownstreamHandler(messageService, deviceService, connectionManager);

        messageBus.register(this);
        log.info("[init][TCP 下游订阅者初始化完成，服务器 ID: {}，Topic: {}]",
                protocol.getServerId(), getTopic());
    }

    @Override
    public String getTopic() {
        return IotDeviceMessageUtils.buildMessageBusGatewayDeviceMessageTopic(protocol.getServerId());
    }

    @Override
    public String getGroup() {
        // 保证点对点消费，需要保证独立的 Group，所以使用 Topic 作为 Group
        return getTopic();
    }

    @Override
    public void onMessage(IotDeviceMessage message) {
        try {
            downstreamHandler.handle(message);
        } catch (Exception e) {
            log.error("[onMessage][处理下行消息失败，设备 ID: {}，方法: {}，消息 ID: {}]",
                    message.getDeviceId(), message.getMethod(), message.getId(), e);
        }
    }

}