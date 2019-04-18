package com.honghe.managerTool.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class HttpRequest {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

    @Resource
    private RestTemplate restTemplate;

    public Object get(String url){
        return restTemplate.getForEntity(url, String.class).getBody();
    }

    public Object post(String url, Map<String,String> params) {
        return restTemplate.postForEntity(url, params, String.class).getBody();
    }
}
