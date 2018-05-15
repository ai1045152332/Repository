package com.honghe.recordweb.service.frontend.deviceuse;

import com.honghe.recordhibernate.entity.DeviceUse;

import java.util.List;

/**
 * Created by lyx on 2015-08-15.
 */
public interface DeviceuseService {

    /**
     * 保存设备使用情况
     *
     * @param deviceuse
     * @return
     */
    public boolean addDeviceuse(DeviceUse deviceuse);


    /**
     * 获取软件活跃度排名
     *
     * @return
     */
    public List<Object[]> getRanking(String startTime, String endTime);
}
