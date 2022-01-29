package com.example.car_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    private String mark;
    private String model;
    private String fuel;
    private String color;
    private Integer year;
    private Integer kilometers;
    private String transmission;
    private Integer price;
    private Double engineCapacity;
}
