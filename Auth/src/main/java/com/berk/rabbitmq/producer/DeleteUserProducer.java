package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.DeleteUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserProducer {
    private final RabbitTemplate rabbitTemplate;
    private String authDirectExchange = "auth-direct-exchange";
    private String deleteUserBindingKey= "delete-user-binding-key";

    public void deleteUser(DeleteUserModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,deleteUserBindingKey,model);
    }

}
