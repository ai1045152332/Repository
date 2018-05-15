package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Ftpsetting;


/**
 * Created by hthwx on 2015/3/26.
 */
public interface FtpSettingDao
{
    //获取当前FTP信息
    public Ftpsetting getFtpSetting() throws Exception;
    //添加FTP信息
    public int addFtpSetting(Ftpsetting fs) throws Exception;
    //修改FTP信息
    public void updateFtpSetting(Ftpsetting fs) throws Exception;
}
