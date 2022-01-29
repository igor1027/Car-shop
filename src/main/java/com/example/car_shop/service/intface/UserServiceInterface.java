package com.example.car_shop.service.intface;

import com.example.car_shop.entity.User;

import java.util.Optional;

public interface UserServiceInterface {

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(String phone);
    void saveUser(User user);

}
