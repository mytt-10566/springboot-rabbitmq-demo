package com.momo.rabbitmq.biz.ttl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TTLService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TTLService.class);

    @Value("${rabbitmq.test.ttl.msgQueueTtlExchange}")
    private String msgQueueTtlExchange;
    @Value("${rabbitmq.test.ttl.msgQueueRoutingKey}")
    private String msgQueueRoutingKey;

    @Value("${rabbitmq.test.ttl.singleMsgTtlExchange}")
    private String singleMsgTtlExchange;
    @Value("${rabbitmq.test.ttl.singleMsgRoutingKey}")
    private String singleMsgRoutingKey;

    @Value("${rabbitmq.test.ttl.queueTtlExchange}")
    private String queueTtlExchange;
    @Value("${rabbitmq.test.ttl.queueRoutingKey}")
    private String queueRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendQueueMsgTTL() {
        String message = "test queue msg ttl message";
        this.rabbitTemplate.convertAndSend(msgQueueTtlExchange, msgQueueRoutingKey, message);
        return true;
    }

    public boolean sendSingleMsgTTL() {
        String message = "test queue msg ttl message";
        this.rabbitTemplate.convertAndSend(singleMsgTtlExchange, singleMsgRoutingKey, message, msg -> {
            MessageProperties messageProperties = msg.getMessageProperties();
            messageProperties.setExpiration("5000");
            return msg;
        });
        return true;
    }

    public boolean sendQueueTTL() {
        String message = "test queue msg ttl message";
        this.rabbitTemplate.convertAndSend(queueTtlExchange, queueRoutingKey, message);
        return true;
    }
}
