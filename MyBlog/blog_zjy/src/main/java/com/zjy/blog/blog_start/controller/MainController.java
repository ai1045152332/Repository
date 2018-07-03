package com.zjy.blog.blog_start.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.zjy.blog.blog_start.domain.Authority;
import com.zjy.blog.blog_start.domain.User;
import com.zjy.blog.blog_start.service.AuthorityService;
import com.zjy.blog.blog_start.service.UserService;

/**
 * 处理登陆等  主页控制器
 * @author zjy
 *20180630
 */

@Controller
public class MainController {
	
	//普通用户ID权限为2
	private static final Long ROLE_USER_AUTHORITY_ID = 2L;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;
	
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
	@PostMapping("/login")
	public String login2(@PathVariable("username") String username,
			@PathVariable("password") String password){
		System.out.println("========="+username+password+"===========");
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
		return "/register";
	}
	//注册
	@PostMapping("/register")
    public String registerUser(User user) {
		String email = user.getEmail();
		//邮箱重复,注册失败!!!
		
		//设置权限
        List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        
		userService.registerUser(user);
		return "redirect:/login";
	}
}
