package com.zjy.cloudnote.controller;

import com.zjy.cloudnote.Util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cloudnote/redis")
public class RedisController {

    @Autowired
    private RedisOperator redisOperator;

    @RequestMapping("/test")
    public String test(){
        redisOperator.set("love","嘻嘻");
//        redisOperator.del("key");
        return redisOperator.get("love");
    }
}
