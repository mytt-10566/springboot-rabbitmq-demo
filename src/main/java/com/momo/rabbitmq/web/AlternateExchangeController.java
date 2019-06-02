package com.momo.rabbitmq.web;

import com.momo.rabbitmq.biz.ae.AlternateExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ae")
public class AlternateExchangeController {

    @Autowired
    private AlternateExchangeService aeService;

    @GetMapping("/send/normal")
    public boolean sendNormalMessage() {
        return aeService.sendNormalMessage();
    }

    @GetMapping("/send/unnormal")
    public boolean sendUnNormalMessage() {
        return aeService.sendAbnormalMessage();
    }

}
