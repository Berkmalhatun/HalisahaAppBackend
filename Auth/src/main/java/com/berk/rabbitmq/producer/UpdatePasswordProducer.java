package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.UpdateUserPasswordModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdatePasswordProducer {
    private final RabbitTemplate rabbitTemplate;
    private String authDirectExchange = "auth-direct-exchange";
    private String resetPasswordBindingKey = "reset-password-binding-key";

    public void resetPassword(UpdateUserPasswordModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,resetPasswordBindingKey,model);
    }
}
