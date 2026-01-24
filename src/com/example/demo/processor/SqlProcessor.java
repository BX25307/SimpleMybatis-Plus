package com.example.demo.processor;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 22:12
 */
public interface SqlProcessor {
    String doApply(String tableName, Object[] args);
}