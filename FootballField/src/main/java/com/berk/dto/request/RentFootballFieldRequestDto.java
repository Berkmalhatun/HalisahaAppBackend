package com.berk.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentFootballFieldRequestDto {
    private String footballFieldid;//hangı halısaha oldugu burada yazacak.
    private String userid;// token dan cekılecek
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate; //tarih kiralama yaparken bos bırakılamaz.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDate; //tarih kiralama yaparken bos bırakılamaz.
    private Long time; // zaman kiralama yaparken bos bırakılamaz.
}
