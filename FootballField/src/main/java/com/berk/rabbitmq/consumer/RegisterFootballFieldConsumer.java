package com.berk.rabbitmq.consumer;


import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import com.berk.service.FootballFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterFootballFieldConsumer {
    private final FootballFieldService footballFieldService;

    @RabbitListener(queues = "create-football-field-queue")
    public void createFootballField(RegisterFootballFieldModel model){
        footballFieldService.register(model);
    }
}
