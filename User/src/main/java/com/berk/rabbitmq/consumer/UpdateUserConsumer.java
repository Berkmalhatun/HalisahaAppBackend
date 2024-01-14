package com.berk.rabbitmq.consumer;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.UpdateUserModel;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserConsumer {
    private final UserService userService;

    @RabbitListener(queues = "update-user-queue")
    public void updateUser(UpdateUserModel model){
        userService.updateUser(model);
    }
}
