package com.example.demo.handler;

import com.example.demo.pojo.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

/**
 * @Author bx25 小陈
 * @Date 2026/1/24 13:07
 */
public class GetUserHandler extends AbstractHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> param = parseQueryParam(exchange.getRequestURI().getQuery());
        String idStr = param.get("id");

        String res;
        if(idStr==null){
            res="缺少id参数!!!";
        }else{
            try{
                Integer id = Integer.valueOf(idStr);
                res=userMapper.selectById(id);
            }catch (Exception e){
                res="id必须是数字!!!";
            }
        }
        sendResponse(exchange,res);
    }
}
