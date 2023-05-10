package com.Ishop.common.vo;

import com.Ishop.common.entity.TbItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageItem implements Serializable {
    private long total;
    private List<TbItem> tbItemList;
}
