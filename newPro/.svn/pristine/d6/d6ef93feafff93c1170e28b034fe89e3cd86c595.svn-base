package com.honghe.recordweb.service.frontend.timeplan;
import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordhibernate.dao.config.DaoFactory;
//import com.honghe.recordweb.service.frontend.devicemanager.DeviceManagerAdapter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by lch on 2014/10/30.
 */
public class TimeplanServiceTest {
    private  TimeplanService timeplanService;
    private DeviceDao deviceDao;
    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.honghe");
        timeplanService = annotationConfigApplicationContext.getBean(TimeplanService.class);
        deviceDao = annotationConfigApplicationContext.getBean(DeviceDao.class);

    }

    @Test
    public void testAddPlanCommand()
    {
       /* timeplan_id      string        计划ID        GUID 非空 主键
        URL                  string        视频地址        非空
        Priority             int             优先级
        host_id            int              班级ID
        DeviceName     string        录制设备名称      192.168.200.230-Onvif
        FileName          string       文件保存名称
        StartTime         DateTime   录制开始时间               非空
        StopTime         DateTime    录制结束时间               非空
        Policy               string        循环策略                       非空
        Repeat             Bool           是否循环                      非空*/

        try
        {
            String timeplanId ="172";
            String uRL;
            String ip="192.168.200.200";
            String devicetoken;
            int host_ID = 2;
            int priority = 1;

            String deviceName="";
            String fileName = "";
            String startTime = "8:00";
            String stopTime = "9:00";
            String policy="week";
            Boolean repeat=true;
            String token = ip+"-Onvif";
           // devicetoken = deviceDao.getTokenByHostid(host_ID);
           // System.out.println(devicetoken);
           // DeviceManagerAdapter deviceManagerAdapter = new DeviceManagerAdapter();
           // uRL = deviceManagerAdapter.getViewclassMediaURL(ip, devicetoken);
            //System.out.println(uRL);
            //timeplanService.addPlanCommand(timeplanId,uRL,priority,host_ID,deviceName,fileName,startTime,stopTime,policy,repeat,token);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
