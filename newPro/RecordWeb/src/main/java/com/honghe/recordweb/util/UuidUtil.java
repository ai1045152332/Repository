package com.honghe.recordweb.util;

import java.util.UUID;

/**
 * Created by lyx on 2016-06-12.
 */
public class UuidUtil {

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //“-”符号替换成"."
        s = s.replaceAll("-",".");
        return s;
    }
    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number){
        if(number < 1){
            return null;
        }
        String[] ss = new String[number];
        for(int i=0;i<number;i++){
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args){
        String[] ss = getUUID(10);
        for(int i=0;i<ss.length;i++){
            System.out.println(ss[i]);
        }
    }

}
