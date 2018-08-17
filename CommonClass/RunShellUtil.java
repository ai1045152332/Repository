package com.honghe.managerTool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 运行shell工具类
 */
public class RunShellUtil {

    private static RunShellUtil instance=null;
    Logger logger = LoggerFactory.getLogger(RunShellUtil.class);


    private RunShellUtil(){}

    public static synchronized RunShellUtil getInstance(){
        if(instance==null){
            instance = new RunShellUtil();
        }
        return instance;
    }

    public boolean execute(String filePath) {
        try {
            String shpath = filePath;
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}