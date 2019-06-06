package com.momo.rabbitmq.web;

import com.momo.rabbitmq.biz.mandatory.MandatoryService;
import com.momo.rabbitmq.biz.ttl.TTLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ttl")
public class TTLController {

    @Autowired
    private TTLService ttlService;

    @GetMapping("/send/msg/queue")
    public boolean sendQueueMsgTTL() {
        return ttlService.sendQueueMsgTTL();
    }

    @GetMapping("/send/msg/single")
    public boolean sendSingleMsgTTL() {
        return ttlService.sendSingleMsgTTL();
    }

    @GetMapping("/send/queue")
    public boolean sendQueueTTL() {
        return ttlService.sendQueueTTL();
    }
}
