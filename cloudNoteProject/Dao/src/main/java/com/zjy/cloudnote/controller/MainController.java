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

    /**
     * 进入登陆页面
     * @return
     */
    @RequestMapping(value="", method=RequestMethod.GET)
    public String index(){
        return "/login/login";
    }

    /**
     * 全局错误界面
     * @return
     */
    @GetMapping("/error")
    public String errorPage(){
        return "/page/404/404";
    }

    /**
     *登录检测
     */
    @PostMapping(value="/login")
    public String sayHello(@RequestParam("username") String loginName,
                           @RequestParam("password") String password,
                           @RequestParam("code") String text,
                           HttpSession session,
                           ModelMap map){
        String returnLoginName = userService.loginCheck(loginName, password);
        if(returnLoginName!=null){
            session.setAttribute("loginName",returnLoginName);
            session.setAttribute("id",11);
            //将信息放到th中
            map.addAttribute("loginName", returnLoginName);
            map.addAttribute("id",11);
            return "page/home/home";
        }else{
            /**
             * 此处应该返回用户名密码错误信息zjy
             */
            return "login/login";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("");
        return "login/login";
    }

}
