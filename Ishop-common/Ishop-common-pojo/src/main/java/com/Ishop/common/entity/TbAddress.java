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
public class TbAddress implements Serializable {
    @TableId(type = IdType.AUTO)
    @TableField("address_id")
    private Long addressId;

    @TableField("user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("tel")
    private String tel;

    @TableField("street_name")
    private String streetName;

    @TableField("is_default")
    private Integer isDefault;
}
