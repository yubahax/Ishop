package com.Ishop.store.config;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application-alipay.properties")
@ConfigurationProperties(prefix = "alipay") //前缀
@Data
public class AliPayConfig {
    private String appId;
    private String privateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String returnUrl;
    private String charset;
    private String signType;
    private String gatewayUrl;
}

