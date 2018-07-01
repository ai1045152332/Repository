package com.zjy.blog.blog_start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 处理登陆等  主页控制器
 * @author zjy
 *20180630
 */

@Controller
public class MainController {
	//显示首页
	@GetMapping("/")
	public String root(){
		return "redirect:/index";
	}
	@GetMapping("/index")
	public String index(){
		return "/index";
	}
	//登陆界面
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	//登陆失败 返回登录界面+提示信息
	@GetMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("loginError",true);
		model.addAttribute("errorMsg","登录失败,用户名或密码错误!");
		return "login";
	}
	//注册
	@GetMapping("/register")
	public String register(){
		return "register";
	}
}
