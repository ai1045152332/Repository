package com.zjy.cloudnote.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cloudnote")
public class LoginController {

    @PostMapping(value="/login")
    public String sayHello(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("code") String text){
        return "hello "+username+password+text;
    }

    @GetMapping(value="/login2")
    public ModelAndView findProjectPage() {
        System.out.println("this");
        ModelAndView modelAndView = new ModelAndView("http://localhost:63342/cloudnote/login.html?_ijt=6h5aegurrvcgpkm8ctqk4t2amc");
        return modelAndView;
    }

}
