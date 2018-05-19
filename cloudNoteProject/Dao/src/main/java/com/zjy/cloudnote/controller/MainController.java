package com.zjy.cloudnote.controller;

import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cloudnote")
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public String index(ModelMap map){
        System.out.println("ni hao ");
        map.addAttribute("name", "thymeleaf-imooc");
        return "/login/login";
    }

    @PostMapping(value="/login")
    public String sayHello(@RequestParam("username") String loginName,
                           @RequestParam("password") String password,
                           @RequestParam("code") String text,
                           HttpSession session){
        String returnLoginName = userService.loginCheck(loginName, password);
        if(returnLoginName!=null){
            System.out.println(session);
            session.setAttribute("loginName",returnLoginName);
            session.setAttribute("id",11);
            return "page/home/home";
        }else{
            return "login/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("");
        return "login/login";
    }

}
