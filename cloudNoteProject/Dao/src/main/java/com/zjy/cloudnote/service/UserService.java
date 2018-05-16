package com.zjy.cloudnote.service;

import com.zjy.cloudnote.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryUser();
    User queryUserById(int userId);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    /*登录功能-验证登录*/
    String loginCheck(String loginName ,String password);
}
