package com.honghe.recordweb.util.base.util;

/** 
 * @author hucl
 * 
 * 自定义异常类
 */  
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException  
{  
    public BusinessException(String frdMessage)  
    {  
        super(createFriendlyErrMsg(frdMessage));  
    }  
  
    public BusinessException(Throwable throwable)  
    {  
        super(throwable);  
    }  
  
    public BusinessException(Throwable throwable, String frdMessage)  
    {  
        super(throwable);  
    }  
  
    private static String createFriendlyErrMsg(String msgBody)  
    {  
        String prefixStr = "抱歉，";  
        String suffixStr = " 请稍后再试或与管理员联系！";  
  
        StringBuffer friendlyErrMsg = new StringBuffer("");  
  
        friendlyErrMsg.append(prefixStr);  
  
        friendlyErrMsg.append(msgBody);  
  
        friendlyErrMsg.append(suffixStr);  
  
        return friendlyErrMsg.toString();  
    }  
}  