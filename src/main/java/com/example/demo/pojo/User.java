package com.example.demo.pojo;

import com.example.demo.aop.TableName;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:28
 */
// --- 业务实体 ---
@TableName("sys_user") // 告诉 MP 表名是 sys_user
public class User {
    private String name;
    public User(String name) { this.name = name; }
    // 省略 getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{name=" + name+"}";
    }
}