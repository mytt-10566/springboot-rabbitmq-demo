package com.momo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * DLX
 *
 * @author moqinggen
 * @date 2019/06/09
 */
@Configuration
public class RabbitDLXConfig {

    @Value("${rabbitmq.test.dlx.normalExchange}")
    private String normalExchangeName;
    @Value("${rabbitmq.test.dlx.normalQueue}")
    private String normalQueueName;
    @Value("${rabbitmq.test.dlx.normalRoutingKey}")
    private String normalRoutingKey;

    @Value("${rabbitmq.test.dlx.dlxExchange}")
    private String dlxExchangeName;
    @Value("${rabbitmq.test.dlx.dlxQueue}")
    private String dlxQueueName;
    @Value("${rabbitmq.test.dlx.dlxRoutingKey}")
    private String dlxRoutingKey;

    @Bean
    public DirectExchange dlxNormalExchange() {
        Map<String, Object> args = new HashMap<>(4);
        // 设置消息过期时间
        args.put("x-message-ttl", 5000);
        // 为普通队列添加DLX
        args.put("x-dead-letter-exchange", dlxExchangeName);
        // 为这个DLX指定路由键，不指定则使用原队列的路由键
        args.put("x-dead-letter-routing-key", dlxRoutingKey);
        return new DirectExchange(normalExchangeName, true, false, args);
    }

    @Bean
    public Queue dlxNormalQueue() {
        // true 持久化
        return new Queue(normalQueueName, true);
    }

    @Bean
    Binding bindingDlxNormalQueue(Queue dlxNormalQueue, DirectExchange dlxNormalExchange) {
        return BindingBuilder.bind(dlxNormalQueue).to(dlxNormalExchange).with(normalRoutingKey);
    }


    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(dlxExchangeName);
    }

    @Bean
    public Queue dlxQueue() {
        // true 持久化
        return new Queue(dlxQueueName, true);
    }

    @Bean
    Binding bindingDlxQueue(Queue dlxQueue, DirectExchange dlxExchange) {
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with(dlxRoutingKey);
    }
}
