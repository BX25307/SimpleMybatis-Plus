package com.example.demo.processor;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 22:12
 */
public class SelectProcessor implements SqlProcessor {
    @Override
    public String handle(String tableName, Object[] args) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = " + args[0];
        System.out.println("模拟执行" + sql);
        // 模拟返回
        return "模拟查询成功：在表 " + tableName + " 中找到了 ID=" + args[0] + " 的数据";
    }
}