package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Initialization;

import java.util.List;

/**
 * Created by lxy on 2016/7/6.
 */

public interface InitializationDao {

    //获取初始化信息列表
    public List getInitializationList()throws Exception;

    public Initialization getInitializationListbytype(String type)throws Exception;

    public boolean updateInitializationInfo(Initialization initialization)throws Exception;
    //获取设备通道信息
    public List getChannel(String devicetype)throws Exception;
}
