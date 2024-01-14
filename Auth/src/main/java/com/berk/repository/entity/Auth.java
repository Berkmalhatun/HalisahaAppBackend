package com.berk.repository.entity;

import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Auth extends BaseEntity{
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String telNo;
    private String password;
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;
    private ERole role;
}
