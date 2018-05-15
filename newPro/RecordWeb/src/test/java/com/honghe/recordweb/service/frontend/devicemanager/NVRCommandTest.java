package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NVRCommandTest {

    Logger logger = Logger.getLogger(NVRCommandTest.class);
    private NVRCommand nvrCommand;
    Map<String, Object> par_map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        nvrCommand = DaoFactory.getDao(NVRCommand.class);
        par_map.clear();
    }

    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }


    @Test
    /**
     * 设置台标
     */
    public void testSetLogo() throws Exception {
        String ip = "";
        String path = "";
        String guid = "";
        String position = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("path", path);
        par_map.put("guid", guid);
        par_map.put("position", position);
        nvrCommand.setLogo(ip, path, guid, position);
    }

    @Test
    /**
     * 获取台标
     */
    public void testGetLogo() throws Exception {

    }

    @Test
    /**
     * 移除台标
     */
    public void testRemoveLogo() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        nvrCommand.removeLogo(ip);

    }

    @Test
    /**
     * 设置分辨率
     */
    public void testSetResolution() throws Exception {
        String ip = "";
        String hostid = "";
        int x = 1920;
        int y = 1080;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostid", hostid);
        par_map.put("x", x);
        par_map.put("y", y);
        nvrCommand.setResolution(ip, hostid, x, y);
    }

    @Test
    /**
     * 设置录像信息
     */
    public void testSetVideoInfo() throws Exception {
        String ip = "";
        String hostid = "";
        int x = 1920;
        int y = 1080;
        int bitRate = 128;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostid", hostid);
        par_map.put("x", x);
        par_map.put("y", y);
        par_map.put("bitRate", bitRate);
        nvrCommand.setVideoInfo(ip, hostid, x, y, bitRate);


    }

    @Test
    /**
     * 查询分辨率
     */
    public void testGetResolution() throws Exception {
        String ip = "";
        String hostid = "";

        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostid", hostid);

        nvrCommand.getResolution(ip, hostid);
    }

    @Test
    /**
     * 进入/退出导播页面
     */
    public void testSetBackgroundDirectMode() throws Exception {
        String ip = "";
        //true 进入导播页面 ；false 退出导播页面
        boolean isStart = false;
        String remoteAddr = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("isStart", String.valueOf(isStart));
        par_map.put("remoteAddr", remoteAddr);
        nvrCommand.setBackgroundDirectMode(ip, isStart, remoteAddr);
    }

    @Test
    /**
     * 设置码率
     */
    public void testSetBitrate() throws Exception {
        String ip = "";
        String hostid = "";
        int bitRate = 128;
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostid", hostid);
        par_map.put("bitRate", bitRate);
        nvrCommand.setBitrate(ip, hostid, bitRate);
    }

    @Test
    /**
     * 查询码率
     */
    public void testGetBitrate() throws Exception {
        String ip = "";
        String hostid = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("hostid", hostid);
        nvrCommand.getBitrate(ip, hostid);

    }

    @Test
    /**
     * 设置nas
     */
    public void testSetNas() throws Exception {
        String ip = "";
        String rootPath = "";
        String hostName = "";
        String userName = "";
        String password = "";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("rootPath", rootPath);
        par_map.put("hostName", hostName);
        par_map.put("userName", userName);
        par_map.put("password", password);

        nvrCommand.setNas(ip, rootPath, hostName, userName, password);


    }

    @Test
    /**
     *  清除nas设置
     */
    public void testClearNas() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        nvrCommand.clearNas(ip);

    }

    @Test
    /**
     * 查询录制状态
     */
    public void testGetRecordingStatus() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Map re_value = nvrCommand.getRecordingStatus(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 查询音量
     */
    public void testGetVolume() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Integer re_value = nvrCommand.getVolume(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 调用设备通信进行字幕查询
     */
    public void testGetCaption() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Map re_value = nvrCommand.getCaption(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     *  片头查询
     */
    public void testGetTitleVideo() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Map re_value = nvrCommand.getTitleVideo(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 片尾查询
     */
    public void testGetEndingVideo() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Map re_value = nvrCommand.getEndingVideo(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 查询课程信息，调用设备通信进行查询。
     */
    public void testGetCourseInfo() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        Map re_value = nvrCommand.getCourseInfo(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 导播界面开启时，调用设备通信进行导播策略的查询。当状态为自动控制模式时候，分屏操作和云台操作禁用。
     */
    public void testGetDirectMode() throws Exception {
        String ip = "";
        par_map.clear();
        par_map.put("ip", ip);
        String re_value = nvrCommand.getDirectMode(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 开始录制(同步)
     */
    public void testStartRecord() throws Exception {
        String ip = "192.168.20.221";
        int hostid = 1;
        String ext = "";
        int a = nvrCommand.startRecord(ip, hostid, ext);
    }

    @Test
    /**
     * 停止录制(同步)
     */
    public void testStopRecord() throws Exception {
        String ip = "192.168.20.221";
        int a = nvrCommand.stopRecord(ip);
    }

    @Test
    /**
     * 获取镜头主码流token
     */
    public void testGetMainTokenByHostid() throws Exception {
        int hostid = 1;
        String device_name = "class1";
        String str = nvrCommand.getMainTokenByHostid(hostid, device_name);
    }

    @Test
    /**
     * 添加录像计划
     */
    public void testAddPlanCommand() throws Exception {
        String ip = "192.168.20.221";
        List list = new ArrayList();
        Map map = new HashMap();
        int a = nvrCommand.addPlanCommand(ip, list);
    }

    //-------------------------------------------------上面的都没测试----------------------------------------------
    @Test
    /**
     * 删除录播计划
     */
    public void testDelPlanCommand() throws Exception {
        int hostId = 1;
        int timeplan = 1;
        String ip = "192.168.20.221";
        int a = nvrCommand.delPlanCommand(hostId, timeplan, ip);
        //todo 返回结果均为-1 不知道参数如何设置
        System.out.println(a);
    }

    @Test
    /**
     * 删除所有的录像计划
     */
    public void testDelAllPlanCommand() throws Exception {
        int hostId = 8;
        String ip = "192.168.20.221";
        boolean b = nvrCommand.delAllPlanCommand(hostId, ip);
        //todo 返回结果均为false 不知参数如何设置
    }

    @Test
    /**
     *获取所有的计划
     */
    public void testGetAllPlan() throws Exception {
        String ip = "192.168.20.221";
        String ext = "";
        List list = nvrCommand.getAllPlan(ip, ext);
        //todo 暂不确定返回值是否有问题 更换多种参数都没有查询到数据
    }

    @Test
    /**
     * 获取设备型号
     *
     */
    public void testGetLocalModel() throws Exception {
        String ip = "192.168.20.221";
        String str = nvrCommand.getLocalModel(ip);
    }

    @Test
    /**
     * 获取当前版式
     *
     */
    public void testGetCurrentLayout() throws Exception {
        String ip = "192.168.20.221";
        String str = nvrCommand.getCurrentLayout(ip);
    }

    @Test
    /**
     * 获取版式
     */
    public void testGetLayout() throws Exception {
        String ip = "192.168.20.221";
        List list = nvrCommand.getLayout(ip);
    }

    @Test
    /**
     * 开机
     */
    public void testStart() throws Exception {
        String ip = "192.168.20.221";
        String mac = "";
        boolean b = nvrCommand.start(ip, mac);
        //todo mac参数没有被使用 暂时未测试
        System.out.println(b);
    }

    @Test
    /**
     * 关机
     */
    public void testShutdown() throws Exception {
        String ip = "192.168.20.221";
        String userSessionId = "";
        boolean b = nvrCommand.shutdown(ip, userSessionId);
        //todo userSessionId参数没有被使用 暂时未测试
        System.out.println(b);
    }

    @Test
    /**
     * 获取所有token
     *
     */
    public void testGetAllMediaToken() throws Exception {
        String ip = "192.168.20.221";
        List list = nvrCommand.getAllMediaToken(ip);
    }

    @Test
    /**
     * 获取设备功能列表
     */
    public void testGetNvrSupport() throws Exception {
        String ip = "192.168.20.221";
        List list = nvrCommand.getNvrSupport(ip);
    }


}