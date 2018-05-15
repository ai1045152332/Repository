package com.honghe.recordweb.action.frontend.schedule;

import com.honghe.recordweb.action.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;

/**
 * Created by yanxue on 2015-06-23.
 */
public class ScheduleAction<T> extends BaseAction implements ModelDriven<T> {


    // =============== ModelDriven的支持 ==================
    protected T model;

    public  ScheduleAction(){
        try {
            // 通过反射获取model的真实类型
            ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
            // 通过反射创建model的实例
            model = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getModel() {
        return model;
    }
}
