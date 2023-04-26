package com.Ishop.security.config;

import com.Ishop.security.filter.JwtAuthenticationTokenFilter;
import com.Ishop.security.service.serviceImpl.UserAuthService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class SecurityConfigation extends WebSecurityConfigurerAdapter {
    @Resource
    UserAuthService userAuthService;

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers().authenticated()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .loginProcessingUrl("/do")
                .permitAll();    //使用表单登录

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean("ok")
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(new BCryptPasswordEncoder());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth
//                .inMemoryAuthentication()   //直接创建一个用户，懒得搞数据库了
//                .passwordEncoder(encoder)
//                .withUser("test").password(encoder.encode("123456")).roles("USER");
    }

    @Bean   //这里需要将AuthenticationManager注册为Bean，在OAuth配置中使用
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("yuba")
    public JwtAccessTokenConverter tokenConverter(){  //Token转换器，将其转换为JWT
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("lbwnb");   //这个是对称密钥，一会资源服务器那边也要指定为这个
        return converter;
    }

    @Bean
    public TokenStore tokenStore(@Qualifier("yuba") JwtAccessTokenConverter converter){  //Token存储方式现在改为JWT存储
        return new JwtTokenStore(converter);  //传入刚刚定义好的转换器
    }

}

