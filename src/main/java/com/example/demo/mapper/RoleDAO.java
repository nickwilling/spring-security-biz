package com.example.demo.mapper;

import com.example.demo.entity.Role;
import com.example.demo.models.RoleEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface RoleDAO {
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    @Select("Select role_id, role_name, status from sys_roles where role_name=#{roleName}")
    Optional<Role> findByName(RoleEnum roleName);

    @Select("Select * from sys_roles where role_Id=#{roleId}")
    Optional<Role> findById(Long roleId);
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    @Select("<script>" +
            "SELECT role_name FROM sys_roles WHERE role_id in \n" +
            "<foreach collection='roleIdList' index='index' item='item' open='(' separator=',' close=')'>#{item}</foreach> \n" +
            "</script>")
    List<Role> findByIdList(List<Long> roleIdList);
}

