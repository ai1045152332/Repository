package com.zjy.blog.blog_start.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContraller {

	@RequestMapping("/hello")
	public String hey(){
		return "hello world";
	}
}
