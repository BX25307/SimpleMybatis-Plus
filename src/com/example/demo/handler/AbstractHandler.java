package com.example.demo.handler;

import com.example.demo.aop.SimpleAutowired;
import com.example.demo.aop.SimpleController;
import com.example.demo.mapper.UserMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author bx25 小陈
 * @Date 2026/1/24 13:13
 */
@SimpleController
public abstract class AbstractHandler implements HttpHandler {
    @SimpleAutowired
    protected UserMapper userMapper;
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //缺省方法
    }
    protected Map<String,String> parseQueryParam(String query){
        Map<String,String> res=new HashMap<>();
        if(query==null||query.isEmpty()){
            return res;
        }
        for(String param:query.split("&")){
            String[] entry = param.split("=");
            if(entry.length>1){
                res.put(entry[0],entry[1]);
            }else if(entry.length==1){
                res.put(entry[0],"");
            }
        }
        return res;
    }
    protected void sendResponse(HttpExchange exchange,String responseText) throws IOException{
        byte[] bytes = responseText.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type","text/plain;charset=utf-8");
        exchange.sendResponseHeaders(200,bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
