package com.example.demo.processor;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 22:13
 */
public class DeleteProcessor implements SqlProcessor {
    @Override
    public String handle(String tableName, Object[] args) {
        String sql = "DELETE FROM " + tableName + " WHERE id = " + args[0];
        System.out.println(">>> [模拟执行] " + sql);
        return "模拟删除成功：ID=" + args[0] + " 已被移除";
    }
}