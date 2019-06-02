package com.momo.rabbitmq.biz.ae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlternateExchangeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlternateExchangeService.class);

    @Value("${rabbitmq.test.ae.normalExchange}")
    private String normalExchangeName;
    @Value("${rabbitmq.test.ae.normalRoutingKey}")
    private String normalRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendNormalMessage() {
        String message = "test normal message";
        this.rabbitTemplate.convertAndSend(normalExchangeName, normalRoutingKey, message);
        return true;
    }

    public boolean sendAbnormalMessage() {
        String message = "test abnormal message";
        this.rabbitTemplate.convertAndSend(normalExchangeName, normalRoutingKey + "2", message);
        return true;
    }
}
