package com.zjy.cloudnote.service.impl;

import com.zjy.cloudnote.dao.UserDao;
import com.zjy.cloudnote.entity.User;
import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        User user =userDao.queryUserById(userId);
        if(user==null){
            throw new RuntimeException("没有找到这个用户"+userId);
        }else{
            return user;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(User user) {
        int result = -1;
        if(user.getLoginName()==null||user.getPassword()==null){
            throw new RuntimeException("用户名或密码不能为空");
        }else{
            //设置创建时间
            user.setGenTime(new Date());
            result = userDao.insertUser(user);
        }

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


    @Override
    public String loginCheck(String loginName, String password) {
        User user = userDao.loginCheck(loginName, password);
        if(user==null||null==user.getLoginName()){
            //用户没找到或用户名为空
            return null;
        }else{
            /*
            登录次数加一        最后一次登录时间++
             */
            return user.getLoginName();
        }

    }
}
