package com.honghe.recordweb.service.frontend.ringbell.impl;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Ringbell;
import com.honghe.recordweb.service.frontend.ringbell.RingbellService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@Service
@Transactional
public class RingbellServiceImplTest {

    private RingbellService ringbellService;

    @Before
    public void setUp() throws Exception {
        ringbellService = DaoFactory.getDao(RingbellService.class);
    }

    @Test
    public void testRingbellListService() throws Exception {

        PageBean pageBean = ringbellService.ringbellListService(1, 5, 0,"hhtc");
        System.out.print("打印信息：" + pageBean.getPageCount() + "列表：" + pageBean.getRecordList().get(0).toString());
    }

    @Test
    public void testRingbellHostExistsService() throws Exception {

        boolean flag = ringbellService.ringbellHostExistsService(4);

        System.out.println("flag:" + flag);
    }

    @Test
    public void testAddRingbellToHostService() throws Exception {

        ringbellService.addRingbellToHostService(3, 26,"");
    }

    @Test
    public void testDelRingbellHostHostService() throws Exception {
        ringbellService.delRingbellHostHostService(6);
    }


    @Test
    public void testGetRingbellInfoService() throws Exception {
        List<Map> map = ringbellService.getRingbellInfoService(3);
        System.out.println(" map :" + map.toString());
    }

    @Test
    public void testGetRingbellService() throws Exception {

        Ringbell ringbell = ringbellService.getRingbellService(3);

        System.out.println(" ringbellID:" + ringbell.getRingbellId());
    }

    @Test
    public void testAddRingbellService() throws Exception {

        Ringbell ringbell = ringbellService.getRingbellService(6);
        boolean flag = ringbellService.addRingbellService(ringbell, "25","","");
        System.out.println("操作结果：" + flag);
    }

    @Test
    public void testUpdateRingbellService() throws Exception {
        Ringbell ringbell = ringbellService.getRingbellService(7);
        ringbell.setRingbellTime("17:00");

        boolean flag = ringbellService.updateRingbellService(ringbell,"25","","");

        System.out.println("操作结果：" + flag);

    }

    @Test
    public void testDelRingbellService() throws Exception {

        Ringbell ringbell = ringbellService.getRingbellService(7);
        boolean flag = ringbellService.delRingbellService(ringbell,"25");

        System.out.println("操作结果：" + flag);
    }

    @Test
    public void testDelRingbellByHostService() throws Exception {

        boolean flag =   ringbellService.delRingbellHostHostService(8);

        System.out.println("操作结果：" + flag);
    }


}