package com.zjy.user.controller;

import com.zjy.user.dao.UserDao;
import com.zjy.user.entity.User;
import com.zjy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /*输出所有user*/
    @GetMapping("/queryUser")
    public Map<String,Object> queryUser() {
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        List<User> list = userService.queryUser();
        modelMap.put("areaList",list);
        return modelMap;
    }
    /*按照id查找user*/
    @GetMapping("/queryOneUser/{id}")
    public Map<String,Object> queryOneUser(@PathVariable Integer id) {
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        System.out.println(id);
        User user = userService.queryUserById(id);
        modelMap.put("user",user);
        return modelMap;
    }
    /*register注册用户   post*/
    @RequestMapping(value="/addUser" , method = RequestMethod.POST)
    private Map<String, Object> addUser(@RequestBody User user){/*传入json*/
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",userService.registerUser(user));
        return modelMap;
    }
    /*修改区域信息*/
    @RequestMapping(value="/modifyUser" , method = RequestMethod.POST)
    private Map<String, Object> modifyUser(@RequestBody User user){/*传入json*/
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",userService.updateUser(user));
        return modelMap;
    }
    @RequestMapping(value="/removeUser" , method = RequestMethod.GET)
    private Map<String, Object> removeUser(Integer userId){
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("success",userService.deleteUser(userId));
        return modelMap;
    }
}
