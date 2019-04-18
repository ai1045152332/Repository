package com.zjy.blog.blog_start.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * simple http request
 *
 * @author zhaojianyu
 * @date 2019/4/18
 */
@Component
public class HttpUtils {

    @Autowired
    private RestTemplate restTemplate;

    public Object get(String url){
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public Object post(String url, Map<String,String> params) {
        return restTemplate.postForEntity(url, params, String.class).getBody();
    }
}
