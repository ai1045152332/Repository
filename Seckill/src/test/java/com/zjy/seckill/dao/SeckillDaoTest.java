package com.zjy.seckill.dao;

import com.zjy.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * 1.启动时加载ioc容器
 * 2.告诉junit spring配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入dao
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        seckillDao.reduceNumber(1000l,new Date());
    }

    @Test
    public void queryById() {
        Seckill seckill = seckillDao.queryById(1000l);
        System.out.println(seckill.toString());
    }

    @Test
    public void queryAll() {
        System.out.println(seckillDao.queryAll(0,100));
    }
}