package com.berk.exception;

import lombok.Getter;

@Getter
public class FootballFieldServiceException extends RuntimeException{
    private final ErrorType errorType;

    public FootballFieldServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public FootballFieldServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
