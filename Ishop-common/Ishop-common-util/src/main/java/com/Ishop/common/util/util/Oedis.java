package com.Ishop.common.util.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class Oedis {
    Config config;
    RedissonClient redisson;
    public Oedis() {
        this.config = new Config();
        config.useClusterServers()
                .addNodeAddress("127.0.0.1:7000")
                .addNodeAddress("127.0.0.1:7001")
                .addNodeAddress("127.0.0.1:7002")
                .addNodeAddress("127.0.0.1:6001")
                .addNodeAddress("127.0.0.1:6002")
                .addNodeAddress("127.0.0.1:6003");
        this.redisson = Redisson.create(config);
    }


}
