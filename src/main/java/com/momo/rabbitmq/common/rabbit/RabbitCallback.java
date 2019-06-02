package com.momo.rabbitmq.common.rabbit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitCallback implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitCallback.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * ReturnCallback是交换机路由到队列失败才会回调
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.warn("return callback, receive message" + message.toString() + ", " + replyText + ", " + exchange + ", " + routingKey);
    }

    /**
     * ConfirmCallback是当生产者将消息投递到交换机成功/失败后的回调
     *  投递到交换器成功，ack=true，此时无论消息是否可以路由到队列
     *  投递到交换器失败，ack=false
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            LOGGER.info("confirm callback, send message success, ack=" + ack);
        } else {
            LOGGER.error("confirm callback, send message fail, ack=" + ack);
        }
    }
}