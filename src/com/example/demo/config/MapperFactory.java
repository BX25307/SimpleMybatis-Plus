package com.example.demo.config;

/**
 * Mapper 工厂 Bean
 * 用于创建 Mapper 接口的代理对象实例
 * 通过动态代理机制实现 Mapper 接口的方法调用
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 21:26
 */

import java.lang.reflect.Proxy;

public class MapperFactory{

/**
 * 创建指定接口的动态代理实例
 *
 * @param <T> 泛型类型，表示要代理的接口类型
 * @param mapperInterface 需要创建代理的接口Class对象
 * @return 返回创建的动态代理实例，该实例实现了指定的接口
 */
   public static<T> T getProxy(Class<T> mapperInterface) {
    // 使用Proxy类的newProxyInstance方法创建动态代理实例
    // 参数1：指定当前接口的类加载器
    // 参数2：指定需要实现的接口数组，这里只需要传入mapperInterface这一个接口
    // 参数3：绑定一个MpInvocationHandler实例，用于处理代理对象的方法调用
      return (T) Proxy.newProxyInstance(
              mapperInterface.getClassLoader(),
              new Class[]{mapperInterface},
              new MpInvocationHandler(mapperInterface) // 绑定 Handler
      );
   }

}