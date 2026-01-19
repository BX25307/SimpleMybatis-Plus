package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:28
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 接口 1: 查询
    // 访问: http://localhost:8080/user/get?id=1001
    @GetMapping("/user/get")
    public String getUser(@RequestParam Integer id) {
        System.out.println("收到请求 -> 查询 ID: " + id);
        return userMapper.selectById(id); // 自动分发给 SelectProcessor
    }

    // 接口 2: 删除
    // 访问: http://localhost:8080/user/delete?id=888
    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam Integer id) {
        System.out.println("收到请求 -> 删除 ID: " + id);
        return userMapper.deleteById(id); // 自动分发给 DeleteProcessor
    }

    // 接口 3: 插入
    // 访问: http://localhost:8080/user/add
    @GetMapping("/user/add")
    public String addUser() {
        System.out.println("收到请求 -> 插入用户");
        return userMapper.insert(new User("clj")); // 自动分发给 InsertProcessor
    }
}
