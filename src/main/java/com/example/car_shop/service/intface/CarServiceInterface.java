package com.example.car_shop.service.intface;

import com.example.car_shop.dto.CarDto;
import com.example.car_shop.entity.Car;
import com.example.car_shop.entity.User;
import com.example.car_shop.service.exception.CarNotFoundServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {

    Car saveCar(CarDto carDto, User user);

    Optional<Car> getCarById(long id, User user);

    Car deleteCar(long id, User user) throws CarNotFoundServiceException;

    Page<Car> findAllCar(Pageable pageable);

    List<Car> findByIdAndCreator(User user);

}
