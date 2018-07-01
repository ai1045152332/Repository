package com.zjy.blog.blog_start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zjy.blog.blog_start.domain.User;
import com.zjy.blog.blog_start.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 查询所有用户
	 * @param model
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("title", "用户管理");
		return new ModelAndView("users/list","userModel",model);
	}
	/**
	 * 根据id查询用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("{id}")
	public ModelAndView listUsers(@PathVariable("id") Long id,Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("title", "查看用户");
		model.addAttribute("user", user);
		return new ModelAndView("users/view","userModel",model);
	}
	/**
	 * 获取创建表单页面
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView createForm(Model model) {
		model.addAttribute("title", "创建用户");
		model.addAttribute("user", new User(null,null,null,null));
		return new ModelAndView("users/form","userModel",model);
	}
	/**
	 * 保存或者修改用户
	 * @param user
	 * @return
	 */
	@PostMapping
	public ModelAndView saveOrUpdateUser(User user) {
		userRepository.save(user);
		//重定向到用户列表
		return new ModelAndView("redirect:/users");
	}
	/**
	 * 根据id删除用户
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") Long id,Model model) {
		userRepository.delete(id);
		model.addAttribute("title", "查看用户");
		return new ModelAndView("redirect:/users");
	}
	/**
	 * 获取修改用户的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/modify/{id}")
	public ModelAndView modifyUser(@PathVariable("id") Long id,Model model) {
		User user = userRepository.findOne(id);
		model.addAttribute("title", "修改用户");
		model.addAttribute("user", user);
		return new ModelAndView("users/form","userModel",model);
	}
}
