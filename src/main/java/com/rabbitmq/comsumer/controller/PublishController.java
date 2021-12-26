package com.rabbitmq.comsumer.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private AmqpTemplate template;

    @PostMapping("/message")
    public String publish(@RequestBody String message) {

        System.out.println("Incoming message: + " + message);
        template.convertAndSend(message);
        return "Message published";
    }
}
