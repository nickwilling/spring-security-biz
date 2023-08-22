package com.example.demo.mapper;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserRole;
import com.example.demo.models.RoleEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface UserRoleDAO {
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "roleId", column = "role_id")
    })
    @Select("select * from sys_user_role where user_id = #{userId}")
   List<UserRole> getUserRoleByUserId(Long userId);

    @Insert({
            "<script>",
            "insert into sys_user_role(user_id, role_id) values ",
            "<foreach collection='roles' item='item' index='index' separator=','>",
            "(#{userId}, #{item.roleId})",
            "</foreach>",
            "</script>"
    })
    void saveList(@Param("roles") Set<Role> roleList,Long userId);
}
