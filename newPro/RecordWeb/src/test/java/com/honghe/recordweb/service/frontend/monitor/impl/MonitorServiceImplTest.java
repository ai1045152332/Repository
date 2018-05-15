package com.honghe.recordweb.service.frontend.monitor.impl;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.Monitor;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordweb.service.frontend.monitor.MonitorService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@Service
public class MonitorServiceImplTest {


    private MonitorService monitorService;


    @Before
    public void setUp() throws Exception {
        monitorService = DaoFactory.getDao(MonitorService.class);
    }

    @Test
    public void testListsService() throws Exception {

        PageBean<Monitor> pageBean = monitorService.listsService(1, 5, "test1", "2015-06-05", "2015-07-06");

        System.out.println("list:" + pageBean.getRecordList());

    }

    @Test
    public void testAddMonitorService() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Monitor monitor =  new Monitor();

        monitor.setMac("test1");
        monitor.setDeviceName("大屏");
        monitor.setSoftName("QQ");
      //  monitor.setUsecount(7);
        Date now = new Date();
        monitor.setCreateTime(df.parse(df.format(now)));


        monitorService.addMonitorService(monitor);

    }
}