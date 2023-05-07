package com.Ishop.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct implements Serializable {

    private Long productId;
    //商品id

    private BigDecimal salePrice;
    //合计价格

    private Long productNum;
    //购买数量

    private String productName;
    //名称

    private String productImg;
    //图片
}
