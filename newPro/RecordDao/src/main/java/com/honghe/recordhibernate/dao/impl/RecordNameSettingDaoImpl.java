package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.RecordNameSettingDao;
import com.honghe.recordhibernate.entity.RecordNameSetting;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by honghe on 2016/9/13.
 */
@Repository
public class RecordNameSettingDaoImpl extends BaseDao implements RecordNameSettingDao {
    private final static Logger logger = Logger.getLogger(RecordNameSettingDaoImpl.class);

    /**
     * 设置录像名称模式，如果数据库中有则修改，反之新建
     * Author xinye
     * @throws Exception
     */
    @Override
    public void setRecordName(boolean subject,boolean teacher,boolean classroom) throws Exception {
        List<RecordNameSetting> list = getRecordNameSetting();
        if(list==null||list.isEmpty()){
            RecordNameSetting recordNameSetting = new RecordNameSetting();
            recordNameSetting.setSubjectName(subject);
            recordNameSetting.setTeacherName(teacher);
            recordNameSetting.setClassRoomName(classroom);
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(recordNameSetting);
        }else{
            RecordNameSetting recordNameSetting = list.get(0);
            recordNameSetting.setSubjectName(subject);
            recordNameSetting.setTeacherName(teacher);
            recordNameSetting.setClassRoomName(classroom);
            this.getHibernateTemplate().update(recordNameSetting);
        }
    }

    /**
     * 查询数据库中记录的录像名称命名模式
     * Author xinye
     * @return
     * @throws Exception
     */
    @Override
    public List<RecordNameSetting> getRecordNameSetting() throws Exception {
        List<RecordNameSetting> recordSettingList = (List<RecordNameSetting>)this.getHibernateTemplate().find("from RecordNameSetting");
        return recordSettingList;
    }
}
