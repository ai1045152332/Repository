package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NVRTest {
    Logger logger = Logger.getLogger(NVRTest.class);
    private NVR nvr;
    Map<String, Object> par_map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        nvr = DaoFactory.getDao(NVRTest.class);
        par_map.clear();
    }

    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }

    @Test
    public void testDiscovered() throws Exception {

    }

    @Test
    public void testInit() throws Exception {

    }

    @Test
    /**
     *  获取在线设备
     */
    public void testGetOnlineClasses() throws Exception {

        par_map.clear();
        List<Object[]> re_vlaue = nvr.getOnlineClasses();
        out(par_map, re_vlaue);
    }

    @Test
    /**
     * 手动发现
     */
    public void testDiscovered1() throws Exception {
        String ip = "";
        String className = "";
        String typeInt = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("className", className);
        par_map.put("typeInt", typeInt);
        Map map = nvr.discovered(ip, className, typeInt);
        out(par_map, map);

    }

    @Test
    /**
     * 添加外网设备
     */
    public void testAddNetHost() throws Exception {
        String ip = "";
        String className = "";
        String typeInt = "";
        String netUrl = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("className", className);
        par_map.put("typeInt", typeInt);
        par_map.put("netUrl", netUrl);
        Map map = nvr.addNetHost(ip, className, typeInt, netUrl);
        out(par_map, map);


    }

    @Test
    public void testGetCameraUrl() throws Exception {
        String ip = "";
        String cameraToken = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("cameraToken", cameraToken);

        String re_value = nvr.getCameraUrl(ip, cameraToken);
        out(par_map, re_value);

    }

    @Test

    /**
     * 添加镜头信息
     */
    public void testAddDevice() throws Exception {
        String hostip = "192.168.17.11";
        String hostid = "55";
        par_map.put("hostid", hostid);
        par_map.put("hostip", hostip);
        boolean value = nvr.addDevice(hostid, hostip);
        out(par_map, value);
        //  todo 结果将该条数据保存在哪个表
    }
}