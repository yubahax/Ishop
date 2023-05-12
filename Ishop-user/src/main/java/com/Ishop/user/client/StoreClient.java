package com.Ishop.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("store-service")
public interface StoreClient {
}
