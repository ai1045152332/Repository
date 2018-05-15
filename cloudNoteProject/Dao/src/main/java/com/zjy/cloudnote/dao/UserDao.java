package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.User;

import java.util.List;

public interface UserDao {
    List<User> queryUser();
    User queryUserById(int userId);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(int userId);
    /*验证登录*/
    User loginCheck(String username ,String password);
}
