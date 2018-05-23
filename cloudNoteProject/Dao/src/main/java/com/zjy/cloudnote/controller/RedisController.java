package com.zjy.cloudnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cloudnote/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    public String test(){
        stringRedisTemplate.opsForValue().set("key","hello 赵健宇");
        System.out.print(stringRedisTemplate.opsForValue().get("key"));
        return stringRedisTemplate.opsForValue().get("key");
    }
}
