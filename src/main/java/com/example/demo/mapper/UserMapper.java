package com.example.demo.mapper;

import com.example.demo.pojo.User;

/**
 * @Author bx25 小陈
 * @Date 2026/1/19 21:28
 */
// --- 业务 Mapper ---
// 注意：这里没有任何 XML，也没有 @Select 注解
public interface UserMapper extends BaseMapper<User> {
    // 空空如也，全靠继承
}