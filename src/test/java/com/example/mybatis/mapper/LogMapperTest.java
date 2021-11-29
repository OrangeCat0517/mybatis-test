package com.example.mybatis.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogMapperTest {

    private SqlSession sqlSession;
    private LogMapper logMapper;
    private SqlSessionFactory sqlSessionFactory;
    @BeforeEach
    void setUp() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = sqlSessionFactory.openSession();
        logMapper = sqlSession.getMapper(LogMapper.class);
    }

    @AfterEach
    void tearDown() {
        sqlSession.close();
    }

    @Test
    void save() {
        logMapper.save(LocalDateTime.now(), 3);
        sqlSession.commit();
    }

    @Test
    void findByUserId() {
        logMapper.findByUserId(3).forEach(System.out::println);
    }
}