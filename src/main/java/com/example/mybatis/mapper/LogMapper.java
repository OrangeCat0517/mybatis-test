package com.example.mybatis.mapper;

import com.example.mybatis.domain.Log;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LogMapper {
    int save(@Param("l") LocalDateTime loginTime, @Param("i") Integer userId);
    List<Log> findByUserId(Integer userId);
}
