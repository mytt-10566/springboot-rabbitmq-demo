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
 * TTL
 *
 * @author moqinggen
 * @date 2019/06/04
 */
@Configuration
public class RabbitTTLConfig {

    @Value("${rabbitmq.test.ttl.msgQueueTtlExchange}")
    private String msgQueueTtlExchange;
    @Value("${rabbitmq.test.ttl.msgQueue}")
    private String msgQueue;
    @Value("${rabbitmq.test.ttl.msgQueueRoutingKey}")
    private String msgQueueRoutingKey;

    @Value("${rabbitmq.test.ttl.singleMsgTtlExchange}")
    private String singleMsgTtlExchange;
    @Value("${rabbitmq.test.ttl.singleMsgQueue}")
    private String singleMsgQueue;
    @Value("${rabbitmq.test.ttl.singleMsgRoutingKey}")
    private String singleMsgRoutingKey;


    @Value("${rabbitmq.test.ttl.queueTtlExchange}")
    private String queueTtlExchange;
    @Value("${rabbitmq.test.ttl.queue}")
    private String queue;
    @Value("${rabbitmq.test.ttl.queueRoutingKey}")
    private String queueRoutingKey;


    @Bean
    public DirectExchange msgQueueTtlExchange() {
        return new DirectExchange(msgQueueTtlExchange);
    }

    @Bean
    public Queue msgQueue() {
        // 设置队列消息过期时间
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 5000);
        return new Queue(msgQueue, true, false, false, arguments);
    }

    @Bean
    Binding bindingMandatoryQueue(Queue msgQueue, DirectExchange msgQueueTtlExchange) {
        return BindingBuilder.bind(msgQueue).to(msgQueueTtlExchange).with(msgQueueRoutingKey);
    }


    @Bean
    public DirectExchange singleMsgTtlExchange() {
        return new DirectExchange(singleMsgTtlExchange);
    }

    @Bean
    public Queue singleMsgQueue() {
        return new Queue(singleMsgQueue, true);
    }

    @Bean
    Binding bindingSingleMsgRoutingKey(Queue singleMsgQueue, DirectExchange singleMsgTtlExchange) {
        return BindingBuilder.bind(singleMsgQueue).to(singleMsgTtlExchange).with(singleMsgRoutingKey);
    }

    @Bean
    public DirectExchange queueTtlExchange() {
        return new DirectExchange(queueTtlExchange);
    }

    @Bean
    public Queue queue() {
        // 设置队列过期时间
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-expires", 5000);
        return new Queue(queue, true, false, false, arguments);
    }

    @Bean
    Binding bindingQueue(Queue queue, DirectExchange queueTtlExchange) {
        return BindingBuilder.bind(queue).to(queueTtlExchange).with(queueRoutingKey);
    }
}
