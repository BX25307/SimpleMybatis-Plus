package com.example.demo.config;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:26
 */

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

public class MapperFactoryBean<T> implements FactoryBean<T> {
   private Class<T> mapperInterface;
   public MapperFactoryBean(Class<T> mapperInterface) { this.mapperInterface = mapperInterface; }

   @Override
   public T getObject() {
      return (T) Proxy.newProxyInstance(
              mapperInterface.getClassLoader(),
              new Class[]{mapperInterface},
              new MpInvocationHandler(mapperInterface) // 绑定 Handler
      );
   }
   @Override
   public Class<?> getObjectType() { return mapperInterface; }
}