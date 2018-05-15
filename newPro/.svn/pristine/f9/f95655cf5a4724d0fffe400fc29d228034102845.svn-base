package com.honghe.recordweb.util;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

/**
 * Created by zhanghongbin on 2014/11/25.
 */
public final class GZip {

    private GZip() {

    }

    public final static String compress(String data) {
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        // 使用默认缓冲区大小创建新的输出流
        try {
            gzip = new GZIPOutputStream(out);
            // 将 b.length 个字节写入此输出流
            gzip.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (Exception e) {

                }
                return out.toString();
            }
            return data;
        }

    }


}
