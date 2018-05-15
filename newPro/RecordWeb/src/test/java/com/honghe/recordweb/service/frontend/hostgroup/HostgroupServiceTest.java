package com.honghe.recordweb.service.frontend.hostgroup;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordweb.util.CommonName;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HostgroupServiceTest {

    Logger logger = Logger.getLogger(HostgroupServiceTest.class);
    private HostgroupService hostgroupService;
    Map<String, Object> par_map = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        hostgroupService = DaoFactory.getDao(HostgroupService.class);
        par_map.clear();
    }


    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }


    @Test
    public void testGetUnknowGroup() {

        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        Map map = hostgroupService.getUnknowGroup(type);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        map = hostgroupService.getUnknowGroup(type);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        map = hostgroupService.getUnknowGroup(type);
        out(par_map, map);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        map = hostgroupService.getUnknowGroup(type);
        out(par_map, map);

    }


//    /**
//     * 模拟测试设备管理
//     */
//    @Test
//    public void testanagement() {
//        int sumNumberHHTCHost = hostgroupService.countService(CommonName.DEVICE_TYPE_SCREEN);
//        int sumNumberHrecHost = hostgroupService.countService(CommonName.DEVICE_TYPE_RECOURD);
//        int sumNumberHTPrHost = hostgroupService.countService(CommonName.DEVICE_TYPE_PROJECTOR);
//        int sumNumberUnknownHost = hostgroupService.countService(CommonName.DEVICE_TYPE_UNKNOWN);
//        String type = "";
//        int userId = 0;
//        logger.debug("-----------------------------------------------------------------------");
//        System.out.println("-----------------------------------------------------------------");
//        List<Object[]> hglist = hostgroupService.getHostgroupList2Service(userId + "", type);
//        logger.debug("-----------------------------------------------------------------------");
//        System.out.println("-----------------------------------------------------------------");
//        //获取所有未分组的教室数量
//        Map<String, Object> map = hostgroupService.getUnknowGroup(type);
//        logger.debug("sumNumberHHTCHost:" + sumNumberHHTCHost + " sumNumberHrecHost" + sumNumberHrecHost + " sumNumberHTPrHost" + sumNumberHTPrHost + " sumNumberUnknownHost" + sumNumberUnknownHost +
//                " hglist" + hglist.toString() + " map" + map.toString());
//        System.out.println("sumNumberHHTCHost:" + sumNumberHHTCHost + " sumNumberHrecHost" + sumNumberHrecHost + " sumNumberHTPrHost" + sumNumberHTPrHost + " sumNumberUnknownHost" + sumNumberUnknownHost +
//                " hglist" + hglist.toString() + " map" + map.toString());
//    }

    @Test
    public void testGetHostInfo() throws Exception {

        int id = 48;
        par_map.put("id", id);
        Host host = hostgroupService.getHostInfo(id);
        out(par_map, host);

    }

    @Test
    public void testGetHostInfoByIp() throws Exception {
        String ip = "192.168.19.118";
        par_map.put("ip", ip);
        Host host = hostgroupService.getHostInfoByIp(ip);
        out(par_map, host);
    }


    //测试是han含三个参数的gnknowGroup方法
    @Test
    public void testGetUnknowGroup1() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        String size = "4";
        String currentPage = "3";
        par_map.put("type", type);
        par_map.put("size", size);
        par_map.put("currentPage", currentPage);
        Map map = hostgroupService.getUnknowGroup(type, size, currentPage);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_RECOURD;
        String size1 = "9";
        String currentPage1 = "1";
        par_map.put("type", type);
        par_map.put("size", size1);
        par_map.put("currentPage", currentPage1);
        Map map1 = hostgroupService.getUnknowGroup(type, size1, currentPage1);
        out(par_map, map1);


        type = CommonName.DEVICE_TYPE_SCREEN;
        String size2 = "9";
        String currentPage2 = "1";
        par_map.put("type", type);
        par_map.put("size", size2);
        par_map.put("currentPage", currentPage2);
        Map map2 = hostgroupService.getUnknowGroup(type, size2, currentPage2);
        out(par_map, map2);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        String size3 = "9";
        String currentPage3 = "1";
        par_map.put("type", type);
        par_map.put("size", size3);
        par_map.put("currentPage", currentPage3);
        Map map3 = hostgroupService.getUnknowGroup(type, size3, currentPage3);
        out(par_map, map3);

    }

    @Test
    public void testHostListJSON() throws Exception {
        String groupid = "1";
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        List list = hostgroupService.hostListJSON(groupid, type);
        out(par_map, list);

        String groupid1 = "2";
        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        list = hostgroupService.hostListJSON(groupid1, type);
        out(par_map, list);

        String groupid2 = "1";
        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        list = hostgroupService.hostListJSON(groupid2, type);
        out(par_map, list);
        String groupid3 = "2";
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        list = hostgroupService.hostListJSON(groupid3, type);
        out(par_map, list);

    }

    @Test
    public void testHostInfoByType() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        List list = hostgroupService.hostInfoByType(type);
        out(par_map, list);


        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        list = hostgroupService.hostInfoByType(type);
        out(par_map, list);


        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        list = hostgroupService.hostInfoByType(type);
        out(par_map, list);

        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        list = hostgroupService.hostInfoByType(type);
        out(par_map, list);

    }

    //未写该方法
    @Test
    public void testHostAndNasListJSON() throws Exception {

    }

    //未写该方法
    @Test
    public void testUpdateHostService() throws Exception {

    }

    //结果为false
    @Test
    public void testDelHostService() throws Exception {
        String id = "47";
        String ip = "192.168.17.2";
        par_map.put("id", id);
        par_map.put("ip", ip);
        boolean value = hostgroupService.delHostService(id, ip);
        out(par_map, value);

    }

    //未写该方法
    @Test
    public void testDelHostDeviceService() throws Exception {

    }

    @Test
    public void testHostDeviceService() throws Exception {
        int id = 50;
        par_map.put("id", id);
        boolean value = hostgroupService.hostDeviceService(id);
        out(par_map, value);

    }

    //todo 暂时无法判断
    @Test
    public void testGetGroupService() throws Exception {
        int usrid = 1;
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        List list = hostgroupService.getGroupService(usrid, type);
        out(par_map, list);

        usrid = 1;
        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getGroupService(usrid, type);
        out(par_map, list);

        usrid = 1;
        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getGroupService(usrid, type);
        out(par_map, list);
        usrid = 1;
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getGroupService(usrid, type);
        out(par_map, list);


    }

    //todo 不能判断是否正确
    @Test
    public void testGetHostgroupListNewService() throws Exception {
        String usrid = "1";
        String hostree = "-1";
        par_map.put("usrid", usrid);
        par_map.put("hostree", hostree);
        List list = hostgroupService.getHostgroupListNewService(usrid, hostree);
        out(par_map, list);
    }


    @Test
    public void testGetHostgroupList2Service() throws Exception {
        String usrid = "2";
        String type = CommonName.DEVICE_TYPE_ALL;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        List list = hostgroupService.getHostgroupList2Service(usrid, type);
        out(par_map, list);

        usrid = "3";
        type = CommonName.DEVICE_TYPE_RECOURD;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getHostgroupList2Service(usrid, type);
        out(par_map, list);

        usrid = "2";
        type = CommonName.DEVICE_TYPE_SCREEN;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getHostgroupList2Service(usrid, type);
        out(par_map, list);
        usrid = "3";
        type = CommonName.DEVICE_TYPE_PROJECTOR;
        par_map.put("type", type);
        par_map.put("userid", usrid);
        list = hostgroupService.getHostgroupList2Service(usrid, type);
        out(par_map, list);
    }


    //todo 不清楚id的作用，测试中未见到id起作用
    @Test
    public void testHostInGroup() throws Exception {
        String type = CommonName.DEVICE_TYPE_ALL;
        Integer id = 19;
        int pageSize = 10;
        int currentPage = 1;
        par_map.put("id", id);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);
        par_map.put("type", type);
        Map map = hostgroupService.hostInGroup(id, "", currentPage, pageSize, type);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_RECOURD;
        id = 40;
        pageSize = 10;
        currentPage = 1;
        par_map.put("id", id);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);
        par_map.put("type", type);
        map = hostgroupService.hostInGroup(id, "", currentPage, pageSize, type);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_SCREEN;
        id = 48;
        pageSize = 10;
        currentPage = 1;
        par_map.put("id", id);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);
        par_map.put("type", type);
        map = hostgroupService.hostInGroup(id, "", currentPage, pageSize, type);
        out(par_map, map);


        type = CommonName.DEVICE_TYPE_PROJECTOR;
        id = 50;
        pageSize = 10;
        currentPage = 1;
        par_map.put("id", id);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);
        par_map.put("type", type);
        map = hostgroupService.hostInGroup(id, "", currentPage, pageSize, type);
        out(par_map, map);


    }

    //todo 不能判断返回内容是否正确
    @Test
    public void testHostInGroup1() throws Exception {
        String viewccname = "高二一班";
        Integer id = 19;
        String condition = "在线";
        int pageSize = 10;
        int currentPage = 1;
        par_map.put("viewccname", viewccname);
        par_map.put("id", id);
        par_map.put("currentPage", currentPage);
        par_map.put("pageSize", pageSize);
        par_map.put("condition", condition);
        Map map = hostgroupService.hostInGroup(viewccname, id, condition, currentPage, pageSize);
        out(par_map, map);


    }

