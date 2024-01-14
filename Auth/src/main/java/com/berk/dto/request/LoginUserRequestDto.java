package com.berk.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequestDto {
    String email;
    String password;
}
