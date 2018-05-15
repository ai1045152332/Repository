package com.zjy.user.dao;

import com.zjy.user.entity.User;

import java.util.List;

public interface UserDao {
    List<User> queryUser();
    User queryUserById(int i);
    int insertUser(User area);
    int updateUser(User area);
    int deleteUser(int areaId);
}
