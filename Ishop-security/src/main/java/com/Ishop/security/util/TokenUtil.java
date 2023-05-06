package com.Ishop.security.util;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * token工具类的编写
 */
public class TokenUtil {
    //注入配置文件的值

    private static final String SECRET_KEY = "lbwnb";

    private static final long  EXPIRATION_TIME = 1800;

    /**
     * 使用uid，生成token
     *
     */
    public static String generateToken(int uid) {
        Map<String, Object> map = new HashMap<>(2);
        // //内容
//        map.put("username", user.getName());
        map.put("uid",uid);
        map.put("created", new Date());
        return generateJwt(map);

    }

    public static String generateToken(Long uid) {
        Map<String, Object> map = new HashMap<>(2);
        // //内容
//        map.put("username", user.getName());
        map.put("uid",uid);
        map.put("created", new Date());
        return generateJwt(map);

    }

    /**
     * 根据荷载信息生成token
     * @param map
     */
    private static String generateJwt(Map<String, Object> map) {
        return Jwts.builder()
                //生成密文
                // 设置信息
                .setClaims(map)
                //加密密钥
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // token保留的时间 当前时间+ 三个小时
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                .compact();
                //契约
    }

    /**
     * 根据token 获取荷载信息
     *
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token) {

//           SecretKey key = generalKey();
           return Jwts.parser()
//                   .setSigningKey(SECRET_KEY)
                   //解密密钥
                   .parseClaimsJws(token)
                   .getBody();
                   //密文

    }

    /**
     * 根据token信息得到用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameByToken(String token) {
        return (String) getTokenBody(token).get("username");
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 重新生成token
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        Claims claims = getTokenBody(token);
        Objects.requireNonNull(claims).setExpiration(new Date());
        return generateJwt(claims);
    }
}

