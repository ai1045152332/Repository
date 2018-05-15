package com.honghe.recordweb.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by zhanghongbin on 2015/1/21.
 */
public final class IOUtil {

    private final static Logger logger = Logger.getLogger(IOUtil.class);

    private IOUtil() {

    }

    /**
     * todo 加注释
     * @param file
     * @param filePath
     * @return
     */
    public static final boolean write(File file, String filePath) {
        try {
            IOUtils.write(IOUtils.toByteArray(new FileInputStream(file)), new FileOutputStream(filePath));
        } catch (Exception e) {
            logger.error("写文件异常", e);
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * todo 加注释
     * @param filePath
     * @return
     */
    public final static boolean delete(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static void main(String[] args) {
        System.out.println(IOUtil.delete("d:/aa.png"));
    }
}
