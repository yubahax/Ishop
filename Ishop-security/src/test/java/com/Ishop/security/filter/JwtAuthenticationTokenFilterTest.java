package com.Ishop.security.filter;

import com.Ishop.security.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import junit.framework.TestCase;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.client.Jackson2ArrayOrStringDeserializer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtAuthenticationTokenFilterTest extends TestCase {


    TokenStore tokenStore;
    public void test(){

    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODI1NDI1NDQsInVzZXJfbmFtZSI6InJvc2UiLCJhdXRob3JpdGllcyI6WyJST0xFX3VzZXIiXSwianRpIjoiZGZkYzY3MGEtMTgzOS00MTA3LWI2ODEtODgyNjMxMjE2YjcxIiwiY2xpZW50X2lkIjoid2ViIiwic2NvcGUiOlsidXNlci1zZXJ2aWNlIiwic2VjdXJpdHktc2VydmljZSIsImJvcnJvdyJdfQ.V7NqVsExDoK7EhAnE4FnjOVI2js0PcWig4h66sgN8SY";
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("lbwnb");   //这个是对称密钥，一会资源服务器那边也要指定为这个

    tokenStore = new JwtTokenStore(converter);
    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        System.out.println(oAuth2AccessToken);


    }

}