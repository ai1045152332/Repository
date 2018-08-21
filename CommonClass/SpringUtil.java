package com.honghe.managerTool.util;


import com.honghe.managerTool.config.annotation.SQLFileAnnotation;
import com.honghe.managerTool.controller.CommandController;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
@SQLFileAnnotation(SQLFileName = "managerTool.sql")
public class SpringUtil {

    private static ApplicationContext applicationContext = null;
    static org.slf4j.Logger logger = LoggerFactory.getLogger(CommandController.class);

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);

    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static void scannerBeans(){

        //扫描SQL文件注解
        String[] sqlFiles = applicationContext.getBeanNamesForAnnotation(SQLFileAnnotation.class);
        List<String> sqlFileNameList = new ArrayList<>();
        for(String string:sqlFiles){
            Object sqlFile = applicationContext.getBean(string);
            String sqlFileName = sqlFile.getClass().getAnnotation(SQLFileAnnotation.class).SQLFileName();
            sqlFileNameList.add(sqlFileName);
        }
        executeSql(sqlFileNameList);

    }

    private static void executeSql(List<String> sqlFileNameList){

        Collections.sort(sqlFileNameList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.contains("-")){
                    o1 = o1.substring(o1.indexOf("-") + 1,o1.length());
                }
                if(o2.contains("-")){
                    o2 = o2.substring(o2.indexOf("-") + 1,o2.length());
                }
                return o1.compareTo(o2);
            }
        });

        for(String name : sqlFileNameList){
            String importedDBs = getImportedDBs();
            if(importedDBs.contains(name)){
                logger.debug("数据库已导入，不再重复导入！！");
                continue;
            }
            boolean isSuccess = false;
            try {
                //读取sql文件
                String content = IOUtils.toString(new FileInputStream(PathUtil.getPath(PathUtil.PathType.CONFIG)+ name),"utf-8");
                isSuccess = SQLExecuteUtils.geInstance().executeUserSQLFile(content,"managerTool",";");//注意：sql脚本中以##（替换;）表示一句完整语句
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(isSuccess){
                updateImportedDB(importedDBs + "\r\n" + name);
            }
        }
    }

    private static String getImportedDBs(){
        StringBuilder importedDB=new StringBuilder();
        try {
            File file =new File(PathUtil.getPath(PathUtil.PathType.CONFIG) + "dbImport.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream in=new FileInputStream(file);
            byte[] b=new byte[1024];
            int i;
            while((i=in.read(b))>0){
                importedDB.append(new String(b,0,i));
            }
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return importedDB.toString();
    }

    private static void updateImportedDB(String string){
        try {
            File file =new File(PathUtil.getPath(PathUtil.PathType.CONFIG) + "dbImport.txt");
            FileOutputStream out =new FileOutputStream(file);
            byte[] newb = string.getBytes();
            out.write(newb);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void getPath() throws IOException {
        String path = SpringUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("path: "+path+"/config");
        JarFile localJarFile = null;
        try {
            localJarFile = new JarFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<JarEntry> entries = localJarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            System.out.println(jarEntry.getName());
            String innerPath = jarEntry.getName();
            if(innerPath.startsWith("conf")){
                InputStream inputStream = SpringUtil.class.getClassLoader().getResourceAsStream(innerPath);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while((line=br.readLine())!=null){
                    System.out.println(innerPath+"内容为:"+line);
                }
            }
        }
    }



}