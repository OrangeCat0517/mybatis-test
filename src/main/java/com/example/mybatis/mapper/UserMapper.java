package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;
import com.example.mybatis.domain.UserSearch;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int save(User user);
    int deleteById(int id);
    int updateById(User user);
    User findOne(Integer id);
    List<User> findAll();
    List<User> findByConditon(UserSearch userSearch);
    List<User> findByEmail(@Param("email") String email);
}
