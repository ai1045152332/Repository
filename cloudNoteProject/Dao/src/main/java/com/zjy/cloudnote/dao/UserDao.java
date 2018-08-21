package com.zjy.cloudnote.dao;

import com.zjy.cloudnote.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserDao {
    List<User> queryUser();
    List<User> queryUserByPage(Example example);
    User queryUserById(int userId);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(int userId);
    /*验证登录*/
    User loginCheck(@Param("loginName") String loginName ,@Param("password") String password);
}
