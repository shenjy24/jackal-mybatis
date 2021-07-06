package com.jonas.dao.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Integer status;
    private Integer score;
    private Long ctime;
    private Long utime;
}
