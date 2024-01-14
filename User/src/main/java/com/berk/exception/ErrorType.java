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
    REGISTER_ERROR_PASSWORD_UNMATCH(1004,"Girilen paralolar uyuşmamaktadır.", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_USERNAME(1005,"Bu kullanıcı adı daha once alınmıstır.", INTERNAL_SERVER_ERROR),
    LOGIN_ERROR_EMAIL_PASSWORD(1006,"Email ya da şifre hatalıdır.", INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(1007,"Geçersiz token.", INTERNAL_SERVER_ERROR),
    EMAIL_ERROR(1008,"Email daha önce alınmıştır.", INTERNAL_SERVER_ERROR),
    PASSWORD_ERROR(1009,"Şifrenizi yanlış girdiniz.Lutfen tekrar deneyiniz.", INTERNAL_SERVER_ERROR),
    NEWPASSWORD_ERROR(1010,"Eski şifre ile yeni şifre aynı olamaz.", INTERNAL_SERVER_ERROR),
    INVALID_ROLE(1011,"Bu özellikten sadece halısaha sahibi olan kişiler yararlanabilir.", INTERNAL_SERVER_ERROR),

    URUN_EKLEME(2001,"Ürün ekleme başarısız oldu", INTERNAL_SERVER_ERROR),
    METHOD_MIS_MATCH_ERROR(2002,"Giriş yaptığınız değer, istenilen değerle uyuşmamaktadır", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"URL içinde eksik parametre gönderimi",BAD_REQUEST),
    INVALID_PARAMETER(3001,"Geçersiz parametre giriş yaptınız", BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
