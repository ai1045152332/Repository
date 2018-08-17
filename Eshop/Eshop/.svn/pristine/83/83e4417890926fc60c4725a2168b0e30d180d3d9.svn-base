package com.shop.filter.json;

import java.util.Set;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class ComplexPropertyPreFilter implements PropertyFilter {
    private Set<String> excludes = null;
    //返回true则该属性进行序列号，否则忽略
    public boolean apply(Object object, String name, Object value) {
    	if(excludes!=null&&excludes.contains(name)) {
    		return false;
    	}
		return true;
    }
	public Set<String> getExcludes() {
		return excludes;
	}
	public void setExcludes(Set<String> excludes) {
		this.excludes = excludes;
	}
    
}
