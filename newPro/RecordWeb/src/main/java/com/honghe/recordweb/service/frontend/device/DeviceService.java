package com.honghe.recordweb.service.frontend.device;


import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordhibernate.entity.Device;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;


/**
 * Created by chby on 2014/9/30.
 */
@Service("deviceService")
public class DeviceService {
    private final static Logger logger = Logger.getLogger(DeviceService.class);
    @Resource
    private DeviceDao deviceDao;

    /**
     * 获取镜头
     * @param hostId 主机id
     * @return
     */
    public List<Object[]> getToken(int hostId) {
        try {
            return deviceDao.getToken(hostId);
        } catch (Exception e) {
            logger.info("镜头不存在,hostId=" + hostId, e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 获取镜头列表
     * @param hostId 主机id
     * @return
     */
    public List<Object[]> getDeviceListService(int hostId) {
        List<Object[]> deviceList;
        try {
            //  deviceList=deviceDao.getDeviceInfoByHostId(hostId+"");
            deviceList = deviceDao.getDeviceList(hostId);
        } catch (Exception e) {
            logger.info("镜头列表不存在，hostId=" + hostId, e);
            deviceList = Collections.EMPTY_LIST;
        }
        return deviceList;
    }


    /**
     * 获取该id的设备实体
     *
     * @param id int 设备ID
     * @return Device实体
     */
    @Transactional
    public Device getDeviceInfoService(int id) {
        try {
            return deviceDao.getDeviceInfo(id);
        } catch (Exception e) {
            logger.error("获取设备实体失败，id=" + id, e);
            return null;
        }
    }

    /**
     * 删除设备
     *
     * @param id int 设备ID
     * @return boolean default true，删除失败或者抛出异常之后为false
     */
    @Transactional
    public boolean delDeviceService(final int id) {
        try {
            deviceDao.delDevice(id);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("删除失败 id=" + id, e);
            return false;
        }
    }

    /**
     * 增加设备
     *
     * @return
     */
    @Transactional
    public boolean addDeviceService(String hostId, String deviceName, String deviceMainToken, String deviceSubToken, String deviceMainStream, String deviceSubStream) {
        Device device = new Device();
        device.setDeviceName(deviceName);
        device.setDeviceMainToken(deviceMainToken);
        device.setDeviceSubToken(deviceSubToken);
        device.setDeviceMainStream(deviceMainStream);
        device.setDeviceSubStream(deviceSubStream);
        device.setHostId(Integer.parseInt(hostId));
        try {
            deviceDao.addDevice(device);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("添加设备失败", e);
            return false;
        }
    }

    /**
     * 根据hostid获取设备镜头
     * @param hostid 主机id
     * @param deviceName 镜头模式
     * @return
     */
    public String getSubTokenByHostid(final int hostid, final String deviceName) {
        try {
            return deviceDao.getSubTokenByHostid(hostid, deviceName);
        } catch (Exception e) {
            logger.error("获取设备镜头失败", e);
            return null;
        }
    }

    /**
     * 根据hostid获取主镜头
     * @param hostId 主机id
     * @param deviceName
     * @return
     */
    public String getMainTokenByHostid(int hostId, String deviceName) {
        try {
            return deviceDao.getMainTokenByHostid(hostId, deviceName);
        } catch (Exception e) {
            logger.error("获取设备主镜头失败", e);
            return "";
        }
    }

    /**
     * 获取学生和教师流
     * @param hostId 主机id
     * @return
     */
    public List<Object[]> getTeachAndStudentStream(int hostId) {
        try {
            return deviceDao.getTeachAndStudentStream(hostId);
        } catch (Exception e) {
            logger.error("获取设备学生镜头失败", e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 获取直播流
     * @param hostId 主机id
     * @param deviceName 镜头模式
     * @return
     */
    public Object[] getStream(int hostId, String deviceName) {
        try {
            return deviceDao.getStream(hostId, deviceName);
        } catch (Exception e) {
            logger.error("获取流媒体失败", e);
            return null;
        }
    }

    /**
     * 修改镜头媒体流地址
     *
     * @param device Device
     * @return boolean default true，删除失败或者抛出异常之后为false
     */
    @Transactional
    public boolean updateDeviceService(Device device) {
        try {
            deviceDao.updateDevice(device);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("修改流媒体失败", e);
            return false;
        }
    }

}
