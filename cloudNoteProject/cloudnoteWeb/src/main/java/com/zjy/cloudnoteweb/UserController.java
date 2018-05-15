package com.zjy.cloudnoteweb;

import com.zjy.cloudnote.entity.User;
import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/userList",method = RequestMethod.GET)
    private Map<String ,Object> listUser(){
        Map<String,Object> map = new HashMap<>();
        List list = userService.queryUser();
        map.put("userList",list);
        return map;
    }
    @RequestMapping(value="/getuserbyid",method = RequestMethod.GET)
    private Map<String ,Object> getUserById(Integer userId){
        Map<String,Object> map = new HashMap<>();
        User user = userService.queryUserById(userId);
        map.put("user",user);
        return map;
    }
    @RequestMapping(value="/adduser",method = RequestMethod.POST)
    private Map<String ,Object> addUser(Integer userId){
        Map<String,Object> map = new HashMap<>();
        User user = userService.queryUserById(userId);
        map.put("user",user);
        return map;
    }
}
