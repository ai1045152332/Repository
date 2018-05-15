package com.honghe.recordweb.util.base.util;

import java.lang.reflect.ParameterizedType;

/**
 * 
 * @author hucl
 *
 * 范类转换，将泛型转化为对应的类
 */
public class GenericSuperClass {
	@SuppressWarnings("rawtypes")
	public static Class getClass(Class genericClass) {
		ParameterizedType pt = (ParameterizedType) genericClass
				.getGenericSuperclass();
		Class entity = (Class) pt.getActualTypeArguments()[0];
		return entity;
	}
}
