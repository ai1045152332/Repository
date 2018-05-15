package com.honghe.recordweb.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.ResourceBundle;

/**
 * Created by lyx on 2016-01-27.
 * 读取配置文件
 * hhrec 对应码：aGhyZWM=;hhtc对应码:aGh0Yw==;hhrec,hhtc对应码为:aGhyZWMsaGh0Yw==;hhrec,hhtc,htpr对应码为:aGhyZWMsaGh0YyxodHBy；hhtc,htpr对应码：aGh0YyxodHBy;hhtc,htpr,hhrec,hhtwb对应码：aGhyZWMsaGh0YyxodHByLGhodHdi
 */
public class ConfigureUtil {

    private static String softInfo = "";
    private static ResourceBundle resourceBundle = null;

    private ConfigureUtil() {
    }

    public static final String getVersionInfo(String key) {
        return resourceBundle != null && resourceBundle.containsKey(key) ? resourceBundle.getString(key) : "";
    }

    public static final String getSoftInfo() {
        return softInfo;
    }

    static {
        try {
            resourceBundle = ResourceBundle.getBundle("configure");
            if (resourceBundle.containsKey("soft")) {
                String info = resourceBundle.getString("soft");
                info = info.substring(2, info.length() - 2);
                byte[] code = decryptBASE64(info);
                String outputStr = new String(code);
                softInfo = outputStr;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }


    /**
     * 是否包含大屏设备
     *
     * @return
     */
    public static boolean isHhtc() {
        return softInfo.contains(CommonName.DEVICE_TYPE_SCREEN);
    }


    /**
     * 是否包含录播设备
     *
     * @return
     */
    public static boolean isHhrec() {
        return softInfo.contains(CommonName.DEVICE_TYPE_RECOURD);
    }

    /**
     * 是否包含投影机设备
     *
     * @return
     */
    public static boolean isHtpr() {
        return softInfo.contains(CommonName.DEVICE_TYPE_PROJECTOR);
    }
    /**
     * 是否包含白板一体机设备
     *
     * @return
     */
    public static boolean isHhtwb() {
        return softInfo.contains(CommonName.DEVICE_TYPE_WHITEBOARD);
    }
    /**
     * 是否只包含录播设备
     *
     * @return
     */
    public static boolean isOnlyHhrec() {
        boolean hhrec = false;
        if (isHhtc() == false && isHhrec() == true && isHtpr() == false && isHhtwb() == false) {
            hhrec = true;
        }
        return hhrec;
    }

    /**
     * 是否只包含大屏设备
     *
     * @return
     */
    public static boolean isOnlyHttc() {
        boolean htpr = false;
        if (isHhtc() == true && isHhrec() == false && isHtpr() == false && isHhtwb() == false) {
            htpr = true;
        }
        return htpr;
    }

    /**
     * 是否只包含投影仪设备
     *
     * @return
     */
    public static boolean isOnlyHtpr() {
        boolean htpr = false;
        if (isHhtc() == false && isHhrec() == false && isHtpr() == true && isHhtwb() == false) {
            htpr = true;
        }
        return htpr;
    }
    /**
     * 是否只包含白板一体机设备
     *
     * @return
     */
    public static boolean isOnlyHhtwb() {
        boolean htpr = false;
        if (isHhtc() == false && isHhrec() == false && isHtpr() == false && isHhtwb() == true) {
            htpr = true;
        }
        return htpr;
    }

    public static boolean isAll() {
        return softInfo.contains(CommonName.DEVICE_TYPE_PROJECTOR) && softInfo.contains(CommonName.DEVICE_TYPE_RECOURD) && softInfo.contains(CommonName.DEVICE_TYPE_SCREEN)&& softInfo.contains(CommonName.DEVICE_TYPE_WHITEBOARD);
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


    public static void main(String arg[]) {


//        String info = "hhaGh0YyxoaHRyZWMsaHRwcg==tc";
//        info = info.substring(2, info.length() - 2);

        try {
//            String code = encryptBASE64(info.getBytes());
//            System.out.println("加密后:" + code);
//            byte[] str = decryptBASE64(code);
//            System.out.println("解密后:" + String.valueOf(str));
            String info = "hhrec,hhtc,htpr,hhtwb";
            byte[] inputData = info.getBytes();
            String code = encryptBASE64(inputData);

            System.out.println("BASE64加密后:\n" + code);

            byte[] output = decryptBASE64(code);

            String outputStr = new String(output);

            System.out.println("BASE64解密后:\n" + outputStr);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
