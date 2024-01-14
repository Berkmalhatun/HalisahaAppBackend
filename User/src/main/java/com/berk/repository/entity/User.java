package com.berk.repository.entity;

import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class User extends BaseEntity{
    @Id
    private String id;
    private String authid;
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private String password;
    private EStatus status;
    private ERole role;
}
