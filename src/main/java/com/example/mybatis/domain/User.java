package com.example.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
     private Integer id;
     private String email;
     private String password;
     private LocalDate birthday;
     private Sex sex;
     private Integer loginCount;
     private LocalDateTime lastLoginTime;
}
