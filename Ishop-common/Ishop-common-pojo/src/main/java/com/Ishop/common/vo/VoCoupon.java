package com.Ishop.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoCoupon implements Serializable {


    private Long id;
    private Long cid;
    private String type ;
    private int limitnum;
    private int value;
}
