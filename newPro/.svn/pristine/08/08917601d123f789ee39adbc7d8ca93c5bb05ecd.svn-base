package com.honghe.recordweb.util;


import java.util.ResourceBundle;


/**
 * 读取配置文件config.properties中的内容
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");	
	/**
	 * 读取配置文件中的value
	 * @param key key值
	 * @return value值
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}		
	public static void main(String[] args) {
		System.out.println(ConfigUtil.get("WEB_ROOT"));
	}

}
