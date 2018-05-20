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
            user.setGenTime(new Date());//设置创建时间
            user.setCount(1);//设置第一次登陆次数
            user.setLastLoginTime(new Date());//设置最后一次登陆时间
            result = userDao.insertUser(user);
        }
        if(result>0){
            //影响行数大于0成功
            return true;
        }else{
            throw new RuntimeException("添加用户失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        if(user.getLoginName()==null||"".equals(user.getLoginName())){
            throw new RuntimeException("用户名为空");
        }
        if(user.getUserId()==null||"".equals(user.getUserId())){
            throw new RuntimeException("用户ID为空");
        }
        if(user.getCount()==null){
            user.setCount(1);
        }
        user.setLastLoginTime(new Date());
        int resultNum = userDao.updateUser(user);
        if(resultNum==1){
            return true;
        }else{
            throw new RuntimeException("修改用户失败");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(int userId) {
        int resultNum = userDao.deleteUser(userId);
        if(resultNum==1){
            return true;
        }else{
            throw new RuntimeException("删除用户失败");
        }
    }


    @Override
    public String loginCheck(String loginName, String password) {
        User user = userDao.loginCheck(loginName, password);
        if(user==null||null==user.getLoginName()){
            //账号或密码错误
            return null;
        }else{
            /*
            登录次数加一        最后一次登录时间++
             */
            user.setCount(user.getCount()+1);
            user.setLastLoginTime(new Date());
            userDao.updateUser(user);
            return user.getLoginName();
        }

    }
}
