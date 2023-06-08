package com.Ishop.store.util;

import com.Ishop.store.config.AliPayConfig;
import com.Ishop.store.pojo.Order;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AliPay {

    @Autowired
    private AliPayConfig aliPayConfig;

    public String pay(Order order) throws AlipayApiException {
        //支付网关
        String serverUrl = aliPayConfig.getGatewayUrl();
        // AppId
        String appId = aliPayConfig.getAppId();
        // 用户密钥(私钥)，即PKCS8格式RSA2私钥
        String privateKey = aliPayConfig.getPrivateKey();
        //格式化为json格式
        String format = "json";
        //编码
        String charset = aliPayConfig.getCharset();
        //支付宝公钥，即对应Appid下的支付宝公钥
        String alipayPublicKey = aliPayConfig.getAlipayPublicKey();
        //签名方式
        String signType = aliPayConfig.getSignType();
        //页面跳转同步通知页面路径
        String returnUrl = aliPayConfig.getReturnUrl();
        //服务器异步通知页面路径
        String notifyUrl = aliPayConfig.getNotifyUrl();

        //1、获取初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        //2、设置请求参数
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayTradePagePayRequest.setReturnUrl(returnUrl);
        alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
        //封装参数（json格式）
        alipayTradePagePayRequest.setBizContent(JSON.toJSONString(order));

        //    3、请求支付宝进行付款，并获取支付结果
        String body = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        System.out.println(body);
        //放回信息
        return body;
    }
}
