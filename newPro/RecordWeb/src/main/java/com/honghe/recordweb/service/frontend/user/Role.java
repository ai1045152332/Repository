package com.honghe.recordweb.service.frontend.user;

/**
 * Created by lch on 2015/7/9.
 */
public enum Role {
    Role_SchoolManager("校园管理员"),Role_HhtcManger("大屏管理员"),Role_HhrecManager("录播设备管理员"),Role_Viewclass("巡课人员"),
    Role_Dmanager("设备控制人员"),Role_ProjectorManger("投影机管理员"),Role_SystemManager("录播系统管理员"),Role_HhtwbManager("白板管理员"),
    Role_HhtwbControl("白板设备控制人员");
    private String name;

    Role(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}