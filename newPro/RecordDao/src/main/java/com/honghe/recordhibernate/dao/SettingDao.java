package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Setting;

/**
 * Created by zhanghongbin on 2014/11/11.
 */
public interface SettingDao {

    public Setting getSetting()throws Exception;

    public void updateSetting(Setting setting)throws Exception;

    public void saveSetting(Setting setting)throws Exception;

}
