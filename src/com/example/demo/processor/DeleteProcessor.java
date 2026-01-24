package com.example.demo.processor;

/**
 * 删除操作处理器
 * 负责生成并执行删除数据的 SQL 语句
 * 根据表名和 ID 参数构造 DELETE 语句
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 22:13
 */
public class DeleteProcessor implements SqlProcessor {
    @Override
    public String handle(String tableName, Object[] args) {
        String sql = "DELETE FROM " + tableName + " WHERE id = " + args[0];
        System.out.println("模拟执行" + sql);
        return "模拟删除成功：ID=" + args[0] + " 已被移除";
    }
}