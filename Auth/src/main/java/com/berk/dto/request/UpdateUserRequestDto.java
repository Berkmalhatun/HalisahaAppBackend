package com.berk.dto.request;

import com.berk.repository.enums.ERole;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private ERole role;
}
