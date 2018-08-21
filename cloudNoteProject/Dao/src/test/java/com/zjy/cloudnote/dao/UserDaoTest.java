package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.User;
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
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void queryUser() {
        List<User> list = userDao.queryUser();
        assertEquals(2,list.size());
    }

    @Test
    public void queryUserById() {
        User user = userDao.queryUserById(1001);
        assertEquals("赵健宇",user.getUsername());
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setGroupId(2);
        user.setLoginName("dengluming");
        user.setPassword("zjy");
        user.setUsername("xingming");
        user.setMobile("187315988");
        user.setEmail("11@qq.cc");
        user.setGenTime(new Date());
        user.setLastLoginTime(new Date());
        user.setCount(1);
        int result = userDao.insertUser(user);
        assertEquals(1,result);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserId(1004);
        user.setGroupId(2);
        user.setLoginName("dengluming");
        user.setPassword("zjy");
        user.setUsername("这里是姓名");
        user.setMobile("187315988");
        user.setEmail("22@qq.cc");
        user.setLastLoginTime(new Date());
        user.setCount(1);
        int result = userDao.updateUser(user);
        assertEquals(1,result);
    }

    @Test
    public void deleteUser() {
        int result = userDao.deleteUser(1003);
        assertEquals(1,result);
    }
}