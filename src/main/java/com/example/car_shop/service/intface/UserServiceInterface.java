package com.example.car_shop.service.intface;

import com.example.car_shop.dto.UserRegistrationDto;
import com.example.car_shop.entity.User;
import com.example.car_shop.service.exception.EmailIsBusyServiceException;
import com.example.car_shop.service.exception.PhoneIsBusyServiceException;
import com.example.car_shop.service.exception.UsernameIsBusyServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserServiceInterface extends UserDetailsService {

    Optional<User> findById(long id);
    User findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(String phone);
    User saveUser(UserRegistrationDto userRegistrationDto) throws EmailIsBusyServiceException, UsernameIsBusyServiceException, PhoneIsBusyServiceException;

}
