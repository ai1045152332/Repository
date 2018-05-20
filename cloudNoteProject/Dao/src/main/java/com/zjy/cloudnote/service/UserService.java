package com.zjy.cloudnote.service;

import com.zjy.cloudnote.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryUser();
    Map<String,Object> queryUserByPage(User user, Integer page, Integer pageSize);
    User queryUserById(int userId);
    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    /*登录功能-验证登录*/
    String loginCheck(String loginName ,String password);
}
