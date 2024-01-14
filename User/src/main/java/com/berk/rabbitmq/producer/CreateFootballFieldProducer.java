package com.berk.rabbitmq.producer;

import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFootballFieldProducer {
    private final RabbitTemplate rabbitTemplate;

    private String userDirectExchange = "user-direct-exchange";
    private String createFootballFieldBindingKey = "create-football-field-binding-key";
    public void createFootballField(RegisterFootballFieldModel model){
        rabbitTemplate.convertAndSend(userDirectExchange,createFootballFieldBindingKey,model);
    }
}
