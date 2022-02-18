package com.example.car_shop.repository;

import com.example.car_shop.entity.Car;
import com.example.car_shop.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>{

    Page<Car> findAll(Pageable pageable);
    Optional<Car> findByIdAndCreator(long id, User user);
    List<Car> findAllByCreator(User user);

    Page<Car> findAllByMark(String mark, Pageable pageable);
    @Override
    Optional<Car> findById(Long id);
}
