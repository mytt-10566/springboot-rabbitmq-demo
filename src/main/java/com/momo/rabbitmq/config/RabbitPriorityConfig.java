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
 * 优先级队列
 *
 * @author moqinggen
 * @date 2019/06/09
 */
@Configuration
public class RabbitPriorityConfig {

    @Value("${rabbitmq.test.priority.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.test.priority.queue}")
    private String queueName;

    @Value("${rabbitmq.test.priority.routingKey}")
    private String routingKey;


    @Bean
    public DirectExchange priorityExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue priorityQueue() {
        // 定义队列优先的最大优先级最大为10
        Map<String, Object> args = new HashMap<>(4);
        args.put("x-max-priority", 10);
        return new Queue(queueName, true, false, false, args);
    }

    @Bean
    Binding bindingOriorityQueue(Queue priorityQueue, DirectExchange priorityExchange) {
        return BindingBuilder.bind(priorityQueue).to(priorityExchange).with(routingKey);
    }
}
