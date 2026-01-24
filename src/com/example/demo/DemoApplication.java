package com.example.demo;


import com.example.demo.config.MpConfig;
import com.example.demo.config.SimpleApplicationContext;
import com.example.demo.handler.AddUserHandler;
import com.example.demo.handler.DeleteUserHandler;
import com.example.demo.handler.GetUserHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DemoApplication {

	public static void main(String[] args) throws IOException {
		System.out.println("正在加载手写ioc容器");
		new SimpleApplicationContext(MpConfig.class);
		System.out.println("正在初始化web服务器");
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/user/add",new AddUserHandler());
		server.createContext("/user/delete",new DeleteUserHandler());
		server.createContext("/user/get",new GetUserHandler());
		System.out.println("web服务器启动成功");
		server.start();
	}

}
