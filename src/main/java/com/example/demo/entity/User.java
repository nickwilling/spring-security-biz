package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {
    private Long userId;
    private String loginName;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Integer status;
    private String loginIp;
    private Date loginDate;
    private Date addTime;
    private Integer addUser;
    private Date updateTime;
    private Integer updateUser;
    private Integer delFlag;
    private String remark;
}
