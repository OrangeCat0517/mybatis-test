package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;

import java.util.List;

public interface UserMapper {
    int save(User user);
    int deleteById(Integer id);
    int updateById(User user);
    User findOne(Integer id);
    List<User> findAll();
}
