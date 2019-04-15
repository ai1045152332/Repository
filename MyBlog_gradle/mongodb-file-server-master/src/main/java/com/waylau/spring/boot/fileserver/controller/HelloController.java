package com.waylau.spring.boot.fileserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 心跳检测
 */
@RestController
public class HelloController {

	@RequestMapping("/ping")
	public String hello() {
	    return "pong";
	}
}
