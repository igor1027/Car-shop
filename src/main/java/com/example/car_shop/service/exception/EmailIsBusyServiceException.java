package com.example.car_shop.service.exception;

public class EmailIsBusyServiceException extends Exception{

    public EmailIsBusyServiceException (String message){
        super(message);
    }

    public EmailIsBusyServiceException(){
        super("Email is busy");
    }
}
