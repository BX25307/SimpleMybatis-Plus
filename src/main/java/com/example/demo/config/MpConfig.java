package com.example.demo.config;

import com.example.demo.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:27
 */
@Configuration
class MpConfig {
    @Bean
    public MapperFactoryBean<UserMapper> userMapper() {
        return new MapperFactoryBean<>(UserMapper.class);
    }
}