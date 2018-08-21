package com.zjy.user.service;

import com.zjy.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryUser();
    User queryUserById(int i);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    boolean registerUser(User user);
}
