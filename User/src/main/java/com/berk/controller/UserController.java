package com.berk.controller;

import com.berk.repository.entity.responce.MessageResponseDto;
import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import com.berk.repository.entity.User;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.berk.constants.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE_FOOTBALL_FIELD)
    @CrossOrigin("*")
    public ResponseEntity<MessageResponseDto> createFootballField(@ModelAttribute RegisterFootballFieldModel model){
        return ResponseEntity.ok(userService.createFootballField(model));
    }
    @PostMapping("findby-userid")
    public ResponseEntity<String> findByUserid(@RequestParam String id){
        return ResponseEntity.ok(userService.findByUserid(id));
    }
    @GetMapping("user-details")
    @CrossOrigin("*")
    public ResponseEntity<User> findByUser(String userid){
        return ResponseEntity.ok(userService.findByUser(userid));
    }
}
