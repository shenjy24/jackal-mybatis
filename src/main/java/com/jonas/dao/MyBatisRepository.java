package com.jonas.dao;

import com.jonas.dao.domain.User;
import com.jonas.dao.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/3/1 14:00
 * @description MyBatisRepository
 */
public class MyBatisRepository {

    private static final String MYBATIS_CONFIG_PATH = "config/mybatis-config.xml";

    //SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
    //使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
    private static SqlSessionFactory sqlSessionFactory;

    static {
        //从工作目录读取配置文件
        File file = new File(MYBATIS_CONFIG_PATH);
        if (!file.exists()) {
            throw new RuntimeException("配置文件不存在");
        }
        //创建流
        Reader reader = null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader = new FileReader(file);
            //从resources中读取配置文件
            //reader = Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        //SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）, 一旦创建了 SqlSessionFactory，就不再需要它了
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 注册Mapper
     *
     * @param clazz
     */
    public static void registerMapper(Class<?> clazz) {
        if (!sqlSessionFactory.getConfiguration().hasMapper(clazz)) {
            sqlSessionFactory.getConfiguration().addMapper(clazz);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     * 获取SqlSession,注意关闭
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
