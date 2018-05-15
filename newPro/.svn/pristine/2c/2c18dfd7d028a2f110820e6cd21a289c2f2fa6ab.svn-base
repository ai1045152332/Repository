package com.honghe.recordweb.util;

import com.honghe.authority.AuthorityCheck;
import com.honghe.authority.AuthorityInfo;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author zhanghongbin
 * @date 2015/9/10
 */
public final class LicenseUtils {

    private final static Logger logger = Logger.getLogger(LicenseUtils.class);
    //等待申请通过
    public final static String STATE_WAITING_EXAMINE = AuthorityInfo.STATE_WAITING_EXAMINE;
    //标识串未绑定
    public final static String STATE_UN_REG = AuthorityInfo.STATE_UN_REG;
    //申请未通过
    public final static String STATE_NOT_PASS_EXAMINE = AuthorityInfo.STATE_NOT_PASS_EXAMINE;

    //校园版录播设备数量最大值
    public final static int  HHTREC_DEVICE_MAXNUM = 600;

    //软件代码
    public static String softCode = "22";
    //软件安装路径
    public static String path = "D:";
    //软件版本
    public static String ver = "2.0.1";

    //未注册时，默认设备数量
    public static final int DEVICE_NUM_DEFAULT = 5;

    public static final String HHTC_DEVICE_NUM = "hhtc_device_num";
    public static final String HHTREC_DEVICE_NUM = "hhtrec_device_num";

    private LicenseUtils() {

    }

    /**
     * 验证
     *
     * @return
     */
    public static Map<String, String> findLicense() {

        boolean flag = AuthorityCheck.authorityCheck(softCode, path, ver);
        AuthorityInfo authorityInfo = AuthorityCheck.info.get(softCode);
        logger.debug(authorityInfo.getMessage() + "---" + authorityInfo.getState());
        if (!flag) return Collections.EMPTY_MAP;
        return authorityInfo.getDataMap();
    }


    /**
     * 注册
     *
     * @param _code               注册码
     * @param _applicationname    申请人姓名
     * @param _applicationcompany 学校/公司名称
     * @param _applicationtell    电话
     * @param _applicationorder   合同单号
     * @param _type               注册方式：1，授权码注册；2.网络激活
     * @return
     */
    public static String regLicense(String _code, String _applicationname, String _applicationcompany, String _applicationtell, String _applicationorder, String _type) {

        //用于保存申请人资料
        HashMap<String, String> hashMap = new HashMap<String, String>();
        boolean flag = true;
        if ("1".equals(_type)) {
            flag = AuthorityCheck.registration(_code, softCode, path, ver);
        }
        if ("2".equals(_type)) {
            hashMap.put("applicationname", _applicationname);
            hashMap.put("applicationcompany", _applicationcompany);
            hashMap.put("applicationtell", _applicationtell);
            hashMap.put("applicationorder", _applicationorder);
            flag = AuthorityCheck.registration(softCode, path, ver, hashMap);
        }
        if (flag) return "";
        AuthorityInfo authorityInfo = AuthorityCheck.info.get(softCode);
        logger.debug(authorityInfo.getMessage() + "---" + authorityInfo.getState());
        return authorityInfo.getMessage();
    }


    /**
     * 验证是否注册授权码
     *
     * @return
     */
    public static boolean isRegister() {

        boolean flag = AuthorityCheck.authorityCheck(softCode, path, ver);

        if (!flag) {
            AuthorityInfo authorityInfo = AuthorityCheck.info.get(softCode);

            if (null != authorityInfo) {
                String state = authorityInfo.getState();

                if (state == AuthorityInfo.STATE_ON_TIME || state == AuthorityInfo.STATE_REG_SUCCESS) {
                    flag = true;
                }
            }

        }

        return flag;
    }

    /**
     * 获取状态
     *
     * @return
     */
    public static String getState() {
        String state = AuthorityInfo.STATE_UN_REG;

        AuthorityInfo authorityInfo = AuthorityCheck.info.get(softCode);
        if (null != authorityInfo) {
            state = authorityInfo.getState();
        }

        return state;
    }

    /**
     * 获取注册设备的数量，如果未注册，默认为5台
     *
     * @param type 设备类型  大屏：hhtc_device_num 录播：hhtrec_device_num
     * @return
     */
    public static int getDeviceNum(String type) {
        int re_value = DEVICE_NUM_DEFAULT;
        Map<String, String> licenseInfo = findLicense();
        if (!licenseInfo.isEmpty() && licenseInfo.get(type) != null) {
            re_value = Integer.parseInt(licenseInfo.get(type));
        }
        return re_value;
    }


    public static void main(String[] args) {
        System.out.println(regLicense("QBD1E390M089665C8B06", "李国强", "保定三中", "15075308659", "HT-010010", "2"));
        System.out.println(LicenseUtils.findLicense());
    }
}
