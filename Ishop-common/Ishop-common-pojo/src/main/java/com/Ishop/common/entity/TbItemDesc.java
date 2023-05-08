package com.Ishop.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbItemDesc implements Serializable {

    @TableId(type = IdType.NONE)
    private Long itemId;

    @TableField("created")
    private String created;
    @TableField("updated")
    private String updated;
    @TableField("item_desc")

    private String itemDesc;
}
