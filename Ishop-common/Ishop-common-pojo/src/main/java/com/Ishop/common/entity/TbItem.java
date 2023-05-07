package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TbItem implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("sell_point")
    private String sellPoint;

    @TableField("price")
    private BigDecimal price;

    @TableField("num")
    private int num;

    @TableField("limit_num")
    private int limitNum;

    @TableField("image")
    private String image;

    @TableField("cid")
    private long cid;

    @TableField("status")
    private int status;

    @TableField("created")
    private String created;

    @TableField("updated")
    private String updated;
}
