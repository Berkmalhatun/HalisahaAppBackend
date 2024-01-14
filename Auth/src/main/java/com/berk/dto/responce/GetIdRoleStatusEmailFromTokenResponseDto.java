package com.berk.dto.responce;

import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetIdRoleStatusEmailFromTokenResponseDto {
    String id;
    ERole role;
    EStatus status;
    String email;
}
