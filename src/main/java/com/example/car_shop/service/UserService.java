package com.example.car_shop.service;

import com.example.car_shop.entity.User;
import com.example.car_shop.entity.UserStatus;
import com.example.car_shop.repository.UserRepository;
import com.example.car_shop.service.intface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("UserServiceInterface")
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));

           return userRepository.save(user);

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByPhoneNumber(String phone) {
        return userRepository.findByPhoneNumber(phone);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }
}
