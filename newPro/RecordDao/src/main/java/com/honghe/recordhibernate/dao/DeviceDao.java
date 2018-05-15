package com.honghe.recordhibernate.dao;
/**
 * Created by lch on 2014/9/23.
 */

import com.honghe.recordhibernate.entity.Device;

import java.util.List;
import java.util.Map;

public interface DeviceDao {
    //获取设备列表
    public List getDeviceList() throws Exception;

    public Boolean updateDevice(Device device) throws Exception;

    public void updateDevice(int deviceId, String deviceName, String deviceToken, int hostId) throws Exception;
    //根据ID获取设备信息
    public Device getDeviceInfo(int id) throws Exception;
    //添加设备
    public void addDevice(Device device) throws Exception;

    //删除设备信息
    public void delDevice(int id) throws Exception;

    public List<Object[]> getDeviceList(int hostId) throws Exception;

    public List<Object[]> getToken(int hostId) throws Exception;

    public String getMainTokenByHostid(final int hostid,final String deviceName) throws Exception;
    public String getSubTokenByHostid(final int hostid,final String deviceName) throws Exception;
    //根据设备型号获取设备
    public List getDeviceBySpec(int specid) throws Exception;
    
    public boolean isDevice(int hostId,String deviceName)throws Exception;

    public List<Object[]> getTeachAndStudentStream(int hostId)throws Exception;

    public Object[] getStream(int hostId,String deviceName)throws Exception;

//    public void updateTBOXDeviceData(String device_name,String device_maintoken)throws Exception;

    public void updateTBOXDeviceName(String deviceName, Integer deviceId) throws Exception;

    public Integer getTBOXDeviceId(String token) throws Exception;

//    public Integer getTBOXDeviceConfigId(String token) throws Exception;

//    public void updateTBOXDeviceConfigName(String deviceName, Integer deviceId) throws Exception;
  //  -------------------------------------------------------------跨库访问设备数据信息------------------------------------------------------------

    public Map getHostInfoById(String hostId) throws Exception;

    public Map<String, Object> getHostInfoByIp(String ip) throws Exception;

    public int getHostCount(String type) throws Exception;

    public List<Map<String, String>> getHostByType(String deviceType);

}
