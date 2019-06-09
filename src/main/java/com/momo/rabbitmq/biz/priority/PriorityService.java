package com.momo.rabbitmq.biz.priority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PriorityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityService.class);

    @Value("${rabbitmq.test.priority.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.test.priority.routingKey}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendMessage() {
        String content = "test normal message";
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, content, message -> {
            // 设置消息的优先级
            message.getMessageProperties().setPriority(8);
            return message;
        });
        return true;
    }

}
