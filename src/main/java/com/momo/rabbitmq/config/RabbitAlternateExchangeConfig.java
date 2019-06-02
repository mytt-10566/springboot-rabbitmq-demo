package com.momo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 备份交换器
 *
 * @author moqinggen
 * @date 2019/06/02
 */
@Configuration
public class RabbitAlternateExchangeConfig {

    @Value("${rabbitmq.test.ae.normalExchange}")
    private String normalExchangeName;
    @Value("${rabbitmq.test.ae.normalQueue}")
    private String normalQueueName;
    @Value("${rabbitmq.test.ae.normalRoutingKey}")
    private String normalRoutingKey;

    @Value("${rabbitmq.test.ae.aeExchange}")
    private String aeExchangeName;
    @Value("${rabbitmq.test.ae.aeQueue}")
    private String aeQueueName;

    @Bean
    public DirectExchange aeNormalExchange() {
        // 配置备份交换器，交换器类型为Fanout
        Map<String, Object> args = new HashMap<>(4);
        args.put("alternate-exchange", aeExchangeName);
        return new DirectExchange(normalExchangeName, true, false, args);
    }

    @Bean
    public Queue aeNormalQueue() {
        // true 持久化
        return new Queue(normalQueueName, true);
    }

    @Bean
    Binding bindingAeNormalQueue(Queue aeNormalQueue, DirectExchange aeNormalExchange) {
        return BindingBuilder.bind(aeNormalQueue).to(aeNormalExchange).with(normalRoutingKey);
    }


    @Bean
    public FanoutExchange aeExchange() {
        return new FanoutExchange(aeExchangeName);
    }

    @Bean
    public Queue aeQueue() {
        // true 持久化
        return new Queue(aeQueueName, true);
    }

    @Bean
    Binding bindingAeQueue(Queue aeQueue, FanoutExchange aeExchange) {
        return BindingBuilder.bind(aeQueue).to(aeExchange);
    }
}
