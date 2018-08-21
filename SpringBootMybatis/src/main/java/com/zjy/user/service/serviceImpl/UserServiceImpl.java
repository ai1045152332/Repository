package com.zjy.user.service.serviceImpl;

import com.zjy.user.dao.UserDao;
import com.zjy.user.entity.User;
import com.zjy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override//查询所有user
    public List<User> queryUser() {
        return userDao.queryUser();
    }

    @Override//根据id查询user
    public User queryUserById(int i) {
        return userDao.queryUserById(i);
    }

    //修改用户信息
    @Override
    public boolean updateUser(User user) {
        if(user.getGroupId()!=null&&user.getLoginName()!=null&&!"".equals(user.getLoginName())&&
                user.getPassword()!=null&&!"".equals(user.getPassword())&&
                user.getUsername()!=null&&!"".equals(user.getUsername())){
            try {
                int effectNum = userDao.updateUser(user);
                if (effectNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新用户信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新用户信息失败!" + e.getMessage());
            }
        }else{
            throw new RuntimeException("用户信息不能为空");
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        if (userId > 0) {
            try {
                int effectNum = userDao.deleteUser(userId);
                if (effectNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除个人信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除个人信息失败!" + e.getMessage());
            }
        } else {
            throw new RuntimeException("删除个人信息失败!");
        }
    }

    /*实现注册功能*/
    @Override
    @Transactional
    public boolean registerUser(User user) {
        if(user.getGroupId()!=null&&user.getLoginName()!=null&&!"".equals(user.getLoginName())&&
                user.getPassword()!=null&&!"".equals(user.getPassword())&&
                user.getUsername()!=null&&!"".equals(user.getUsername())){
            user.setGenTime(new Date());//设置创建时间
            user.setLastLoginTime(new Date());//最后登录时间
            user.setCount(1);//登录次数
            try {
                int effectNum = userDao.insertUser(user);
                if(effectNum>0){
                    return true;
                }else {
                    throw new RuntimeException("插入用户信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入用户信息失败!" + e.getMessage());
            }
        }else {
            throw new RuntimeException("用户信息不能为空");
        }
    }

}
