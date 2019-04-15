package com.zjy.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 配置客户端rest访问配置
 *
 * @author zhaojianyu
 * @create 2018-12-21 8:49 PM
 */
@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig(){
        return "application:"+applicationName+"<br/>"+
                "eurekaServer:"+eurekaServer+"<br/>"+
                "port:"+port+"<br/>";
    }
}