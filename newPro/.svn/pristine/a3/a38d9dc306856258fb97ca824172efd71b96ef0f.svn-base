package com.honghe.recordweb.service.frontend.user;

import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordhibernate.dao.UserDao;
import com.honghe.recordhibernate.entity.Device;
import com.honghe.recordhibernate.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * Created by wzz on 2014-09-26.
 */
@Component
public class CookieKeeplogin {

    private final static Logger logger = Logger.getLogger(CookieKeeplogin.class);

    @Resource
    private UserDao userDao;
    @Resource
    private DeviceDao deviceDao;

    /**
     * 本地用户Cookie和数据库验证
     *
     * @param cookieValue String
     * @return user 自定义User类
     * @throws Exception
     */
    public final User getCookieKeeplogin(String cookieValue) {
        User user = null;
        String[] split = cookieValue.split(",");
        if (split.length == 2) {
            String name = split[0];
            String password = split[1];
            try {
                User userDB = userDao.getUser(name);
                if (userDB != null) {
                    if (userDB.getUserPwd().equals(password)) {
                        user = userDB;
                    }
                }
            } catch (Exception e) {
                logger.error("本地用户Cookie和数据库验证异常", e);
//                e.printStackTrace();
            }
        }

        return user;
    }

    /**
     * 数据库中获取用户权限
     *
     * @param user User
     * @return HashMap<String,Integer>
     * @throws Exception
     */
    public final HashMap<String, Integer> getRole(User user) {
        try {
            HashMap<String, Integer> RoleMap = new HashMap<String, Integer>(0);
            RoleMap = userDao.getUserRoles(user);
            return RoleMap;
        } catch (Exception e) {
            logger.error("数据库中获取角色权限异常", e);
            return null;
        }
    }

    /**
     * 数据库中获取用户权限
     *
     * @param user User
     * @return HashMap<String,Integer>
     * @throws Exception
     */
    public final HashMap<String, Integer> getAuth(User user) {
        try {
            HashMap<String, Integer> AuthMap = new HashMap<String, Integer>(0);
            AuthMap = userDao.getUserAuthoritys(user);
            return AuthMap;
        } catch (Exception e) {
            logger.error("数据库中获取用户权限异常", e);
            return null;
        }
    }

    /**
     * 数据库中获取设备类表
     *
     * @return HashMap<String,Integer>
     * @throws Exception
     */
    public final List getDeviceList() {
        try {
            return deviceDao.getDeviceList();
        } catch (Exception e) {
            logger.error("数据库中获取设备类表异常", e);
            return null;
        }
    }
}
