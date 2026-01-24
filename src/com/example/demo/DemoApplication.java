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
		SimpleApplicationContext context = new SimpleApplicationContext(MpConfig.class);
		AddUserHandler addUserHandler = (AddUserHandler)context.getBean("addUserHandler");
		DeleteUserHandler deleteUserHandler = (DeleteUserHandler) context.getBean("deleteUserHandler");
		GetUserHandler getUserHandler = (GetUserHandler) context.getBean("getUserHandler");
		System.out.println("正在初始化web服务器");
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/user/add",addUserHandler);
		server.createContext("/user/delete",deleteUserHandler);
		server.createContext("/user/get",getUserHandler);
		System.out.println("web服务器启动成功");
		server.start();
	}

}
