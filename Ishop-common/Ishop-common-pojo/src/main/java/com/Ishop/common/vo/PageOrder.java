package com.Ishop.common.vo;

import com.Ishop.common.entity.TbOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageOrder implements Serializable {
    private int total;
    private List<Order> tbOrderList;
}
