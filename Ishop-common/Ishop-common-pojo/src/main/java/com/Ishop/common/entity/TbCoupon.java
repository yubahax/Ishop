package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbCoupon implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("type")
    private String type ;

    @TableField("limit_num")
    private int limitnum;

    @TableField("value")
    private int value;

    @TableField("total")
    private int total;

    @TableField("active_time")
    private int activetime;

    @TableField("updated")
    private String updated;

    @TableField("created")
    private String created ;


}
