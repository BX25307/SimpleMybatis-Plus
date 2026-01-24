package com.example.demo.mapper;

import java.io.Serializable;

/**
 * 基础 Mapper 接口
 * 定义通用的 CRUD 操作方法
 * 所有 Mapper 接口应继承此接口以获得基础功能
 * 
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
