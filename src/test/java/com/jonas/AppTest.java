package com.jonas;

import com.jonas.dao.MyBatisRepository;
import com.jonas.dao.domain.User;
import com.jonas.dao.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

public class AppTest {

    @Test
    public void testGetUser() {
        //创建session实例
        //每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
        //绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
        try (SqlSession session = MyBatisRepository.getSqlSession()) {
            //传入参数查询，返回结果
            User user = session.selectOne("getUser", 1);
            //输出结果
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserByMapper() {
        //创建session实例
        try (SqlSession session = MyBatisRepository.getSqlSession()) {
            //映射器实例的最合适的作用域是方法作用域，映射器实例并不需要被显式地关闭
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUserByMapper() {
        //创建session实例
        try (SqlSession session = MyBatisRepository.getSqlSession()) {
            //映射器实例的最合适的作用域是方法作用域，映射器实例并不需要被显式地关闭
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);
            user.setScore(new BigInteger("112926528800000000000000000000000000000000000002319739038520784315200000002460000000246246"));
            user.setUtime(System.currentTimeMillis());
            userMapper.updateUser(user);
            //session.commit();
        }
    }

    @Test
    public void testListUser() {
        try (SqlSession session = MyBatisRepository.getSqlSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            List<User> users = userMapper.listUser(BigInteger.valueOf(100));
            System.out.println(users);
        }
    }

    @Test
    public void testSaveUser() {
        try (SqlSession session = MyBatisRepository.getSqlSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = new User(1, "Tom", 12, 1, BigInteger.TEN, System.currentTimeMillis(), System.currentTimeMillis());
            userMapper.saveUser(user);
            session.commit();
        }
    }
}
