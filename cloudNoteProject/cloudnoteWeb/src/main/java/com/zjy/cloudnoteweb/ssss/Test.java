package com.zjy.cloudnoteweb.ssss;

import com.zjy.cloudnote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {
    @Autowired
    private static UserService userService;

    public static void main(String[] args){
        System.out.println(userService.queryUser());
    }
}
