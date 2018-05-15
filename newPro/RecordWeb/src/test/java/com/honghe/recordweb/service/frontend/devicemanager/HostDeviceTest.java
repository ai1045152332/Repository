package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostDeviceTest {

    Logger logger = Logger.getLogger(HostDeviceTest.class);
    private HostDevice hostDevice;
    Map<String, Object> par_map = new HashMap<>();


    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }


    @Before
    public void setUp() throws Exception {
        hostDevice = DaoFactory.getDao(HostDevice.class);
        par_map.clear();
    }
//


    @Test
    /**
     * 更新设备用户名，密码
     */
    public void testUpdate() throws Exception {
        String ip = "";
        String userName = "";
        String password = "";

        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("userName", userName);
        par_map.put("password", password);

        boolean re_value = hostDevice.update(ip, userName, password);
        out(par_map, re_value);
    }

    @Test
    /**
     *  依据IP获取设备状态
     */
    public void testGetStatus() throws Exception {
        String ip = "";

        par_map.clear();
        par_map.put("ip", ip);

        String re_value = hostDevice.getStatus(ip);

        out(par_map, re_value);

    }

    @Test
    /**
     * 依据IP判断是否在线
     */
    public void testIsOnline() throws Exception {
        String ip = "";

        par_map.clear();
        par_map.put("ip", ip);

        boolean re_value = hostDevice.isOnline(ip);

        out(par_map, re_value);
    }

    @Test
    /**
     * 根据ip获取设备信息
     */
    public void testGetHostInfoByIp() throws Exception {
        String ip = "";

        par_map.clear();
        par_map.put("ip", ip);

        Map re_value = hostDevice.getHostInfoByIp(ip);

        out(par_map, re_value);

    }

    @Test
    /**
     * 获取设备信息
     */
    public void testGetExtensionInfo() throws Exception {
        String ip = "";

        par_map.clear();
        par_map.put("ip", ip);

        Map re_value = hostDevice.getExtensionInfo(ip);

        out(par_map, re_value);
    }

    @Test
    /**
     * 未分组设备信息
     */
    public void testGetNotHostInfo() throws Exception {
        String ip = "";
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("type", type);

        List<Map> re_value = hostDevice.getNotHostInfo(type, ip);
        out(par_map, re_value);

        ip = "";
        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("type", type);

        re_value = hostDevice.getNotHostInfo(type, ip);
        out(par_map, re_value);


        ip = "";
        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("type", type);

        re_value = hostDevice.getNotHostInfo(type, ip);
        out(par_map, re_value);


        ip = "";
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("type", type);

        re_value = hostDevice.getNotHostInfo(type, ip);
        out(par_map, re_value);


    }

    @Test
    /**
     * 获取设备信息
     */
    public void testGetHostInfo() throws Exception {
        int id = 1;
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        par_map.put("id", id);
        Result host = hostDevice.getHostInfo(type, String.valueOf(id));
        out(par_map, host);

        id = 2;
        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        par_map.put("id", id);
        host = hostDevice.getHostInfo(type, String.valueOf(id));
        out(par_map, host);

        id = 3;
        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        par_map.put("id", id);
        host = hostDevice.getHostInfo(type, String.valueOf(id));
        out(par_map, host);


        id = 5;
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        par_map.put("id", id);
        host = hostDevice.getHostInfo(type, String.valueOf(id));
        out(par_map, host);
    }

    @Test
    /**
     * 获取设备数量
     */
    public void testGetHostCount() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        int counts = hostDevice.getHostCount(type);
        out(par_map, counts);

        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        counts = hostDevice.getHostCount(type);
        out(par_map, counts);

        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        counts = hostDevice.getHostCount(type);
        out(par_map, counts);


        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        counts = hostDevice.getHostCount(type);
        out(par_map, counts);

    }

    @Test
    /**
     * 修改班级名称
     * 参数 String ip, String hostName
     */
    public void testUpdateHostName() throws Exception {
        String ip = "192.168.18.21";
        String hostName = "class211123";
        boolean b = hostDevice.updateHostName(ip, hostName,"hhtc");
        //todo 数据库名称更改 但大屏显示未变
        System.out.println(b);
    }

    @Test
    /**
     * 删除设备
     */
    public void testDelHostd() throws Exception {

        String ip = "";
        String hostId = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostId", hostId);

        boolean re_value = hostDevice.delHostd(hostId, ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 发现设备数量
     */
    public void testDiscoverCount() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        String str = hostDevice.discoverCount(type);
        out(par_map, str);

        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        str = hostDevice.discoverCount(type);
        out(par_map, str);

        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        str = hostDevice.discoverCount(type);
        out(par_map, str);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        str = hostDevice.discoverCount(type);
        out(par_map, str);

        type = CommonName.DEVICE_TYPE_WHITEBOARD;
        par_map.put("type", type);
        str = hostDevice.discoverCount(type);
        out(par_map, str);


    }

    @Test
    /**
     * 获取某一类型的所有设备信息
     */
    public void testGetAllHostInfo() throws Exception {
        String type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        Map map = hostDevice.getAllHostInfo(type);
        out(par_map, map);

        par_map.clear();
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        map = hostDevice.getAllHostInfo(type);
        out(par_map, map);

        par_map.clear();
        type = CommonName.DEVICE_TYPE_WHITEBOARD;
        par_map.put("type", type);
        map = hostDevice.getAllHostInfo(type);
        out(par_map, map);
    }

    @Test
    /**
     *  根据物理地址返回设备名
     */
    public void testGetNameByMac() throws Exception {
        String mac = "D0:FF:50:96:42:FE";
        Map map = hostDevice.getNameByMac(mac);
        par_map.put("mac", mac);
        out(par_map, map);
    }

    @Test
    /**
     * 获取设备信息并分页
     */
    public void testGetHostInfoByPage() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        String hostIds = "";
        int currentPage = 1;
        int pageSize = 2;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        Map re_value = hostDevice.getNotHostInfoByPage(type, hostIds, pageSize, currentPage);
        ;
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_SCREEN;
        hostIds = "";
        currentPage = 1;
        pageSize = 2;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, pageSize, currentPage);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_RECOURD;
        hostIds = "";
        currentPage = 1;
        pageSize = 2;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, pageSize, currentPage);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        hostIds = "";
        currentPage = 1;
        pageSize = 100;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, pageSize, currentPage);
        out(par_map, re_value);

    }

    @Test
    /**
     * 获取未分组设备信息并分页
     */
    public void testGetNotHostInfoByPage() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        String hostIds = "";
        int currentPage = 1;
        int pageSize = 100;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        Map re_value = hostDevice.getNotHostInfoByPage(type, hostIds, currentPage, pageSize);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_SCREEN;
        hostIds = "";
        currentPage = 1;
        pageSize = 100;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, currentPage, pageSize);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_RECOURD;
        hostIds = "";
        currentPage = 1;
        pageSize = 100;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, currentPage, pageSize);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        hostIds = "";
        currentPage = 1;
        pageSize = 100;
        par_map.clear();
        par_map.put("type", type);
        par_map.put("hostIds", hostIds);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);

        re_value = hostDevice.getNotHostInfoByPage(type, hostIds, currentPage, pageSize);
        out(par_map, re_value);
    }

    @Test
    /**
     * 按名称精确查找host信息
     */
    public void testGetHostService() throws Exception {
        String name = "class2";
        par_map.put("name", name);
        boolean value = hostDevice.getHostService(name);
        out(par_map, value);
    }

    @Test
    /**
     * 通过id获取设备信息
     */
    public void testGetHostInfoById() throws Exception {
        String id = "19";
        par_map.put("id", id);
        Map map = hostDevice.getHostInfoById(id);
        out(par_map, map);
    }


    @Test
    /**
     * 获取某一类型所有设备列表
     */
    public void testGetHostInfoByType() throws Exception {

        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.clear();
        par_map.put("type", type);

        List<Map<String, Object>> re_value = hostDevice.getHostInfoByType(type);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.clear();
        par_map.put("type", type);

        re_value = hostDevice.getHostInfoByType(type);
        out(par_map, re_value);

        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.clear();
        par_map.put("type", type);

        re_value = hostDevice.getHostInfoByType(type);
        out(par_map, re_value);


        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.clear();
        par_map.put("type", type);

        re_value = hostDevice.getHostInfoByType(type);
        out(par_map, re_value);
    }

    @Test
    /**
     *  添加设备入库
     */
    public void testAddHost() throws Exception {
        String type = CommonName.DEVICE_TYPE_WHITEBOARD;
        String username = "admins";
        String password = "admins";
        String systerm = "55";
        par_map.put("type", type);
        par_map.put("username", username);
        par_map.put("password", password);
        par_map.put("systerm", systerm);
        Map map = hostDevice.addHost(type, username, password, systerm);
        out(par_map, map);
    }


    @Test
    /**
     * 根据名字获取设备信息
     */
    public void testGetHostListByNames() throws Exception {
        String type = CommonName.DEVICE_TYPE_RECOURD;
        String names = "20.228,20.229,332,高中1班,虚拟教室";
        List<Map> map = hostDevice.getHostListByNames(type, names);
        out(par_map, map);

    }

}