//    @Test
//    public void testHostOnline() throws Exception {
//
//    }

//    @Test
//    public void testGetHostNameByIp() throws Exception {
//
//    }

//    @Test
//    public void testGetDeviceConfigList() throws Exception {
//
//    }

    @Test
    public void testAddHost() throws Exception {
        String type = "htpr";
        String username = "admins";
        String password = "admins";
        String systerm = "55";
        par_map.put("type", type);
        par_map.put("username", username);
        par_map.put("password", password);
        par_map.put("systerm", systerm);
        JSONObject json = hostgroupService.addHost(type, username, password, systerm);
        out(par_map, json);

    }

    @Test
    public  void TestAddVirtualHost(){
        String hostName = "虚拟教室";
        String typeInt = "6";
        String netUrl = "学生全景，教师全景,";
        String cameraName = "rtsp://admin:hht123456@192.168.20.105:554/cam/realmonitor?channel=1&subtype=1,rtsp://root:root@192.168.20.41:8554/session2.mpg,";
       int i=  hostgroupService.addVirtualHost(hostName,typeInt,netUrl,cameraName);

    }

//    @Test
//    public void testAddNetHost() throws Exception {
//
//    }

//    @Test
//    public void testAddDeviceToHost() throws Exception {
//
//    }

//    @Test
//    public void testAddNetDeviceToHost() throws Exception {
//
//    }

//    @Test
//    public void testGetGroupInfoByhostId() throws Exception {
//
//    }

    //    @Test
//    public void testGetIp() throws Exception {
//
//    }
}