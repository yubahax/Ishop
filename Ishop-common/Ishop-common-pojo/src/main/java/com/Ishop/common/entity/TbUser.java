package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yubahax
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

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

    @TableField("created")
    private String created;

    @TableField("updated")
    private String updated;

}
