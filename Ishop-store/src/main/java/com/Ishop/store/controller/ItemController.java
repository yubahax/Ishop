package com.Ishop.store.controller;


import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import com.Ishop.store.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    ItemService itemService;
    @GetMapping("/getAllItem")
    public RestBean getAllItem(@RequestParam(defaultValue = "1") int num,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "") String sort,
                               @RequestParam(defaultValue = "-1") int priceGt,
                               @RequestParam(defaultValue = "-1") int priceLe){
       return RestGenerator.successResult(itemService.getAllItem(num,size,sort,priceGt,priceLe));
    }
    @GetMapping("/getItemDesc")
    public RestBean getItem(@RequestParam("itemId") int itemId){
        return RestGenerator.successResult(itemService.getItemDesc(itemId));
    }
}
