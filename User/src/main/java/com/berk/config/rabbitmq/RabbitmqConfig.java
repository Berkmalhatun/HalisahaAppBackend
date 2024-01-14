package com.berk.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitmqConfig {
    private String userDirectExchange = "user-direct-exchange";
    @Bean
    DirectExchange userDirectExchange(){
        return new DirectExchange(userDirectExchange);
    }
    private String createUserQueue = "create-user-queue";
    @Bean
    Queue createUserQueue(){
        return new Queue(createUserQueue);
    }
    private String resetPasswordQueue = "reset-password-queue";
    @Bean
    Queue updateUserPasswordQueue(){
        return new Queue(resetPasswordQueue);
    }
    private String deleteUserQueue= "delete-user-queue";
    @Bean
    Queue deleteUserQueue(){
        return new Queue(deleteUserQueue);
    }
    private String updateUserQueue= "update-user-queue";

    @Bean
    Queue updateUserQueue(){
        return new Queue(updateUserQueue);
    }


    private String createFootballFieldBindingKey = "create-football-field-binding-key";
    private String createFootballFieldQueue = "create-football-field-queue";

    @Bean
    Queue createFootballFieldQueue(){
        return new Queue(createFootballFieldQueue);
    }
    @Bean
    public Binding createFootballFieldBindingKey(final Queue createFootballFieldQueue , final DirectExchange userDirectExchange){
        return BindingBuilder.bind(createFootballFieldQueue).to(userDirectExchange).with(createFootballFieldBindingKey);
    }


}
