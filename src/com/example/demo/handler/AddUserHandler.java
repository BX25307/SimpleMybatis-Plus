package com.example.demo.handler;

import com.example.demo.aop.SimpleAutowired;
import com.example.demo.aop.SimpleController;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

/**
 * @Author bx25 小陈
 * @Date 2026/1/24 13:06
 */
public class AddUserHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> param = parseQueryParam(exchange.getRequestURI().getQuery());
        String name = param.get("name");

        String res;
        if(name==null){
            res="缺少name参数!!!";
        }else{
            res=userMapper.insert(new User(name));
        }
        sendResponse(exchange,res);
    }
}
