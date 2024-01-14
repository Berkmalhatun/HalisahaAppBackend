package com.berk.dto.request;

import com.berk.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFootballFieldRequestDto {
    private String id;
    private String name;
    private String telephoneNumber;
    private String city;
    private String district;
    private String address;
    private Double price;
    private String email;// bıldırım olarak maıl gıdecek.
}
