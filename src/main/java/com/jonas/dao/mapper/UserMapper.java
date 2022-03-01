package com.jonas.dao.mapper;

import com.jonas.dao.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {

    User getUser(int id);

    @Select("select * from `user` where `user_score` > #{s}")
    List<User> listUser(BigInteger score);

    void updateUser(User user);

    @Insert("insert into `user`(`user_id`,`user_name`,`user_age`,`user_status`,`user_score`, `ctime`,`utime`) " +
            "values (#{user.id},#{user.name},#{user.age},#{user.status},#{user.score},#{user.ctime},#{user.utime})")
    void saveUser(@Param("user") User user);
}
