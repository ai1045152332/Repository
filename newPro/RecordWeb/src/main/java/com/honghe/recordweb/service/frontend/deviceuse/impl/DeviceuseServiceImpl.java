package com.honghe.recordweb.service.frontend.deviceuse.impl;

import com.honghe.recordhibernate.dao.DeviceUseDao;
import com.honghe.recordhibernate.entity.DeviceUse;
import com.honghe.recordweb.service.frontend.deviceuse.DeviceuseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyx on 2015-08-15.
 */
@Service
@Transactional
public class DeviceuseServiceImpl implements DeviceuseService {

    Logger logger = Logger.getLogger(DeviceuseServiceImpl.class);

    @Resource
    private DeviceUseDao deviceUseDao;

    /**
     * 保存设备使用情况
     *
     * @param deviceuse
     * @return
     */
    @Override
    public boolean addDeviceuse(DeviceUse deviceuse) {
        try {
            return deviceUseDao.saveDevice(deviceuse);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("",e);
            return false;
        }
    }

    /**
     * 获取软件活跃度排名
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Object[]> getRanking(String startTime, String endTime) {

        return deviceUseDao.getRanking(startTime, endTime);
    }
}
