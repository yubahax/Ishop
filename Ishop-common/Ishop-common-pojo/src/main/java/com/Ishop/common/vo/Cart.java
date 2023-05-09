package com.Ishop.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private Long userId;

    private Long productId;

    private int productNum;
}
