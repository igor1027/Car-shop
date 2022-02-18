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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Optional<Car> findByid(long id){
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

    @Override
    public Page<Car> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.carRepository.findAll(pageable);
    }

    public Page<Car> findPaginatedSortedCar(String mark, int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.carRepository.findAllByMark(mark,pageable);
    }

    @Override
    public Page<Car> findAllByMark(String mark, Pageable pageable) {
        return carRepository.findAllByMark(mark, pageable);
    }

    @Override
    public Car update(long id, User user, String mark, String model, String fuel,
                       String color, Integer year,  Integer kilometers,
                       String transmission,  Integer price, Double engineCapacity,
                       String image, String body,String description) throws CarNotFoundServiceException {

        Car car = carRepository.findById(id).orElseThrow(CarNotFoundServiceException::new);

        car.setMark(mark);
        car.setModel(model);
        car.setFuel(fuel);
        car.setColor(color);
        car.setYear(year);
        car.setKilometers(kilometers);
        car.setTransmission(transmission);
        car.setPrice(price);
        car.setEngineCapacity(engineCapacity);
        car.setImage(image);
        car.setBody(body);
        car.setDescription(description);

        log.info("Success update car: {}", car);
        return carRepository.save(car);
    }
}
