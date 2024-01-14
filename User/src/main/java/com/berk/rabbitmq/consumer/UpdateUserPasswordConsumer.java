package com.berk.rabbitmq.consumer;

import com.berk.rabbitmq.model.UpdateUserPasswordModel;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserPasswordConsumer {
    private final UserService userService;

    @RabbitListener(queues = "reset-password-queue")
    public void updateUserPassword(UpdateUserPasswordModel model){
        userService.updatePassword(model);
    }
}
