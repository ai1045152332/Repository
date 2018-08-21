package com.zjy.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSession getSqlSession() {
		InputStream inputStream;
		try {
			// 1加载主配置文件
			inputStream = Resources.getResourceAsStream("mybatis.xml");
			if (sqlSessionFactory == null) {
				// 2如果为空创建selsessionFactory对象
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			}
			return sqlSessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
