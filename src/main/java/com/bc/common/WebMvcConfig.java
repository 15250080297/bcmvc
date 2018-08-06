package com.bc.common;

import com.aliyun.openservices.ons.api.*;
import com.bc.common.AuthInterceptor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

/**
 * Created by beijixiong on 2017/6/1.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
    private static final Logger logger= Logger.getLogger(WebMvcConfig.class);

    @Autowired
    private AuthInterceptor authInterceptor;




    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authInterceptor).addPathPatterns("/api/mvc/post","api/mvc/post","/mvc/*");

        super.addInterceptors(registry);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("token","Content-Type","content-type","uid");
        super.addCorsMappings(registry);
    }





}
