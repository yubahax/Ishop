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
public class TbUserCoupon implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("uid")
    private Long uid;

    @TableField("cid")
    private Long cid;

    @TableField("end_time")
    private String endtime;

    @TableField("created")
    private String created;



}
