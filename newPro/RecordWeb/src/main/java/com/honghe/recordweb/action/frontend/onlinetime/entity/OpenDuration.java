package com.honghe.recordweb.action.frontend.onlinetime.entity;

/**
 * Created by qinzhihui on 2015/12/15.
 * 这只是一个发给页面的实体对象 方便调用
 */
public class OpenDuration {
    //----------------------如有需要 在下方添加属性---------------------------------
    private String name;//返回页面时显示的名称 如高一一班
    private int totalTime;//对应的开机时长
    //-----------------------下面是set get方法-------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
