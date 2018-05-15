package com.honghe.recordweb.util.base.util;

import java.util.Properties;

public class ServerInfo {
	public static int getProcessors(){
		Properties p=System.getProperties();//获取当前的系统属性
		return Runtime.getRuntime().availableProcessors();
	}
	
	public static void main(String[] args) {
		System.out.println("可用核心数："+getProcessors());
	}
}
