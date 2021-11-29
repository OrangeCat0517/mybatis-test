package com.example.mybatis.mapper;

import com.example.mybatis.domain.Sex;
import com.example.mybatis.domain.User;
import com.example.mybatis.domain.UserSearch;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;


class UserMapperTest {
    private SqlSession sqlSession; //类似于JDBC中的Connection，数据库链接
    private UserMapper userMapper;
    private SqlSessionFactory sqlSessionFactory;
    @BeforeEach
    void setUp() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        //通过读取MyBatis配置文件可以生成SqlSessionFactory，这个对象表示数据库的所有关键信息
        sqlSession = sqlSessionFactory.openSession(); //通过SqlSessionFactory获得一个打开的数据库连接
        userMapper = sqlSession.getMapper(UserMapper.class);//mybatis利用映射文件帮我们生成了这个接口的实现类
    }

    @AfterEach
    void tearDown() {
        sqlSession.close(); //关闭数据库链接
    }

    @Test
    void save() {
        User user = new User();
        user.setEmail("tom@google.com");
        user.setPassword("123456");
        user.setBirthday(LocalDate.now());
        user.setSex(Sex.M);
        user.setLoginCount(0);
        user.setLastLoginTime(null);
        System.out.println(user); //此时id是null
        System.out.println(userMapper.save(user)); //要么是1(插入成功),要么是0(插入失败)
        //当插入一个主键自增的对象时，怎么获得数据库自动分配的这个主键
        sqlSession.commit(); //提交事务，增删该都需要提交事物才能生效

        System.out.println(user);
        //当在映射文件中加入useGeneratedKeys="true" keyProperty="id" keyColumn="id"之后，一旦插入成功，主键会被自动放入刚刚被插入的实体中。
    }

    @Test
    void deleteById() {
        userMapper.deleteById(2);
        sqlSession.commit();
    }

    @Test
    void updateById() {
        User user = new User();
        user.setId(3);
        user.setEmail("jerry@google.com");
        user.setPassword("654321");
        user.setBirthday(LocalDate.now());
        user.setSex(Sex.M);
        user.setLoginCount(0);
        user.setLastLoginTime(null);
        userMapper.updateById(user);
        sqlSession.commit(); //提交事务，增删该都需要提交事物才能生效
    }

    @Test
    void findOne() throws IOException {
        System.out.println(userMapper.findOne(3));
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.findOne(3));
        System.out.println(userMapper.findOne(3));
        System.out.println(userMapper.findOne(3));
        System.out.println(userMapper.findOne(3));
    }

    @Test
    void findAll() {
        userMapper.findAll().forEach(System.out::println);
    }

    @Test
    void xxx() {
        UserSearch userSearch = new UserSearch();
        userSearch.setId(3);
        userSearch.setEmail("jerry@google.com");
        userMapper.findByConditon(userSearch).forEach(System.out::println);
    }
}