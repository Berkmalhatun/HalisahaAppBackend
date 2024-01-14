package com.berk.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    //directexchange 1 tane ...
    private String authDirectExchange = "auth-direct-exchange";
    private String createUserBindingKey = "create-user-binding-key";
    private String createUserQueue = "create-user-queue";

    @Bean
    DirectExchange authDirectExchange(){
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue createUserQueue(){
        return new Queue(createUserQueue);
    }
    @Bean
    public Binding createUserBindingKey(final Queue createUserQueue , final DirectExchange authDirectExchange){
        return BindingBuilder.bind(createUserQueue).to(authDirectExchange).with(createUserBindingKey);
    }
    private String resetPasswordBindingKey = "reset-password-binding-key";
    private String resetPasswordQueue = "reset-password-queue";
    @Bean
    Queue resetPasswordQueue(){
        return new Queue(resetPasswordQueue);
    }
    @Bean
    public Binding resetPasswordBindingKey(final Queue resetPasswordQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(resetPasswordQueue).to(authDirectExchange).with(resetPasswordBindingKey);
    }
    private String deleteUserBindingKey= "delete-user-binding-key";
    private String deleteUserQueue= "delete-user-queue";
    @Bean
    Queue deleteUserQueue(){
        return new Queue(deleteUserQueue);
    }
    @Bean
    public Binding deleteUserBindingKey(final Queue deleteUserQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(deleteUserQueue).to(authDirectExchange).with(deleteUserBindingKey);
    }
    private String updateUserBindingKey= "update-user-binding-key";
    private String updateUserQueue= "update-user-queue";

    @Bean
    Queue updateUserQueue(){
        return new Queue(updateUserQueue);
    }
    @Bean
    public Binding updateUserBindingKey(final Queue updateUserQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(updateUserQueue).to(authDirectExchange).with(updateUserBindingKey);
    }
}
