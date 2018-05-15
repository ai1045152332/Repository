package com.honghe.recordweb.util;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by yanxue on 2015-07-23.
 */
public class CommonName {
    private final static Logger logger = Logger.getLogger(CommonName.class);

    public final static String DEVICE_TYPE_SCREEN = "hhtc";  //大屏设备
    public final static String DEVICE_TYPE_RECOURD = "hhrec"; //录播设备
    public final static String DEVICE_TYPE_PROJECTOR = "htpr"; //投影仪设备
    public static final String DEVICE_TYPE_WHITEBOARD = "hhtwb";//电子白板
    public final static String DEVICE_TYPE_UNKNOWN = "unknown"; //未知设备
    public final static String DEVICE_TYPE_ALL = ""; //所有设备
    public final static String DEVICE_NVR = "nvr"; //对接目录服务 device_type表定义字段 add mz
    public final static String DEVICE_NVR_JP = "2"; //精品录播主机对应的dspecid
    public final static String DEVICE_NVR_JK = "17"; //虚拟录播主机对应的dspecid

    //集控方法调用时，需要的参数， 详见 设备服务接口api文档
    public final static String METHOD_TYPE_DEVICE = "device";
    public final static String METHOD_TYPE_DEVICECOMMAND = "deviceCommand";
    //大屏设备命令标识
    public final static String METHOD_TYPE_SCREENCOMMAND = "screenCommand";
    //录播设备命令标识
    public final static String METHOD_TYPE_RECORDCOMMAND = "recordCommand";
    //投影机设备命令标识
    public final static String METHOD_TYPE_PROJECTORCOMMAND = "projectorCommand";
    //电子白板设备命令标识
    public static final String METHOD_TYPE_WHITEBOARD = "whiteboardCommand";

    public final static String METHOD_TYPE_ALL = "";
    //统一退出地址
    public static String LOGOUT_PATH = ServletActionContext.getRequest().getContextPath()+"/login.jsp";

    /**
     * 对接目录服务获取设备树时根据返回的设备类型返回对应的设备图标
     * @param type 设备类型
     * @return
     */
    public static String getDeviceIcon(String type,String dspecId){
        String str = "";
        if (DEVICE_TYPE_SCREEN.equals(type)){//大屏图标class值
            str = "hhtc_icon";
        }else if (DEVICE_NVR.equals(type)){//录播图标class值
            str = "tree_jy";
            if ((DEVICE_NVR_JP).equals(dspecId)){//精品录播
                str = "tree_jp";
            }
            if ((DEVICE_NVR_JK).equals(dspecId)){//虚拟录播设备
                str = "tree_jk";
            }
        }
        return str;
    }
    /**
     * 测试log4j输出
     *
     * @param arg
     */
    public static void main(String[] arg) {
        File file = new File("d:\\adfasf.txt");
        try {
            InputStream input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.info(" e.printStackTrace(),输出：");
            e.printStackTrace();
            logger.info("logger.error(e.toString())，输出：");
            logger.error(e.toString());
            logger.info("logger.error(e)，输出：");
            logger.error("", e);
            logger.info("logger.error(\"\",e)，输出：");
            logger.error("错误", e);

        }
    }
}