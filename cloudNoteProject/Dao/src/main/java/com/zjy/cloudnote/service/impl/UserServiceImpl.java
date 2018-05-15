package com.zjy.cloudnote.service.impl;

import com.zjy.cloudnote.dao.UserDao;
import com.zjy.cloudnote.entity.User;
import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUser() {
        return userDao.queryUser();
    }

    @Override
    public User queryUserById(int userId) {
        return userDao.queryUserById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(User user) {
        //可以写判断用户名等是否为空
        //可以设置最后登录时间  创建时间
         userDao.insertUser(user);
         //判断返回值是否为1   是1   影响1行成功

        //可以抛出异常

        //    throw new RuntimeException("插入失败"+e.getMessage());
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        //可以写判断用户名等是否为空
        //可以设置最后登录时间  创建时间
        userDao.updateUser(user);
        //判断返回值是否为1   是1   影响1行成功
        return true;
    }

    @Override
    public boolean deleteUser(int userId) {
        //可以写判断用户名等是否为空
        //可以设置最后登录时间  创建时间
        userDao.deleteUser(userId);
        //判断返回值是否为1   是1   影响1行成功
        return true;
    }
}
