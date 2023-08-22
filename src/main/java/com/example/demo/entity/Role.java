package com.example.demo.entity;

import com.example.demo.models.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    Long roleId;
    RoleEnum roleName;
    Integer status;
}
