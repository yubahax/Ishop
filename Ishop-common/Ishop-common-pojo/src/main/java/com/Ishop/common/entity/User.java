package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;

    @TableField("password")
    private String password;

    @TableField("role")
    private String role;

    @TableField("isalive")
    private Integer isalive;

    @TableField("addtime")
    private String addtime;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("created")
    private String created;

    @TableField("updated")
    private String updated;

    @TableField("sex")
    private String sex;

    @TableField("address")
    private String address;

    @TableField("state")
    private Integer state;

    @TableField("description")
    private String description;

    @TableField("role_id")
    private Integer roleId;

    @TableField("file")
    private String file;

    @TableField("rolename")
    private String roleNames;

}
