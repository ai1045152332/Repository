package com.zjy.chuanzhispringboot;

import com.zjy.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@Configuration //通过该注解来表明该类是一个Spring的配置，相当于一个xml文件
@ComponentScan(basePackages = "com.zjy.chuanzhispringboot") //配置扫描包
@PropertySource(value= {"classpath:jdbc.properties"})
public class SpringConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Bean // 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
    public UserDaoImpl getUserDao(){
        return new UserDaoImpl(); // 直接new对象做演示
    }
/*
    @Bean // 通过该注解来表明是一个Bean对象，相当于xml中的<bean>
    public UserService getUserService(){
        return new UserService(); // 直接new对象做演示
    }*/

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter  =
                new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

}