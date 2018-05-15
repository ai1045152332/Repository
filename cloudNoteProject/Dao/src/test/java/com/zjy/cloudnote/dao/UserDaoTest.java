package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertEquals(1,list.size());
    }

    @Test
    public void queryUserById() {
        User user = userDao.queryUserById(0);
        assertEquals("admin",user.getUsername());
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("zjy");
        user.setPassword("zjy");
        int result = userDao.insertUser(user);
        assertEquals(1,result);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("zjy");
        user.setPassword("admin");
        int result = userDao.updateUser(user);
        assertEquals(1,result);
    }

    @Test
    public void deleteUser() {
        int result = userDao.deleteUser(3);
        assertEquals(1,result);
    }
}