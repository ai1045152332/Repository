package com.zjy.user.dao;

import com.zjy.user.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

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
    }
}