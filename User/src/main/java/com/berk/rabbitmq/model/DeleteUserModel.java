package com.berk.rabbitmq.model;

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
public class DeleteUserModel implements Serializable {
    private String id;
    private EStatus status;
}
