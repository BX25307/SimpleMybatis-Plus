package com.example.demo.processor;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 22:13
 */
public class InsertProcessor implements SqlProcessor {
    @Override
    public String handle(String tableName, Object[] args) {
        // 简单模拟，不解析字段了
        String sql = "INSERT INTO " + tableName + " VALUES (" + args[0] + ")";
        System.out.println(">>> [模拟执行] " + sql);
        return "模拟插入成功：对象 " + args[0].getClass().getSimpleName() + " 已入库";
    }
}