package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.CreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final RabbitTemplate rabbitTemplate;
    private String authDirectExchange = "auth-direct-exchange";
    private String createUserBindingKey = "create-user-binding-key";

    public void createUser(CreateUserModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,createUserBindingKey,model);
    }
}
