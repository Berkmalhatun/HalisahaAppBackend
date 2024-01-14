package com.berk.rabbitmq.consumer;

import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.DeleteUserModel;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserConsumer {

    private final UserService userService;

    @RabbitListener(queues = "delete-user-queue")
    public void deleteUser(DeleteUserModel model){
        userService.deleteUser(model);
    }
}
