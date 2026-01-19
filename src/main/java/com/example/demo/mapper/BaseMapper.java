package com.example.demo.mapper;

import java.io.Serializable;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:24
 */
public interface BaseMapper<T> {
    // 策略1: 查询
    String selectById(Serializable id);
    // 策略2: 插入
    String insert(T entity);
    // 策略3: 删除
    String deleteById(Serializable id);
}
