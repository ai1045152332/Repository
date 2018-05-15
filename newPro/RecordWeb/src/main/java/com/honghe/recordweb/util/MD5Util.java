package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanghongbin on 2015/1/28.
 */
public class MD5Util {

    private final static Logger logger = Logger.getLogger(MD5Util.class);

    private MD5Util() {

    }

    /**
     * 方法1
     *
     * @param source
     * @return 16进制整数字符串格式
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] =
                { // 用来将字节转换成 16 进制表示的字符
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
                        'c', 'd', 'e', 'f'
                };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            // MD5 的计算结果是一个 128 位的长度整数，
            byte tmp[] = md.digest();

            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {   // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移（即无符号右移），将符号位一起右移

                // 取字节中低 4 位的数字转换
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
        return s;
    }

    /**
     * MD5加码。32位
     * @param inStr
     * @return
     */
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        inStr = inStr + "hitevision";
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }


    /**
     * 可逆的加密算法
     * @param inStr
     * @return
     */
    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    /**
     * 加密后解密
     * @param inStr
     * @return
     */
    public static String JM(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    /**
     * todo 加注释
     * @param s
     * @return
     */
    public final static boolean ipValid(String s) {
        String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
        String regex1 = "1\\d{2}";
        String regex2 = "[1-9]\\d";
        String regex3 = "\\d";
        String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|(" + regex3 + ")";
        regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex + ")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }


    /**
     * 测试主函数
     * @param args
     */
    public static void main(String args[]) {
        String s = new String("hhtc&&hhtrec_");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + MD5(s));
        System.out.println("MD5后再加密：" + KL(MD5(s)));
        System.out.println("解密为MD5后的：" + JM(KL(MD5(s))));


        String _type = "hhtc,";
        String[] types = _type.split(",");
        System.out.println(types.toString());
    }
}
