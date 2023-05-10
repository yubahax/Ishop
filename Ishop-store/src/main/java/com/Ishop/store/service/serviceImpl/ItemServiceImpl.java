package com.Ishop.store.service.serviceImpl;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbItemDesc;
import com.Ishop.common.vo.PageItem;
import com.Ishop.common.vo.Product;
import com.Ishop.store.mapper.ItemDescMapper;
import com.Ishop.store.mapper.ItemMapper;
import com.Ishop.store.service.ItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;
    @Resource
    ItemDescMapper itemDescMapper;
    @Override
    public PageItem getAllItem(int num,int size,String sort,int priceGe,int priceLe) {
        if(priceGe == -1)
            priceGe = Integer.MIN_VALUE;
        if(priceLe == -1)
            priceLe = Integer.MAX_VALUE;
        boolean condition = false;
        boolean s = false;
        if(!sort.equals(""))
            condition = true;
        if(sort.equals("Asc"))
            s = true;
        Page<TbItem> tbItemPage = new Page<>(num,size);
        QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
        tbItemQueryWrapper
                .ge("price",priceGe)
                .le("price",priceLe)
                .orderBy(condition,s,"price");
        itemMapper.selectPage(tbItemPage,tbItemQueryWrapper);
        PageItem pageItem = new PageItem();
        pageItem.setTotal(tbItemPage.getTotal());
        pageItem.setTbItemList(tbItemPage.getRecords());
        return pageItem;
    }

    @Override
    public Product getItemDesc(int itemId) {
        Product product = new Product();
        TbItem tbItem = itemMapper.selectById(itemId);
        TbItemDesc tbItemDesc = itemDescMapper.selectById(itemId);
        product.setProductId(tbItem.getId());
        product.setSalePrice(tbItem.getPrice());
        product.setProductName(tbItem.getTitle());
        product.setSubTitle(tbItem.getSellPoint());
        product.setProductImageBig(tbItemDesc.getItemDesc());
        return product;
    }
}
