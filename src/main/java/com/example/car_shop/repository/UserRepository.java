package com.example.car_shop.repository;

import com.example.car_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

   Optional <User> findByEmail(String email);

   @Query(name = "User.findByPhoneNumber", value = "select p from User p where p.phone = :t")
   Optional <User> findByPhoneNumber(@Param("t") String phone);
}
