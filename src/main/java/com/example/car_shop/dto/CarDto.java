package com.example.car_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String mark;
    private String model;
    private String fuel;
    private String color;
    private Integer year;
    private Integer kilometers;
    private String transmission;
    private Integer price;
    private Double engineCapacity;
    private String image;
    private String body;
    private String description;
}
