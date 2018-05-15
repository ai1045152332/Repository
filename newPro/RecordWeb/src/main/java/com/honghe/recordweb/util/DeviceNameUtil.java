package com.honghe.recordweb.util;

/**
 * Created by lyx on 2016-06-22.
 */
public class DeviceNameUtil {
    public final static int DEVICE_NORMALIZATION_RECOURD = 0; //常态化录播主机
    public final static int DEVICE_FINE_RECOURD = 1; //精品录播主机
    public static final int DEVICE_EMBEDDED_RECOURD = 2;//嵌入式精品录播主机(ZJ0500)
    public final static int DEVIC_SCREEN = 3;  //大屏设备
    public final static int DEVICE_TBOX_RECOURD = 4; //TBOX
    // 添加wbox
    public final static int DEVICE_WBOX_RECOURD = 20; //WBOX
    public final static int DEVICE_PROJECTOR = 5; //投影机
    public final static int DEVICE_CLASSROOMMONITOR = 6; //教室监控
    public final static int DEVICE_WHITEBOARD = 7; //白板一体机

    /**
     * 根据设备编号 获取厂家
     *
     * @param typeInt 设备编号
     * @return
     */
    public static String getFactoryByTypeInt(String typeInt) {
        String re_value = "";

        if (ConfigureUtil.isHhrec()) {//录播设备
            re_value = "HiteTech";
            if (String.valueOf(DEVICE_EMBEDDED_RECOURD).equals(typeInt)) { //Arec 设备
                re_value = "Arec";
            } else if (String.valueOf(DEVICE_TBOX_RECOURD).equals(typeInt)) { //TBOX设备
                re_value = "Touch";
            } else if (String.valueOf(DEVICE_WBOX_RECOURD).equals(typeInt)) { //WBOX设备
                re_value = "honghe-wbox";
            }
        }

        if (ConfigureUtil.isHhtc()) {
            if (String.valueOf(DEVIC_SCREEN).equals(typeInt)) { //大屏设备
                re_value = "Screen";
            }
        }

        if (ConfigureUtil.isHtpr()) {
            if (String.valueOf(DEVICE_PROJECTOR).equals(typeInt)) {  //投影机设备
                re_value = "HiteTech-Projector";
            }
        }

        if (ConfigureUtil.isHhtwb()) {
            if (String.valueOf(DEVICE_WHITEBOARD).equals(typeInt)) { //白板一体机
                re_value = "HiteTech-WhiteBoard";
            }
        }
        return re_value;
    }
    /**
     * 获取设备对应的类型
     *
     * @param typeInt 具体设备编号
     * @return
     */
    public static String getDeviceType(String typeInt) {
        String re_value = CommonName.DEVICE_TYPE_UNKNOWN;

        if (ConfigureUtil.isHhrec()) { //录播设备
            re_value = CommonName.DEVICE_TYPE_RECOURD;
        }

        if (ConfigureUtil.isHhtc()) {
            if (String.valueOf(DEVIC_SCREEN).equals(typeInt)) { //大屏设备
                re_value = CommonName.DEVICE_TYPE_SCREEN;
            }
        }
        if (ConfigureUtil.isHtpr()) {
            if (String.valueOf(DEVICE_PROJECTOR).equals(typeInt)) {  //投影机设备
                re_value = CommonName.DEVICE_TYPE_PROJECTOR;
            }
        }
        if (ConfigureUtil.isHhtwb()) {
            if (String.valueOf(DEVICE_WHITEBOARD).equals(typeInt)) {//白板一体机
                re_value = CommonName.DEVICE_TYPE_WHITEBOARD;
            }
        }
        return re_value;
    }

}
