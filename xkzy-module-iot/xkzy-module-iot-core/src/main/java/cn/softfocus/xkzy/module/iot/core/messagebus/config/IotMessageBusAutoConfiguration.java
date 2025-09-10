package cn.softfocus.xkzy.module.iot.core.messagebus.config;

import cn.softfocus.xkzy.framework.mq.redis.core.RedisMQTemplate;
import cn.softfocus.xkzy.framework.mq.redis.core.job.RedisPendingMessageResendJob;
import cn.softfocus.xkzy.framework.mq.redis.core.job.RedisStreamMessageCleanupJob;
import cn.softfocus.xkzy.framework.mq.redis.core.stream.AbstractRedisStreamMessage;
import cn.softfocus.xkzy.framework.mq.redis.core.stream.AbstractRedisStreamMessageListener;
import cn.softfocus.xkzy.module.iot.core.messagebus.core.IotMessageBus;
import cn.softfocus.xkzy.module.iot.core.messagebus.core.local.IotLocalMessageBus;
import cn.softfocus.xkzy.module.iot.core.messagebus.core.redis.IotRedisMessageBus;
import cn.softfocus.xkzy.module.iot.core.messagebus.core.rocketmq.IotRocketMQMessageBus;
import cn.softfocus.xkzy.module.iot.core.mq.producer.IotDeviceMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

import static cn.softfocus.xkzy.framework.common.util.collection.CollectionUtils.convertList;

/**
 * IoT 消息总线自动配置
 *
 *
 */
@AutoConfiguration
@EnableConfigurationProperties(IotMessageBusProperties.class)
@Slf4j
public class IotMessageBusAutoConfiguration {

    @Bean
    public IotDeviceMessageProducer deviceMessageProducer(IotMessageBus messageBus) {
        return new IotDeviceMessageProducer(messageBus);
    }

    // ==================== Local 实现 ====================

    @Configuration
    @ConditionalOnProperty(prefix = "xkzy.iot.message-bus", name = "type", havingValue = "local", matchIfMissing = true)
    public static class IotLocalMessageBusConfiguration {

        @Bean
        public IotLocalMessageBus iotLocalMessageBus(ApplicationContext applicationContext) {
            log.info("[iotLocalMessageBus][创建 IoT Local 消息总线]");
            return new IotLocalMessageBus(applicationContext);
        }

    }

    // ==================== RocketMQ 实现 ====================

    @Configuration
    @ConditionalOnProperty(prefix = "xkzy.iot.message-bus", name = "type", havingValue = "rocketmq")
    @ConditionalOnClass(RocketMQTemplate.class)
    public static class IotRocketMQMessageBusConfiguration {

        @Bean
        public IotRocketMQMessageBus iotRocketMQMessageBus(RocketMQProperties rocketMQProperties,
                                                           RocketMQTemplate rocketMQTemplate) {
            log.info("[iotRocketMQMessageBus][创建 IoT RocketMQ 消息总线]");
            return new IotRocketMQMessageBus(rocketMQProperties, rocketMQTemplate);
        }

    }

    // ==================== Redis 实现 ====================

    /**
     * 特殊：由于 XkzyRedisMQConsumerAutoConfiguration 关于 Redis stream 的消费是动态注册，所以这里只能拷贝相关的逻辑！！！
     *
     * @see cn.softfocus.xkzy.framework.mq.redis.config.XkzyRedisMQConsumerAutoConfiguration
     */
    @Configuration
    @ConditionalOnProperty(prefix = "xkzy.iot.message-bus", name = "type", havingValue = "redis")
    @ConditionalOnClass(RedisTemplate.class)
    public static class IotRedisMessageBusConfiguration {

        @Bean
        public IotRedisMessageBus iotRedisMessageBus(StringRedisTemplate redisTemplate) {
            log.info("[iotRedisMessageBus][创建 IoT Redis 消息总线]");
            return new IotRedisMessageBus(redisTemplate);
        }

        /**
         * 创建 Redis Stream 重新消费的任务
         */
        @Bean
        public RedisPendingMessageResendJob iotRedisPendingMessageResendJob(IotRedisMessageBus messageBus,
                                                                            RedisMQTemplate redisTemplate,
                                                                            RedissonClient redissonClient) {
            List<AbstractRedisStreamMessageListener<?>> listeners = getListeners(messageBus);
            return new RedisPendingMessageResendJob(listeners, redisTemplate, redissonClient);
        }

        /**
         * 创建 Redis Stream 消息清理任务
         */
        @Bean
        public RedisStreamMessageCleanupJob iotRedisStreamMessageCleanupJob(IotRedisMessageBus messageBus,
                                                                            RedisMQTemplate redisTemplate,
                                                                            RedissonClient redissonClient) {
            List<AbstractRedisStreamMessageListener<?>> listeners = getListeners(messageBus);
            return new RedisStreamMessageCleanupJob(listeners, redisTemplate, redissonClient);
        }

        private List<AbstractRedisStreamMessageListener<?>> getListeners(IotRedisMessageBus messageBus) {
            return convertList(messageBus.getSubscribers(), subscriber ->
                    new AbstractRedisStreamMessageListener<>(subscriber.getTopic(), subscriber.getGroup()) {

                        @Override
                        public void onMessage(AbstractRedisStreamMessage message) {
                            throw new UnsupportedOperationException("不应该调用！！！");
                        }
                    });
        }

    }

}