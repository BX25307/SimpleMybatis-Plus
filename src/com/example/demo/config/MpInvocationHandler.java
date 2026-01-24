package com.example.demo.config;

/**
 * MyBatis-Plus 动态代理调用处理器
 * 实现动态代理的核心逻辑，负责拦截 Mapper 接口的方法调用
 * 使用策略模式根据方法名选择对应的 SQL 处理器
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 21:26
 */

import com.example.demo.aop.TableName;
import com.example.demo.processor.DeleteProcessor;
import com.example.demo.processor.InsertProcessor;
import com.example.demo.processor.SelectProcessor;
import com.example.demo.processor.SqlProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MpInvocationHandler implements InvocationHandler {
    private String tableName;

    // 【核心】策略地图：存放 "方法名" -> "处理器" 的映射
    // 以后要加新功能，只需要在这里 put 新的处理器，不需要改 invoke 代码
    private static final Map<String, SqlProcessor> strategyMap = new HashMap<>();

    static {
        strategyMap.put("selectById", new SelectProcessor());
        strategyMap.put("insert",     new InsertProcessor());
        strategyMap.put("deleteById", new DeleteProcessor());
    }

    public MpInvocationHandler(Class<?> mapperInterface) {
        // 1. 解析泛型 T
        Class<?> entityClass = null;
        for (Type type : mapperInterface.getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                entityClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
                break;
            }
        }
        // 2. 解析表名
        if (entityClass != null && entityClass.isAnnotationPresent(TableName.class)) {
            this.tableName = entityClass.getAnnotation(TableName.class).value();
        } else {
            this.tableName = "unknown_table";
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) return method.invoke(this, args);

        // 1. 拿到方法名 (例如 "deleteById")
        String methodName = method.getName();

        // 2. 查表找策略
        SqlProcessor processor = strategyMap.get(methodName);

        if (processor == null) {
            return "报错：方法 " + methodName + " 还没开发！";
        }

        // 3. 执行策略
        return processor.handle(this.tableName, args);
    }
}