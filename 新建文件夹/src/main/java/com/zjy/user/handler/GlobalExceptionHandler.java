package com.zjy.user.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*针对不同service编写不同的处理方法*/
    @ExceptionHandler(value=Exception.class)/*value应该为抛出异常名*/
    @ResponseBody        /*不返回html页面,只返回错误信息*/
    private Map<String , Object> exceptionHandler(HttpServletRequest req , Exception e){
        Map<String,Object>  modelMap = new HashMap<String , Object>();
        modelMap.put("succes",false);
        modelMap.put("errMsg",e.getMessage());

        //写错误日志

        //返回给用户看的信息
        return modelMap;
    }

}
