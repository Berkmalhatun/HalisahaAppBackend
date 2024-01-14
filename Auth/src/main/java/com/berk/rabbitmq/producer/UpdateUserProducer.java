package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.UpdateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserProducer {
    private final RabbitTemplate rabbitTemplate;
    private String authDirectExchange = "auth-direct-exchange";
    private String updateUserBindingKey = "update-user-binding-key";

    public void updateUser(UpdateUserModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,updateUserBindingKey,model);
    }
}
