package com.jonas.dao.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Integer status;
    private BigInteger score;
    private Long ctime;
    private Long utime;
}
