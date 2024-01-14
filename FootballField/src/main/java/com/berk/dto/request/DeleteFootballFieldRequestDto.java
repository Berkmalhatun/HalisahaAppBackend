package com.berk.dto.request;

import com.berk.repository.enums.EStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteFootballFieldRequestDto {
    private String id;
    private EStatus status;
}
