package com.example.demo.processor;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 22:12
 */
public interface SqlProcessor {
    // 不需要 JDBC 了，直接模拟返回 String 结果
    String handle(String tableName, Object[] args);
}