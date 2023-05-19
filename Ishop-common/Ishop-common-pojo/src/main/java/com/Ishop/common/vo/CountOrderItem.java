package com.Ishop.common.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountOrderItem implements Serializable {

    @ExcelProperty("商品编号")
    private String itemId;

    @ExcelProperty("商品数量")
    private Integer num;
    @ExcelProperty("商品单价")
    private String title;
    @ExcelProperty("商品总价")
    private BigDecimal totalPrice;

}
