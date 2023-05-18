package com.Ishop.store.service.serviceImpl;

import com.Ishop.common.entity.TbItem;
import com.Ishop.common.entity.TbItemDesc;
import com.Ishop.common.util.util.BloomFilterHelper;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.common.vo.PageItem;
import com.Ishop.common.vo.Product;
import com.Ishop.store.mapper.ItemDescMapper;
import com.Ishop.store.mapper.ItemMapper;
import com.Ishop.store.service.ItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    ItemMapper itemMapper;
    @Resource
    ItemDescMapper itemDescMapper;

    @Resource
    Yedis yedis;

    @Resource
    BloomFilterHelper bloomFilterHelper;

    private static final String ITEM_NAME = "bitem";

    private static final int PAGE_SIZE = 10;

    @Override
    public PageItem getAllItem(int num,String sort,int priceGe,int priceLe) {
        if(priceGe == 0 && priceLe == 0)
        {
            priceGe = Integer.MIN_VALUE;
            priceLe = Integer.MAX_VALUE;
        }
        boolean condition = false;
        boolean s = false;
        if(!sort.equals(""))
            condition = true;
        if(sort.equals("Asc"))
            s = true;
        Page<TbItem> tbItemPage = new Page<>(num,PAGE_SIZE);
        QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
        tbItemQueryWrapper
                .between("price",priceGe,priceLe)
                .orderBy(condition,s,"price");
        itemMapper.selectPage(tbItemPage,tbItemQueryWrapper);
        PageItem pageItem = new PageItem();
        pageItem.setTotal(tbItemPage.getTotal());
        pageItem.setTbItemList(tbItemPage.getRecords());
        return pageItem;
    }

    @Override
    public PageItem getItemList(int num,String searchItem,String minDate,String maxDate) {
        Page<TbItem> tbItemPage = new Page<>(num,PAGE_SIZE);
        QueryWrapper<TbItem> tbItemQueryWrapper = new QueryWrapper<>();
        tbItemQueryWrapper
                .between("created",minDate,maxDate)
                .like("title",searchItem)
                .or()
                .like("sell_point",searchItem)
                .or()
                .like("price",searchItem);
        itemMapper.selectPage(tbItemPage,tbItemQueryWrapper);
        PageItem pageItem = new PageItem();
        pageItem.setTotal(tbItemPage.getTotal());
        pageItem.setTbItemList(tbItemPage.getRecords());
        return pageItem;
    }

    @Override
    public Product getItemDesc(int itemId) {
        if (!yedis.includeByBloomFilter(bloomFilterHelper,ITEM_NAME,itemId)) {
            return null;
        }
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

    @Override
    public boolean addItem(TbItem tbItem) {
        tbItem.setStatus(0);
        tbItem.setCreated(TimeUtil.getTime());
        tbItem.setUpdated(TimeUtil.getTime());
        boolean flag = itemMapper.insert(tbItem) == 1;
        yedis.addByBloomFilter(bloomFilterHelper,ITEM_NAME,tbItem.getId());
        return flag;
    }

    @Override
    public boolean updateStatus(int id,int status) {
        if (!yedis.includeByBloomFilter(bloomFilterHelper,ITEM_NAME,id)) {
            return false;
        }
        UpdateWrapper<TbItem> tbItemUpdateWrapper = new UpdateWrapper<>();
        tbItemUpdateWrapper
                .eq("id",id)
                .set("status",status);
        return (itemMapper.update(null,tbItemUpdateWrapper)>=1);
    }

    @Override
    public boolean delItem(int id) {
        if (!yedis.includeByBloomFilter(bloomFilterHelper,ITEM_NAME,id)) {
            return false;
        }
        return (itemMapper.deleteById(id)>=1);
    }

    @Override
    public boolean delItemList(List<Integer> ids) {
        for (int i:ids) {
            if (!yedis.includeByBloomFilter(bloomFilterHelper, ITEM_NAME, i)) {
                return false;
            }
        }
        return (itemMapper.deleteBatchIds(ids)>=1);
    }

    @Override
    public boolean updateItem(TbItem tbItem) {
        if (!yedis.includeByBloomFilter(bloomFilterHelper,ITEM_NAME,tbItem.getId())) {
            return false;
        }
        return ((itemMapper.updateById(tbItem))>=1);
    }

    @PostConstruct
    public void initItemBloom() {
        List<TbItem> tbItems  = itemMapper.selectList(new QueryWrapper<TbItem>().select("id"));
        tbItems.forEach(a -> yedis.addByBloomFilter(bloomFilterHelper,ITEM_NAME,a.getId()));
    }

    @Scheduled(cron = "0 0 12 ? * 4")
    public void reflushItemBloom() {
        List<TbItem> tbItems  = itemMapper.selectList(new QueryWrapper<TbItem>().select("id"));
        yedis.del(ITEM_NAME);
        tbItems.forEach(a -> yedis.addByBloomFilter(bloomFilterHelper,ITEM_NAME,a.getId()));
    }
}
