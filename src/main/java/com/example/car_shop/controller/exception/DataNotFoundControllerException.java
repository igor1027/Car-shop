package com.example.car_shop.controller.exception;

public class DataNotFoundControllerException extends Exception{
    public DataNotFoundControllerException(String message) {
        super(message);
    }

    public DataNotFoundControllerException() {
        super("Data not found");
    }
}
