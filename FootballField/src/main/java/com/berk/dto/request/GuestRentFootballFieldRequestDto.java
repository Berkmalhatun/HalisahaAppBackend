package com.berk.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestRentFootballFieldRequestDto {
    private String footballFieldid;//hangı halısaha oldugu burada yazacak.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDate; //tarih kiralama yaparken bos bırakılamaz.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDate; //tarih kiralama yaparken bos bırakılamaz.
    private Long time; // zaman kiralama yaparken bos bırakılamaz.
    private String name;
    private String surname;
    private String telNo;
    private String email;
}
