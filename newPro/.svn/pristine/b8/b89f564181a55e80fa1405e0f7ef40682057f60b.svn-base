package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.SettingDao;
import com.honghe.recordhibernate.entity.Setting;
import org.springframework.stereotype.Repository;

/**
 * Created by zhanghongbin on 2014/11/11.
 */
@Repository
public class SettingDaoImpl extends BaseDao implements SettingDao {

    /**
     * 修改设置
     * @param setting
     * @throws Exception
     */
    @Override
    public void updateSetting(Setting setting) throws Exception {
        this.getHibernateTemplate().update(setting);
    }

    /**
     * 获取设置
     * @return
     * @throws Exception
     */
    @Override
    public Setting getSetting() throws Exception {
        return this.getHibernateTemplate().get(Setting.class, 1);
    }

    /**
     * 保存设置
     * @param setting
     * @throws Exception
     */
    @Override
    public void saveSetting(Setting setting) throws Exception {
        this.getHibernateTemplate().save(setting);
    }
}
