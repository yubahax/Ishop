package com.Ishop.common.vo;

import com.Ishop.common.entity.TbAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Long orderId;

    private BigDecimal orderTotal;

    private TbAddress addressInfo;

    private List<CartProduct> goodsList;

    private String orderStatus;

    private String createDate;

    private String closeDate;

    private String finishDate;

    private String payDate;
}
