package com.Ishop.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountOrderItem implements Serializable {


    private String itemId;

    private Integer num;

    private String title;

    private BigDecimal totalPrice;

}
