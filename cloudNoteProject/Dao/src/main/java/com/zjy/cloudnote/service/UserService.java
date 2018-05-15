package com.zjy.cloudnote.service;

import com.zjy.cloudnote.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryUser();
    User queryUserById(int userId);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(int userId);
}
