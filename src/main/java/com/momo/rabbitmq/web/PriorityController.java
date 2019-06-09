package com.momo.rabbitmq.web;

import com.momo.rabbitmq.biz.priority.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @GetMapping("/send/msg")
    public boolean sendMessage() {
        return priorityService.sendMessage();
    }

}
