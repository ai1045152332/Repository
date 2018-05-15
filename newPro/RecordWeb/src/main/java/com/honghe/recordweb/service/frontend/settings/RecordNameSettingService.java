package com.honghe.recordweb.service.frontend.settings;

import com.honghe.recordhibernate.dao.RecordNameSettingDao;
import com.honghe.recordhibernate.entity.RecordNameSetting;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by honghe on 2016/9/13.
 */

/**
 * 设置录像命名模式
 */
@Service
public class RecordNameSettingService {
    private final static Logger logger = Logger.getLogger(RecordNameSettingService.class);
    @Resource
    private RecordNameSettingDao recordNameSettingDao;

    /**
     * 获取设置
     * Author xinye
     * @return
     */
    public RecordNameSetting getSetting(){
        try{
            List<RecordNameSetting> list = recordNameSettingDao.getRecordNameSetting();
           return list!=null&&!list.isEmpty()?list.get(0):null;
        }catch(Exception e){
            logger.error("",e);
            return null;
        }
    }

    /**
     * 保存设置
     * Author xinye
     * @param subject 科目名称
     * @param teacher 教师姓名
     * @param classroom 教室名称
     * @return
     */
    @Transactional
    public boolean setRecordNameSetting(boolean subject,boolean teacher,boolean classroom){
        try{
            recordNameSettingDao.setRecordName(subject,teacher,classroom);
            return true;
        }catch(Exception e){
            logger.error("",e);
            return false;
        }
    }
}
