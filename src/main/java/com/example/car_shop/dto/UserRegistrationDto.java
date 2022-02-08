package com.example.car_shop.dto;

import com.example.car_shop.entity.Role;
import com.example.car_shop.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

    @NotBlank(message = "Required for entering")
    @Length(min = 3, max = 30, message = "The length of the username must be from 3 to 30 characters")
    private String username;

    @NotBlank(message = "Required for entering")
    @Length(min = 3, max = 30, message = "The length of the email must be from 4 to 30 characters")
    @Email(message = "Enter the correct email address")
    private String email;

    @NotBlank(message = "Required for entering")
    @Length(min = 3, max = 30, message = "The length of the password must be from 3 to 30 characters")
    private String password;

    @NotBlank(message = "Required for entering")
    @Length(min = 2, max = 30, message = "The length of the name must be from 3 to 30 characters")
    private String name;

    @NotBlank(message = "Required for entering")
    @Length(min = 3, max = 30, message = "The length of the lastname must be from 3 to 30 characters")
    private String lastname;

    @NotBlank(message = "Required for entering")
    @Length(min = 12, max = 14, message = "The length of the phone must be from 3 to 30 characters")
    private String phone;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public UserStatus getStatus(UserStatus active) {
//        return status;
//    }
//
//    public void setStatus(UserStatus status) {
//        this.status = status;
//    }
}
