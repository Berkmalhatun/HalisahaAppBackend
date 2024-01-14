package com.berk.rabbitmq.consumer;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserService userService;

    @RabbitListener(queues = "create-user-queue")
    public void createUser(CreateUserModel model){
        userService.createUser(model);
    }
}
