package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.FtpSettingDao;
import com.honghe.recordhibernate.entity.Ftpsetting;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hthwx on 2015/3/26.
 */
@Repository
public class FtpSettingDaoImpl extends BaseDao implements FtpSettingDao
{
    /**
     * 修改FTP信息
     * @param fs
     * @throws Exception
     */
    @Override
    public void updateFtpSetting(Ftpsetting fs) throws Exception {
        this.getHibernateTemplate().update(fs);
    }

    /**
     * 添加FTP信息
     * @param fs
     * @return
     * @throws Exception
     */
    @Override
    public int addFtpSetting(Ftpsetting fs) throws Exception {
        return (int) this.getHibernateTemplate().save(fs);
    }

    /**
     * 获取当前FTP信息
     * @return
     * @throws Exception
     */
    @Override
    public Ftpsetting getFtpSetting() throws Exception {
        List<Ftpsetting> list = (List<Ftpsetting>) this.getHibernateTemplate().find(" from Ftpsetting h");
        if(list == null || list.isEmpty()) {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }
}
