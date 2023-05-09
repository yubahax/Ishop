package com.Ishop.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Exrickx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private Long productId;

    private BigDecimal salePrice;

    private String productName;

    private String subTitle;

    private String productImageBig;

}
