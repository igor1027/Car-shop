package com.example.car_shop.service.exception;

public class PhoneIsBusyServiceException extends Exception{

    public PhoneIsBusyServiceException (String message){
        super(message);
    }

    public PhoneIsBusyServiceException(){
        super("Phone is busy");
    }
}
