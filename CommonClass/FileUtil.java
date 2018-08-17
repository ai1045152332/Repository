package com.honghe.managerTool.util;

import java.io.*;

/**
 * 文件读取
 *
 * @author zhaojianyu
 */
public class FileUtil {
    public static void main(String[] args){

        String[] split = readLine("d:\\processInfo.txt",0).split("\\s+");
        if(split!=null){
            System.out.println(split[2]+"%");
            System.out.println(split[3]+"%");
        }
    }

    public static String readLine(String fileName,int lineNum){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String strOneLine = null;
            int i = 0;
            while ((strOneLine = br.readLine()) != null) {
                if(i==lineNum){
                    return strOneLine;
                }
                i++;
            }
            br.close();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String read(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String strOneLine = null;
            StringBuffer s = new StringBuffer("");
            int i = 0;
            while ((strOneLine = br.readLine()) != null) {
                i++;
                s.append(strOneLine);
            }
            br.close();
            return s.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void write(String s[],String fileName){
        try {
            FileWriter file = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(file);
            for (int i = 0; i < s.length; i++) {
                bw.write(s[i]);
                bw.newLine();
            }
            bw.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

