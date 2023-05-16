package com.Ishop.business.client;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("store-service")
public interface StoreClient {


    @GetMapping("/orderitem/getDayOrderItem")
    RestBean getDayOrderItem();
    @GetMapping("/orderitem/getWeekOrderItem")
    RestBean getWeekOrderItem();

    @GetMapping("/orderitem/getMonthOrderItem")
    RestBean getMonthOrderItem();





}
