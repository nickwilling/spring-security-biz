package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserDAO {
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "loginName", column = "login_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "loginIp", column = "login_ip"),
            @Result(property = "loginDate", column = "login_date"),
            @Result(property = "addTime", column = "add_time"),
            @Result(property = "addUser", column = "add_user"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "updateUser", column = "update_user"),
            @Result(property = "delFlag", column = "del_flag")
    })
    @Select("select * from sys_users where user_id= #{userId} for update")
    Optional<User> getUserByIdForUpdate(Long userId);

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "loginName", column = "login_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "loginIp", column = "login_ip"),
            @Result(property = "loginDate", column = "login_date"),
            @Result(property = "addTime", column = "add_time"),
            @Result(property = "addUser", column = "add_user"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "updateUser", column = "update_user"),
            @Result(property = "delFlag", column = "del_flag")
    })
    @Select("select * from sys_users where user_id= #{userId}")
    Optional<User> getUserById(Long userId);
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "loginName", column = "login_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "loginIp", column = "login_ip"),
            @Result(property = "loginDate", column = "login_date"),
            @Result(property = "addTime", column = "add_time"),
            @Result(property = "addUser", column = "add_user"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "updateUser", column = "update_user"),
            @Result(property = "delFlag", column = "del_flag")
    })
    @Select("select * from sys_users where phone=#{username} or email=#{username} ")
    Optional<User> getUserByUserName(String username);

    @Insert("insert into sys_users (user_id,user_name,password,phone,email,status,add_time,update_time,del_flag) values " +
            "(#{userId},#{userName},#{password},#{phone},#{email},0,now(),now(),0)")
    void saveUser(User user);

    @Update({
            "<script> ",
            "update sys_users set ",
            "<if test = \"enable == true\"> ",
            "status=0, update_time=now(), ",
            "</if> ",
            "login_date=now() ",
            "WHERE phone = #{username} or email = #{username}",
            "</script>"
    })
    void updateWhenLogin(String username, boolean enable);

    @Update("update sys_users set status = 1, update_time = now() where phone = #{username} or email = #{username}")
    void lockUser(String username);

}
