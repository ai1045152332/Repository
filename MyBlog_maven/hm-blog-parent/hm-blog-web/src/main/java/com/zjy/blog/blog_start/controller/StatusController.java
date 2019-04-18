package com.zjy.blog.blog_start.controller;

import com.zjy.blog.blog_start.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询服务状态
 *
 * @author zhaojianyu
 * @date 2019/4/18
 */
@RestController
public class StatusController {

    @Autowired
    private HttpUtils httpUtil;

    @RequestMapping("/status")
    public String status(){
        String s = (String) httpUtil.get("http://localhost:8080/hello");
        return "web :"+s;
    }

}
