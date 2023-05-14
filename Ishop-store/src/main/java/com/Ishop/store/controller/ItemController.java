package com.Ishop.store.controller;


import com.Ishop.common.entity.TbItem;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.store.service.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    ItemService itemService;
    @GetMapping("/getAllItem")
    public RestBean getAllItem(@RequestParam(defaultValue = "1") int num,
                               @RequestParam(defaultValue = "") String sort,
                               @RequestParam(defaultValue = "0") int priceGe,
                               @RequestParam(defaultValue = "0") int priceLe){
        if(!ParamVail.vailNumber(num,priceGe,priceLe) && ParamVail.vailString(sort)){
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(itemService.getAllItem(num,sort,priceGe,priceLe));
    }

    @GetMapping("getItemList")
    public RestBean getItemList(@RequestParam(defaultValue = "1") int num,
                                @RequestParam("searchItem") String searchItem,
                                @RequestParam("minDate") String minDate,
                                @RequestParam("maxDate") String maxDate){
        if(!ParamVail.vailNumber(num) && ParamVail.vailString(searchItem,minDate,maxDate)){
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(itemService.getItemList(num,searchItem,minDate,maxDate));
    }

    @GetMapping("/getItemDesc")
    public RestBean getItem(@RequestParam("itemId") int itemId){
        if(!ParamVail.vailNumber(itemId)){
            return RestGenerator.errorResult("非法参数");
        }
        return RestGenerator.successResult(itemService.getItemDesc(itemId));
    }

    @PostMapping ("/addItem")
    public RestBean addItem(@RequestBody TbItem tbItem){
        if(ParamVail.isNull(tbItem)){
            return RestGenerator.errorResult("非法参数");
        }
        return itemService.addItem(tbItem)?RestGenerator.successResult("添加成功"):RestGenerator.errorResult("添加失败");
    }

    @PostMapping ("/updateStatus")
    public RestBean updateStatus(@RequestParam("id") int id,
                                 @RequestParam("status") int status){
        if(!ParamVail.vailNumber(id,status)){
            return RestGenerator.errorResult("非法参数");
        }
        return itemService.updateStatus(id,status)?RestGenerator.successResult("发布成功"):RestGenerator.errorResult("发布失败");

    }

    @PostMapping("/delItem")
    public RestBean delItem(@RequestParam("id")int id){
        if(!ParamVail.vailNumber(id)){
            return RestGenerator.errorResult("非法参数");
        }
        return itemService.delItem(id)?RestGenerator.successResult("删除成功"):RestGenerator.errorResult("删除失败");
    }

    @PostMapping("/delItemList")
    public RestBean delItemList(@RequestParam("ids") List<Integer> ids){
        if(ParamVail.isNull(ids)){
            return RestGenerator.errorResult("非法参数");
        }
        return itemService.delItemList(ids)?RestGenerator.successResult("删除成功"):RestGenerator.errorResult("删除失败");
    }

}
