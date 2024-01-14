package com.berk.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFootballFieldModel implements Serializable {
    private String userid;
    private String name;
    private String telephoneNumber;
    private String city;
    private String district;
    private String address;
    private Double price;
    private String email;// bıldırım olarak maıl gıdecek.
//    private String image;
}
