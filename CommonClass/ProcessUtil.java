package com.honghe.managerTool.util;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ProcessUtil {

    public static final void setProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Integer pid = Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
        System.out.println("PID:"+pid);
        try {
            File file = new File("pid.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(pid);// 往文件里写入字符串
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
            System.out.print("创建文件失败");
        }

    }
}