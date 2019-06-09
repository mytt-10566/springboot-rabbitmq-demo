package com.momo.rabbitmq.web;

import com.momo.rabbitmq.biz.dlx.DLXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlx")
public class DLXController {

    @Autowired
    private DLXService dlxService;

    @GetMapping("/send/msg")
    public boolean sendQueueMsgTTL() {
        return dlxService.sendMessage();
    }

}
