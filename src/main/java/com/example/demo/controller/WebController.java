package com.example.demo.controller;

import com.example.demo.aop.SimpleAutowired;
import com.example.demo.config.MpConfig;
import com.example.demo.config.SimpleApplicationContext;
import com.example.demo.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/user")
@RestController
public class WebController {
    @SimpleAutowired
    private UserController userController;
    @PostConstruct
    public void init(){
        System.out.println("========== Spring Boot 启动，正在加载手写 IOC 容器 ==========");
        SimpleApplicationContext container = new SimpleApplicationContext(MpConfig.class);
        System.out.println("========== 手写 IOC 容器加载完毕 ==========");
        this.userController=(UserController)container.getBean("userController");
    }

    // 测试插入: http://localhost:8080/user/add
    @GetMapping("/add")
    public String add(@RequestParam String name) {
        return userController.addUser(name);
    }

    // 测试查询: http://localhost:8080/user/get?id=100
    @GetMapping("/get")
    public String get(@RequestParam Integer id) {
        return userController.getUser(id);
    }
    // 测试删除: http://localhost:8080/user/delete?id=100
    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        return userController.deleteUser(id);
    }
}
