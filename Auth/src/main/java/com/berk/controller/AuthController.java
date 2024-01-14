package com.berk.controller;

import static com.berk.constants.EndPoints.*;

import com.berk.dto.request.LoginUserRequestDto;
import com.berk.dto.request.RegisterUserRequestDto;
import com.berk.dto.request.UpdateUserPasswordRequestDto;
import com.berk.dto.request.UpdateUserRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.dto.responce.UpdateUserResponseDto;
import com.berk.repository.entity.Auth;
import com.berk.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;
    @CrossOrigin("*")
    @PostMapping(REGISTER)
    public ResponseEntity<MessageResponseDto> register(@RequestBody RegisterUserRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @CrossOrigin("*")
    @PutMapping(UPDATE)
    public ResponseEntity<MessageResponseDto> update(@RequestBody UpdateUserRequestDto dto){
        return ResponseEntity.ok(authService.updateUser(dto));
    }
    @CrossOrigin("*")
    @PutMapping(UPDATE_PASSWORD)
    public ResponseEntity<MessageResponseDto> updatePassword(@RequestBody UpdateUserPasswordRequestDto dto){
        return ResponseEntity.ok(authService.updateUserPassword(dto));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(DELETE_USER)
    public ResponseEntity<MessageResponseDto> deleteUser(String id) {
        return ResponseEntity.ok(authService.deleteUser(id));
    }
    @CrossOrigin("*")
    @PostMapping("/dologin")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginUserRequestDto dto) {
        // authService.login(dto) işlemini gerçekleştir ve sonucu dön
        return ResponseEntity.ok(authService.login(dto));
    }
    @PutMapping(UPDATE_ACTIVE)
    public ResponseEntity<MessageResponseDto> updateActiveUser(String id){
        return ResponseEntity.ok(authService.updateActiveUser(id));
    }
    @GetMapping(GETALL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Auth> findAllUser(){
        return authService.findAll();
    }

    @CrossOrigin("*")
    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(String token){
        return ResponseEntity.ok(authService.validateToken(token));
    }
    @CrossOrigin("*")
    @GetMapping("/user-details")
    public ResponseEntity<UpdateUserResponseDto> findByUserDetailsFromId(@RequestParam String id){
    return ResponseEntity.ok(authService.findByUserDetailsFromId(id));
}
}
