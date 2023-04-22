package com.Ishop.security.filter;



import com.Ishop.common.util.RedisUtils;
import com.Ishop.common.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证过滤器
 * @author bing_  @create 2022/1/5-14:12
 * 继承 OncePerRequestFilter 保证请求经过过滤器一次
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 token ( 前端，用户登录后，将 token 放到请求头当中。所以这里从请求头中获取 token )
        String token = request.getHeader("token");

        if (!StringUtils.hasText(token)) {
            // 如果请求头没有 token ，放行，就不需要后面的操作。之后的过滤器就会拦截
            filterChain.doFilter(request, response);
            System.out.println("没有token");
            //必须要return，因为等请求转回来之后，不return还会执行下面的对token的操作
            return;
        }

        // token 不为空，解析 token
        String uesrId;
        try {
            Claims claims = TokenUtil.getTokenBody(token);
            uesrId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非法 token");
        }
        // 从 redis 中获取用户信息
        String redisKey = "login:" + uesrId;

        UserDetails user = (UserDetails) redisUtils.get(redisKey);

        if (Objects.isNull(user)) {
            throw new RuntimeException("用户未登录");
        }
        // 将用户信息存入 SecurityContextHolder
        // 获取权限信息封装到 Authentication 中
        // 参数：用户信息、已认证状态、权限信息
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }
}

