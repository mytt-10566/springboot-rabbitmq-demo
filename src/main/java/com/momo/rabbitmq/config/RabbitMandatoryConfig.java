package com.momo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mandatory参数
 *
 * @author moqinggen
 * @date 2019/06/02
 */
@Configuration
public class RabbitMandatoryConfig {

    @Value("${rabbitmq.test.mandatory.exchangeName}")
    private String exchangeName;

    @Value("${rabbitmq.test.mandatory.queueName}")
    private String queueName;

    @Value("${rabbitmq.test.mandatory.routingKey}")
    private String routingKey;


    @Bean
    public DirectExchange mandatoryExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue mandatoryQueue() {
        // true 持久化
        return new Queue(queueName, true);
    }

    @Bean
    Binding bindingMandatoryQueue(Queue mandatoryQueue, DirectExchange mandatoryExchange) {
        return BindingBuilder.bind(mandatoryQueue).to(mandatoryExchange).with(routingKey);
    }
}
