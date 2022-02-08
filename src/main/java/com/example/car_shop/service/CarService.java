package com.example.car_shop.service;

import com.example.car_shop.dto.CarDto;
import com.example.car_shop.entity.Car;
import com.example.car_shop.entity.User;
import com.example.car_shop.repository.CarRepository;
import com.example.car_shop.service.exception.CarNotFoundServiceException;
import com.example.car_shop.service.intface.CarServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CarService implements CarServiceInterface {

    private CarRepository carRepository;
    private ModelMapper mapper;

    public CarService(@Lazy CarRepository carRepository, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.mapper = mapper;
    }

    @Override
    public Car saveCar(CarDto carDto, User user) {
        Car car = mapper.map(carDto, Car.class);
        car.setCreator(user);
        Car saveCar = carRepository.save(car);

        log.info("Create new car: {}", saveCar);
        return saveCar;
    }

    @Override
    public Optional<Car> getCarById(long id, User user) {
        return carRepository.findByIdAndCreator(id, user);
    }

    public Optional<Car> id(long id){
        return carRepository.findById(id);
    }

    @Override
    public Car deleteCar(long id, User user) throws CarNotFoundServiceException {
        Car car = carRepository.findByIdAndCreator(id, user).orElseThrow(CarNotFoundServiceException::new);
        carRepository.delete(car);

        log.info("Success delete car: {}", car);
        return car;
    }

    @Override
    public Page<Car> findAllCar(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public List<Car> findByIdAndCreator(User user) {
        return carRepository.findAllByCreator(user);
    }
}
