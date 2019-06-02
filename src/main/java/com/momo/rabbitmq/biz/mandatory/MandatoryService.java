package com.momo.rabbitmq.biz.mandatory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MandatoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MandatoryService.class);

    @Value("${rabbitmq.test.mandatory.exchangeName}")
    private String exchangeName;

    @Value("${rabbitmq.test.mandatory.routingKey}")
    private String routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendNormalMessage() {
        String message = "test normal message";
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        return true;
    }

    public boolean sendAbnormalMessage() {
        String message = "test abnormal message";
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey + "2", message);
        return true;
    }
}
