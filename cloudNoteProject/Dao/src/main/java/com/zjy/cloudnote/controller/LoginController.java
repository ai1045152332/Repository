package com.zjy.cloudnote.controller;

import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cloudnote")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/login")
    public String sayHello(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("code") String text){
        boolean flag = userService.loginCheck(username, password);
        return "hello "+username+password+text + flag;
    }

    @GetMapping(value="/login2")
    public ModelAndView findProjectPage() {
        System.out.println("this");
        ModelAndView modelAndView = new ModelAndView("http://localhost:63342/cloudnote/login.html?_ijt=6h5aegurrvcgpkm8ctqk4t2amc");
        return modelAndView;
    }

}
