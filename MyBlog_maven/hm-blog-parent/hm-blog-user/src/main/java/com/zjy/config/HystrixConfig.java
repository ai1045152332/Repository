package com.zjy.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.apache.http.client.config.RequestConfig;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(120 * 1000)
                .setSocketTimeout(120 * 1000).setConnectTimeout(120 * 1000).build();
        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registration.addUrlMappings("/hystrix.stream");
        return registration;
    }
}
