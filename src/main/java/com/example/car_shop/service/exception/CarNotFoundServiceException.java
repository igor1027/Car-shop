package com.example.car_shop.service.exception;

public class CarNotFoundServiceException extends Exception{
    public CarNotFoundServiceException() {
        super("Board not found");
    }

    public CarNotFoundServiceException(String message) {
        super(message);
    }
}
