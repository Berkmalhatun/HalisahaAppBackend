package com.berk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    KULLANICI_BULUNAMADI(1003,"Aradınız kullanıcı sistemde kayıtlı değildir.", BAD_REQUEST),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004,"Girilen paralolar uyuşmamaktadır.", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"Bu kullanıcı adı daha once alınmıstır.", BAD_REQUEST),
    LOGIN_ERROR_EMAIL_PASSWORD(1006,"Email ya da şifre hatalıdır.", BAD_REQUEST),
    INVALID_TOKEN(1007,"Geçersiz token.", BAD_REQUEST),
    EMAIL_ERROR(1008,"Email daha önce alınmıştır.", BAD_REQUEST),
    PASSWORD_ERROR(1009,"Şifrenizi yanlış girdiniz.Lutfen tekrar deneyiniz.", BAD_REQUEST),
    NEWPASSWORD_ERROR(1010,"Eski şifre ile yeni şifre aynı olamaz.", BAD_REQUEST),
    HALISAHA_BULUNAMADI(1011,"Boyle bir halısaha bulunamadı.", BAD_REQUEST),
    USERID_NOT_FOUND(1012,"Kullanıcı bulunumadı.", BAD_REQUEST),
    DATE_AND_TIME_EMPTY(1013,"Saat aralığı boş olamaz.", BAD_REQUEST),
    KIRALAMA_ISLEMI_BULUNAMADI(1014,"Kiralama talebi bulunamadı.", BAD_REQUEST),
    KIRALAMA_ISLEMI_YAPILAMADI(1015,"Seçilen saat aralığı dolu.", BAD_REQUEST),
    KIRALAMA_GECMISI_YOK(1016,"Kiralama geçmişiniz boş..", BAD_REQUEST),
    KIRALAMA_ISLEMI_IPTAL_EDILEMEZ(1017,"Geçmiş işlemler iptal edilemez." ,BAD_REQUEST ),
    URUN_EKLEME(2001,"Ürün ekleme başarısız oldu", INTERNAL_SERVER_ERROR),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre giriş yaptınız", BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
