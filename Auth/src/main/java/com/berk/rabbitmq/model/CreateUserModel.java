package com.berk.rabbitmq.model;

import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserModel implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private String password;
    private EStatus status;
    private ERole role;
}
