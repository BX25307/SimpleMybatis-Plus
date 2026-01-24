package com.example.demo.processor;

import com.example.demo.pojo.User;

/**
 * 插入操作处理器
 * 负责生成并执行插入数据的 SQL 语句
 * 根据表名和实体对象构造 INSERT 语句
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 22:13
 */
public class InsertProcessor implements SqlProcessor {
    @Override
    public String doApply(String tableName, Object[] args) {
        User user = (User) args[0];
        String userName = user.getName();
        // 简单模拟，不解析字段了
        String sql = "INSERT INTO " + tableName + " VALUES (" + userName + ")";
        System.out.println("模拟执行" + sql);
        return "模拟插入成功：对象 " + args[0].getClass().getSimpleName()+ " 已入库";
    }
}