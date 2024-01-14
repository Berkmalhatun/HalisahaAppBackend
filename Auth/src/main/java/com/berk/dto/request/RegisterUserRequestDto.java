package com.berk.dto.request;

import com.berk.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private String password;
    private String rePassword;
    private ERole role;
}
