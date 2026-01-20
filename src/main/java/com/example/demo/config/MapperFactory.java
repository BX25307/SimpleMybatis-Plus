package com.example.demo.config;

/**
 * Mapper 工厂 Bean
 * 用于创建 Mapper 接口的代理对象实例
 * 通过动态代理机制实现 Mapper 接口的方法调用
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 21:26
 */

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

public class MapperFactory{

   public static<T> T getProxy(Class<T> mapperInterface) {
      return (T) Proxy.newProxyInstance(
              mapperInterface.getClassLoader(),
              new Class[]{mapperInterface},
              new MpInvocationHandler(mapperInterface) // 绑定 Handler
      );
   }

}