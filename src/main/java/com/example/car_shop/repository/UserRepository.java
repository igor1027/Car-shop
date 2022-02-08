package com.example.car_shop.repository;

import com.example.car_shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

//   @Query(name = "User.findByEmail", value = "select p from User p where p.email = :q")
//   Optional<User>findbyMail(@Param("q")String email);

   @Query(name = "User.findByPhoneNumber", value = "select p from User p where p.phone = :t")
   Optional <User> findByPhoneNumber(@Param("t") String phone);

   Optional<User>findByEmail(String email);

   boolean existsByPhone(String phone);
   boolean existsByUsername(String username);
   boolean existsByEmail(String email);
//   User findByEmail(String email);
   Optional<User> findById(long id);
}
