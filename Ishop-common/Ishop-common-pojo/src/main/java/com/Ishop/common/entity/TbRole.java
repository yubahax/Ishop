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
public class TbRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

//    private Integer[] roles;
}
