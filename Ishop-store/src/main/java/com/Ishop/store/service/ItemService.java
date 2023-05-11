package com.Ishop.store.service;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbItemDesc;
import com.Ishop.common.vo.PageItem;
import com.Ishop.common.vo.Product;

import java.util.List;

public interface ItemService {

    /**
     * @Description分页获取全部商品列表，按价格排序，可查询指定区间价格商品
     * @Param num当前页
     * @Param size每页大小
     * @Param sort排序方式 Asc or Des
     * @Param priceGe 左边价格
     * @Param PriceLe 右边价格
     * @Return {@link List< PageItem>}
     */
    PageItem getAllItem(int num,int size,String sort,int priceGe,int PriceLe);

    /**
     * @Description 获取商品详情
     * @Param itemId 商品id
     * @Return {@link Product}
     */
    Product getItemDesc(int itemId);

}
