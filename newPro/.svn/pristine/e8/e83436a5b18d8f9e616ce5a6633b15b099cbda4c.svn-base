package com.honghe.recordhibernate.entity.tools;

import java.util.ResourceBundle;


/**
 * 读取配置文件config.properties,jdbc.properies中的内容
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static final ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");
	/**
	 * 读取配置文件中的value
	 * @param key key值
	 * @return value值
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}
	
	public static String getJdbcConfig(String key){
		return jdbcBundle.getString(key);
	}
	
	public static void main(String[] args) {

	}

}
