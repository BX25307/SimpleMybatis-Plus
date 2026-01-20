package com.example.demo.controller;

import com.example.demo.aop.SimpleAutowired;
import com.example.demo.aop.SimpleController;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 提供 User 相关的 REST API 接口
 * 包含查询、删除、插入等基本操作
 * 
 * @Author bx25 小陈
 * @Date 2026/1/19 21:28
 */
@SimpleController
public class UserController {

    @SimpleAutowired
    private UserMapper userMapper;


    public String getUser(Integer id) {
        System.out.println("收到请求 -> 查询 ID: " + id);
        return userMapper.selectById(id); // 自动分发给 SelectProcessor
    }


    public String deleteUser(@RequestParam Integer id) {
        System.out.println("收到请求 -> 删除 ID: " + id);
        return userMapper.deleteById(id); // 自动分发给 DeleteProcessor
    }

    public String addUser(@RequestParam String name) {
        System.out.println("收到请求 -> 插入用户");
        return userMapper.insert(new User(name)); // 自动分发给 InsertProcessor
    }
}
