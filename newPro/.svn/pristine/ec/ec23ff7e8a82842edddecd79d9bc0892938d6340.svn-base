package com.honghe.recordweb.service.frontend.settings;

import com.honghe.recordhibernate.dao.FtpSettingDao;
import com.honghe.recordhibernate.entity.Ftpsetting;
import com.honghe.recordweb.service.frontend.ftp.Ftp4jService;
import it.sauronsoftware.ftp4j.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hthwx on 2015/3/26.
 */
@Service
public class FtpSettingService {
    private final static Logger logger = Logger.getLogger(FtpSettingService.class);

    @Resource
    private FtpSettingDao ftpSettingDao;
    @Resource
    private Ftp4jService ftp4jService;

    /**
     * 修改ftp设置
     *
     * @param map
     * @return
     */
    @Transactional
    public boolean updateFtpSetting(Map<String, String> map) {
        try {
            Ftpsetting ftpSetting = ftpSettingDao.getFtpSetting();
            boolean isAdd = false;
            if (ftpSetting == null) {
                ftpSetting = new Ftpsetting();
                isAdd = true;
            }
            if (map.containsKey("ftp_addr") && map.get("ftp_addr") != null) {
                ftpSetting.setFtpAddr(map.get("ftp_addr"));
            }
            if (map.containsKey("ftp_port") && map.get("ftp_port") != null) {
                ftpSetting.setFtpPort(Integer.parseInt(map.get("ftp_port").toString()));
            }
            if (map.containsKey("ftp_user") && map.get("ftp_user") != null) {
                ftpSetting.setFtpUser(map.get("ftp_user"));
            }
            if (map.containsKey("ftp_pass") && map.get("ftp_pass") != null) {
                ftpSetting.setFtpPass(map.get("ftp_pass"));
            }
            if (isAdd) {
                ftpSettingDao.addFtpSetting(ftpSetting);
            } else {
                ftpSettingDao.updateFtpSetting(ftpSetting);
            }
            return true;
        } catch (Exception e) {
            logger.error("修改ftp设置异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取ftp信息
     *
     * @return map<String,String>
     */
    public Map<String, String> getFtp() {
        Map<String, String> map = new HashMap<>();
        try {
            Ftpsetting ftpSetting = ftpSettingDao.getFtpSetting();
            if (ftpSetting != null) {
                map.put("id", ftpSetting.getId() + "");
                map.put("ftp_addr", ftpSetting.getFtpAddr());
                map.put("ftp_port", ftpSetting.getFtpPort() + "");
                map.put("ftp_user", ftpSetting.getFtpUser());
                map.put("ftp_pass", ftpSetting.getFtpPass());
                map.put("ftp_remark", ftpSetting.getFtpRemark());
            }
        } catch (Exception e) {
            logger.error("获取ftp信息异常", e);
//            e.printStackTrace();
        }
        return map;
    }

    /**
     * FTP测试连接
     *
     * @param addr FTP地址
     * @param port 端口
     * @param user 用户名
     * @param pass 密码
     * @return boolean 连接成功或失败
     */
    public boolean testFTPConnect(String addr, String port, String user, String pass) {
        boolean flag = false;
        try {
            FTPClient ftpClient = ftp4jService.run(addr, Integer.parseInt(port), user, pass);
            if (ftpClient != null) {
                flag = true;
                ftp4jService.disconnect(ftpClient, false);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("FTP测试连接异常！", e);
        }
        return flag;
    }
}
