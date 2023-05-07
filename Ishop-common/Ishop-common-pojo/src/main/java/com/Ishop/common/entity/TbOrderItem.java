package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbOrderItem implements Serializable {
    @TableId(type = IdType.NONE)
    private String id;

    @TableField("item_id")
    private String itemId;

    @TableField("order_id")
    private String orderId;

    @TableField("num")
    private Integer num;

    @TableField("title")
    private String title;

    @TableField("price")
    private BigDecimal price;

    @TableField("total_fee")
    private BigDecimal totalFee;

    @TableField("pic_path")
    private String picPath;

}
