package com.berk.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordRequestDto {
    String id;
    String password;
    String newPassword;
    String reNewPassword;
}
