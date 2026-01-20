package com.example.demo.config;

import com.example.demo.aop.SimpleBean;
import com.example.demo.aop.SimpleConfiguration;
import com.example.demo.aop.SimpleController;
import com.example.demo.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类
 * 用于配置和注册 Mapper 接口的 Bean
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 21:27
 */
@SimpleConfiguration
public class MpConfig {
    @SimpleBean
    public UserMapper userMapper() {
        return MapperFactory.getProxy(UserMapper.class);
    }
}