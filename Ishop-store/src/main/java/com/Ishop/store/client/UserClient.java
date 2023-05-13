package com.Ishop.store.client;

import com.Ishop.common.util.util.RestBean;
import com.Ishop.common.util.util.RestGenerator;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/address/getDefaultAddress")
    RestBean getDefaultAddress();



}
