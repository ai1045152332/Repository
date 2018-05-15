package com.honghe.recordweb.service.frontend.signalplan.impl;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Signalplan;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanService;
import com.honghe.recordweb.util.CommonName;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SignalplanImplTest {

    private SignalplanService signalplanService;

    @Before
    public void setUp() throws Exception {
        signalplanService = DaoFactory.getDao(SignalplanService.class);
    }

    @Test
    public void testSignalplanListService() throws Exception {
        PageBean pageBean = signalplanService.signalplanListService(1, 5, 0, CommonName.DEVICE_TYPE_SCREEN);
        System.out.print("打印信息：" + pageBean.getPageCount() + "列表：" + pageBean.getRecordList().get(0).toString());
    }

    @Test
    public void testSignalplanHostExistsService() throws Exception {
        boolean flag = signalplanService.signalplanHostExistsService(5);
        System.out.println("flag:" + flag);
    }

    @Test
    public void testAddSignalplanToHostService() throws Exception {

        boolean flag = signalplanService.addSignalplanToHostService(8, 26, " ");
        System.out.println("flag:" + flag);
    }

    @Test
    public void testDelSignalplanHostHostService() throws Exception {
        boolean flag = signalplanService.delSignalplanHostHostService(8);
        System.out.println("flag:" + flag);
    }


    @Test
    public void testGetSignalplanInfoService() throws Exception {
        List<Map> mapList = signalplanService.getSignalplanInfoService(6);
        System.out.println(" mapList :" + mapList.toString());
    }

    @Test
    public void testGetSignalplanService() throws Exception {
        Signalplan signalplan = signalplanService.getSignalplanService(6);
        System.out.println(" id:" + signalplan.getSignalId());
    }

    @Test
    public void testAddSignalplanService() throws Exception {
        Signalplan signalplan = signalplanService.getSignalplanService(8);
        boolean flag = signalplanService.addSignalplanService(signalplan, "25,26", " , "," ");
        System.out.println("操作结果：" + flag);
    }

    @Test
    public void testUpdateSignalplanService() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Signalplan signalplan = signalplanService.getSignalplanService(9);

        signalplan.setSignal("0");
        signalplan.setSignalCode("0");
        signalplan.setSignalLoop(1);
        signalplan.setSignalLooptype("1");
        signalplan.setSignalDate(2);

        signalplan.setSignalWeek(2);
        signalplan.setSignalTime("17:00");
        signalplan.setSignalUid(2);
        signalplan.setSignalUname("测试");
        Date now = new Date();
        signalplan.setSignalCreatetime(new Timestamp(df.parse(df.format(now)).getTime()));

        boolean flag = signalplanService.updateSignalplanService(signalplan, "25", " "," ");

        System.out.println("操作结果：" + flag);
    }

    @Test
    public void testDelSignalplanService() throws Exception {
        Signalplan signalplan = signalplanService.getSignalplanService(9);
        boolean flag = signalplanService.delSignalplanService(signalplan, "25");
        System.out.println("操作结果：" + flag);
    }

    @Test
    public void testDelSignalplanByHostService() throws Exception {
        boolean flag = signalplanService.delSignalplanHostHostService(10);
        System.out.println("操作结果：" + flag);
    }
}