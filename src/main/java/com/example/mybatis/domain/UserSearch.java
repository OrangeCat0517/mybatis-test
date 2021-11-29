package com.example.mybatis.domain;

import lombok.Data;

@Data
public class UserSearch {
    private Integer id;
    private String email;
    private Integer loginCount;
}
