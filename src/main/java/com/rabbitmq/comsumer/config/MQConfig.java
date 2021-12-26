package com.rabbitmq.comsumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    private Queue queue() {
        return QueueBuilder.durable("test_queue")
                .build();
    }

    private Exchange exchange() {
        return ExchangeBuilder.directExchange("test_exchange.direct")
                .durable(true)
                .build();
    }

    private Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with("test_routing_key")
                .noargs();
    }

    @Bean
    public RabbitTemplate template() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(new CachingConnectionFactory());
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
