package com.zjy.chuanzhispringboot;

import com.zjy.dao.UserDao;
import com.zjy.dao.UserDaoImpl;
import com.zjy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired // 注入Spring容器中的bean对象
    private UserDaoImpl userDAOImpl;

    @Autowired
    private UserDao userDao;

    public List<User> queryUserList() {
        // 调用userDAO中的方法进行查询
        System.out.println(userDao.queryUserList());
        return this.userDAOImpl.queryUserList();
    }

}