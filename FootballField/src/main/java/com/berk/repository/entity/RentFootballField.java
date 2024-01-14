package com.berk.repository.entity;

import com.berk.repository.enums.EOccupancyStatus;
import com.berk.repository.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class RentFootballField extends BaseEntity{
    @Id
    private String id;
    private String footballFieldid;//hangı halısaha oldugu burada yazacak.
    private String userid;// token dan cekılecek
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate; //tarih kiralama yaparken bos bırakılamaz.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDate; //tarih kiralama yaparken bos bırakılamaz.
    private Long time; // zaman kiralama yaparken bos bırakılamaz.
    @Builder.Default
    private EOccupancyStatus occupancyStatus=EOccupancyStatus.BLANK; // kiralanan saat aralıgında durumu kontrol eder.
    // halısaha sahıplerı manuel olarak bırıne sahayı kıralayabılsın dıye isim , soyisim , eposta , telno eklenmıstır.
    private String name;
    private String surname;
    private String telNo;
    private String email;
}
