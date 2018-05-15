package com.honghe.recordweb.util;

import com.honghe.recordweb.service.frontend.user.Role;
import com.honghe.recordweb.service.frontend.user.UserService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by lyx on 2016-01-05.
 * 依据角色进行判断的类
 */
public class RoleUtil {
    @Resource
    private UserService userService;

    /**
     * 根据用户ID获取对应角色可以控制的设备类型
     *
     * @param _userId   用户ID
     * @param _roleName 角色表
     * @return 可控的设备类型
     */
    public static String RoleToDevice(String _userId, Map<String, String> _roleName) {
        String re_vlaue = CommonName.DEVICE_TYPE_ALL;

        if (_userId != null && _roleName != null) {
            if (_userId.equals("1") || _roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())|| _roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                re_vlaue = CommonName.DEVICE_TYPE_ALL;
            } else if (_roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_HhrecManager.toString())) {
                re_vlaue = CommonName.DEVICE_TYPE_RECOURD;
            } else if (_roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_HhtcManger.toString())) {
                re_vlaue = CommonName.DEVICE_TYPE_SCREEN;
            } else if (_roleName.containsKey(Role.Role_ProjectorManger.toString())) {
                re_vlaue = CommonName.DEVICE_TYPE_PROJECTOR;
            } else if (_roleName.containsKey(Role.Role_HhtwbManager)){
                re_vlaue = CommonName.DEVICE_TYPE_WHITEBOARD;
            }
        }
        return re_vlaue;
    }

    /**
     * 根据用户id获取其对应角色的权限
     * @param _userId   用户id
     * @param _roleName 角色表
     * @return
     */
    public static int RoleToPermission(int _userId, Map<String, String> _roleName) {
        int re_value = _userId;

        if (_roleName != null) {
            if (_userId == 1 || _roleName.containsValue(Role.Role_SchoolManager.toString())|| _roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                re_value = 0;
            } else if (_roleName.containsValue(Role.Role_HhrecManager.toString())) {
                re_value = 0;
            } else if (_roleName.containsValue(Role.Role_HhtcManger.toString())) {
                re_value = 0;
            } else if (_roleName.containsKey(Role.Role_ProjectorManger.toString())) {
                re_value = 0;
            }else if (_roleName.containsKey(Role.Role_HhtwbManager.toString())){
                re_value = 0;
            }
        }
        return re_value;
    }

}
