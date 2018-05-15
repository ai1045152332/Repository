package com.zjy.chuanzhispringboot;

import com.zjy.chuanzhispringboot.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("say")
    public String hello(){
       // System.out.println(userService.queryUserList());
        return "hello飞行机器人";
    }
}
