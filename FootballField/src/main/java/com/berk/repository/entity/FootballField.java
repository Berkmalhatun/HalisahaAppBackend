package com.berk.repository.entity;

import com.berk.repository.enums.EOccupancyStatus;
import com.berk.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class FootballField extends BaseEntity{
    @Id
    private String id;
    private String userid;//halısaha sahibinin userid'si
    private String name;
    private String telephoneNumber;
    private String city;
    private String district;
    private String address;
    private Double price;
//    @Builder.Default
//    private EOccupancyStatus occupancyStatus=EOccupancyStatus.BLANK; // Kiralanan tarıh saate gore otomatık olarak dolacak olacak.
    private Double score;
    @Builder.Default
    private EStatus status=EStatus.ACTIVE; // Halı saha sıstemden kaydı sılınmedıgı surece aktıf olarak kalacak.
    private String email;// bıldırım olarak maıl gıdecek.
//    private String image;
}
