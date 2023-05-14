package com.Ishop.store.service;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbItemDesc;
import com.Ishop.common.vo.PageItem;
import com.Ishop.common.vo.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    /**
     * @Description 分页获取全部商品列表，按价格排序，可查询指定区间价格商品用于前台商城展示
     * @Param size每页大小
     * @Param sort排序方式 Asc or Des
     * @Param priceGe 左边价格
     * @Param PriceLe 右边价格
     * @Return {@link List< PageItem>}
     */
    PageItem getAllItem(int num,String sort,int priceGe,int PriceLe);

    /**
     * @Description后台分页获取全部商品列表,可按创建时间和商品名称、商品描述、商品价格进行查询
     * @Param searchItem模糊查询3个条件
     * @Param minDate起始时间
     * @Param maxDate终止时间
     * @Return {@link PageItem}
     */
    PageItem getItemList(int num,String searchItem,String minDate,String maxDate);

    /**
     * @Description 前台全部商品页面中点击某个商品查看详情,获取商品详情
     * @Param itemId 商品id
     * @Return {@link Product}
     */
    Product getItemDesc(int itemId);

    /**
     * @Description 后台添加商品操作,添加商品后其状态为未发布
     * @Param tbItem
     * @Return {@link boolean}
     */
    boolean addItem(TbItem tbItem);

    /**
     * @Description 后台点击发布/下架商品,商品状态变更
     * @Param id
     * @Param status
     * @Return {@link boolean}
     */
    boolean updateStatus(int id,int status );

    /**
     * @Description 后台删除单个商品
     * @Param id
     * @Return {@link boolean}
     */
    boolean delItem(int id);

    /**
     * @Description 后台批量删除商品
     * @Param ids
     * @Return {@link boolean}
     */
    boolean delItemList(List<Integer> ids);

    /**
     * @Description 后台修改商品信息
     * @Param tbItem
     * @Return {@link boolean}
     */
    boolean updateItem(TbItem tbItem);
}
