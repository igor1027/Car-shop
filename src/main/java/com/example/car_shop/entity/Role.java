package com.example.car_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String roleName;
//    @Override
//    public String getAuthority() {
//        return name();
//    }
}
