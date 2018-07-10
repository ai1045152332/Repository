package com.honghe.dmanager.common.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuk on 2016/12/16.
 */
public class ParamUtil {

    static Logger logger = LoggerFactory.getLogger(ParamUtil.class);
    private ParamUtil(){}
    /**
     * 某一个参数为空或不合法，返回true
     * @param objects
     * @return
     */

    public static boolean isOneEmpty(Object... objects){
        for(Object o : objects){
            if(o == null){
                return true;
            }
            if(o instanceof String){
                if("".equals(o)){
                    return true;
                }
            }else if(o instanceof Integer && Integer.parseInt(String.valueOf(o))<0){
                return true;
            }
        }
        return false;
    }

    /**
     * 所有参数都为空或不合法，返回true
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Object... objects){
        Boolean flag;
        int count = objects.length;
        for(Object o : objects){
            if(o == null){
                count = count -1;
            }
            if(o instanceof String){
                if("".equals(o)){
                    count = count -1;
                }
            }else if(o instanceof Integer && Integer.parseInt(String.valueOf(o))<=0){
                count = count -1;
            }
        }
        if(count == 0){
            flag = true;
        }else {
            flag = false;
        }
        return flag;

    }

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    /**
     * TODO
     * Convert String to long
     * @param value
     * @param def default value
     * @return
     */
    public static long toLong(String value, long def) {
        if (isEmpty(value)) {
            return def;
        }

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }

    /**
     * Convert String to int
     * @param value
     * @param def default value
     * @return
     */
    public static int toInt(String value, int def) {
        if (isEmpty(value)) {
            return def;
        }
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return def;
        }
    }


    public static Map calculatePage(int page,int pageSize){
        Map<String,Integer> map = new HashMap<>();
        if(page <=0){
            page = 1;
        }
        map.put("start",(page-1) * pageSize);
        map.put("size",pageSize);
        return map;
    }

    public static Integer changeObjectToInt(Object o){
        if(null==o||o.equals("")) return null;
        return  Integer.parseInt(o.toString());
    }
    public static String changeObjectToStr(Object o){
        if(null==o) return null;
        return  o.toString();
    }


    /**
     * 获取request参数
     *
     * @param request http请求信息
     * @return 参数map
     */
    public static  Map<String, Object> getParams(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();
        String type = request.getHeader("Content-Type");
        if (request.getMethod().equalsIgnoreCase("GET") || !type.contains("application/json")) {
            parameters = request.getParameterMap();
            int len;
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                len = entry.getValue().length;
                if (len == 1) {
                    params.put(entry.getKey(), entry.getValue()[0]);
                } else if (len > 1) {
                    params.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            try {
                ServletInputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line + "\n");
                }
                String strContent = content.toString();
                params = (Map<String, Object>) JSON.parse(strContent);
            } catch (IOException e) {
                Logger logger = LoggerFactory.getLogger(ParamUtil.class);
                logger.error("getParams" + TipsMessage.PARES_ERROR, e);
            }
        }
        return params;
    }

    /**
     * 验证传入的授权码是否符合要求
     * @param licenseCode 授权码
     * @param soft_type 软件代码信息，22集控设备（交互平板，白板），23班牌设备
     * @return
     */
    public static boolean check_licenseCode(String licenseCode,String soft_type){
        boolean result = false;
        char[] licenseCodeStr = licenseCode.toCharArray();
        StringBuilder tempCodeStr = new StringBuilder();
        //将授权码进行处理，每隔四位插入一个"-"字符
        for (int i=0;i<licenseCodeStr.length;i++){
            if (i!=0&&i%4==0){
                tempCodeStr.append('-');
            }
            tempCodeStr.append(licenseCodeStr[i]);
        }
        licenseCode = tempCodeStr.toString();
        if(licenseCode.length() != 24)return false;
        String testCode = licenseCode;
        StringBuilder resultCode = new StringBuilder();
        String tempinfo = soft_type+testCode.substring(1,2);
        resultCode.append(testCode.substring(0,2));
        resultCode.append(MD5Util.MD5(tempinfo).substring(0,2));
        resultCode.append("-");

        tempinfo = testCode.substring(0,4) + testCode.substring(0,4);

        resultCode.append(MD5Util.MD5(tempinfo).substring(0,3));

        tempinfo = testCode.substring(5,8);
        resultCode.append(MD5Util.MD5(tempinfo).substring(4,5));

        String key = testCode.substring(10,11);
        int keyPostion = getSoftType(key);
        String keyvalue = testCode.substring(keyPostion,keyPostion+1);
        resultCode.append("-");
        resultCode.append(testCode.substring(10,11));

        tempinfo = testCode.substring(0,4) + testCode.substring(5,9);
        resultCode.append(MD5Util.MD5(tempinfo).substring(6,9));
        resultCode.append("-");
        tempinfo = testCode.substring(5,9) + testCode.substring(10,11);
        resultCode.append(MD5Util.MD5(tempinfo).substring(6,10));
        resultCode.append("-");

        tempinfo = testCode.substring(10,11) + testCode.substring(0,4);
        resultCode.append(MD5Util.MD5(tempinfo).substring(6,10));

        resultCode.replace(keyPostion,keyPostion+1,keyvalue);

        String code = resultCode.toString().toUpperCase();

        if(code.equals(licenseCode)){
            result = true;
        }
        return result;
    }

    /**
     * 获取软件代码信息
     * @param key
     * @return
     */
    private static int getSoftType(String key){
        int keyPostion = 23;
        switch (key){
            case "T":
                keyPostion = 23;
                break;
            case "S":
                keyPostion = 22;
                break;
            case "R":
                keyPostion = 21;
                break;
            case "Q":
                keyPostion = 20;
                break;
            case "P":
                keyPostion = 18;
                break;
            case "O":
                keyPostion = 17;
                break;
            case "N":
                keyPostion = 16;
                break;
            case "M":
                keyPostion = 15;
                break;
            case "L":
                keyPostion = 13;
                break;
            case "K":
                keyPostion = 12;
                break;
            case "J":
                keyPostion = 11;
                break;
        }
    return keyPostion;
    }

    public static void main(String[] args) {
        String code = "BBB7F273Q683AD196770";
//        char[] SS = code.toCharArray();
//        System.out.println(code.toCharArray());
//        System.out.println(code.indexOf(4));
        System.out.println(check_licenseCode(code,"23"));
    }
}
