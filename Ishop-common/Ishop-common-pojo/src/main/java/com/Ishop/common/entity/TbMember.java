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
public class TbMember implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("state")
    private int state;

    @TableField("description")
    private String description;

    @TableField("points")
    private Long points;

    @TableField("starttime")
    private String starttime;

    @TableField("endtime")
    private String endtime;

    @TableField("updated")
    private String updated;

    @TableField("created")
    private String created;

}
