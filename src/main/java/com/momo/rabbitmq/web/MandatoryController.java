package com.momo.rabbitmq.web;

import com.momo.rabbitmq.biz.mandatory.MandatoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mandatory")
public class MandatoryController {

    @Autowired
    private MandatoryService mandatoryService;

    @GetMapping("/send/normal")
    public boolean sendNormalMessage() {
        return mandatoryService.sendNormalMessage();
    }

    @GetMapping("/send/unnormal")
    public boolean sendUnNormalMessage() {
        return mandatoryService.sendAbnormalMessage();
    }

}
