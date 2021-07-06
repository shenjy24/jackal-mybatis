package com.jonas;

import com.jonas.dao.domain.User;
import com.jonas.dao.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class AppTest {

    //SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
    //使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() {
        //定义读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader = null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader = Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        //SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）, 一旦创建了 SqlSessionFactory，就不再需要它了
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void testGetUser() {
        //创建session实例
        //每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
        //绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //传入参数查询，返回结果
            User user = session.selectOne("getUser", 1);
            //输出结果
            System.out.println(user);
        }
    }

    @Test
    public void testGetUserByMapper() {
        //创建session实例
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //映射器实例的最合适的作用域是方法作用域，映射器实例并不需要被显式地关闭
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.getUser(1);
            System.out.println(user);
        }
    }
}
