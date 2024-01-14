package com.berk.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String footballFieldDirectExchange = "football-field-direct-exchange";
    private String createFootballFieldQueue = "create-football-field-queue";

    @Bean
    Queue createFootballFieldQueue(){
        return new Queue(createFootballFieldQueue);
    }
}
