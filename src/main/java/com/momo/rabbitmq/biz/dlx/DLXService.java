package com.momo.rabbitmq.biz.dlx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DLXService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DLXService.class);

    @Value("${rabbitmq.test.dlx.normalExchange}")
    private String normalExchangeName;
    @Value("${rabbitmq.test.dlx.normalRoutingKey}")
    private String normalRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendMessage() {
        String message = "test message";
        this.rabbitTemplate.convertAndSend(normalExchangeName, normalRoutingKey, message);
        return true;
    }

}
