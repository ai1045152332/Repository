package com.zjy.demo.dao;

import com.zjy.demo.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryArea() {
        List<Area> list = areaDao.queryArea();
        assertEquals(2,list.size());
    }

    @Test
    public void queryAreaById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("路北",area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("eiben");
        area.setPriority(1);
        int num = areaDao.insertArea(area);
        assertEquals(1,num);
    }

    @Test
    public void updateArea() {
        Area area = new Area();
        area.setAreaId(1);
        area.setAreaName("路北");
        area.setPriority(10);
        area.setLastEditTime(new Date());
        int num = areaDao.updateArea(area);
        assertEquals(1,num);
    }

    @Test
    public void deleteArea() {
        int num = areaDao.deleteArea(5);
        assertEquals(1,num);
    }
}