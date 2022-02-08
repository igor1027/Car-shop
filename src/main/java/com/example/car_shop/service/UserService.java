package com.example.car_shop.service;

import com.example.car_shop.dto.UserRegistrationDto;
import com.example.car_shop.entity.Car;
import com.example.car_shop.entity.Role;
import com.example.car_shop.entity.User;
import com.example.car_shop.entity.UserStatus;
import com.example.car_shop.repository.RoleRepository;
import com.example.car_shop.repository.UserRepository;
import com.example.car_shop.service.exception.EmailIsBusyServiceException;
import com.example.car_shop.service.exception.PhoneIsBusyServiceException;
import com.example.car_shop.service.exception.UsernameIsBusyServiceException;
import com.example.car_shop.service.intface.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Transactional
@Service("UserServiceInterface")
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserService(@Lazy UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
//        return userRepository.findByEmail(email);
        return null;
    }

    @Override
    public Optional<User> findUserByPhoneNumber(String phone) {
        return userRepository.findByPhoneNumber(phone);
    }

    @Override
    public User saveUser(UserRegistrationDto userDto) throws UsernameIsBusyServiceException, EmailIsBusyServiceException, PhoneIsBusyServiceException {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new EmailIsBusyServiceException();
        }
        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new UsernameIsBusyServiceException();
        }
        if (userRepository.existsByPhone(userDto.getPhone())){
            throw new PhoneIsBusyServiceException();
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        User saveUser = userRepository.save(user);

        log.info("A new user has been registered: {}", saveUser);
        return saveUser;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//    }

}
