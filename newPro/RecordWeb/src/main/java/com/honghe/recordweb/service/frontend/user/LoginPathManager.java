package com.honghe.recordweb.service.frontend.user;

import com.honghe.recordhibernate.entity.Device;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import org.springframework.web.context.ContextLoaderListener;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wj on 2014-10-15.
 */
public class LoginPathManager {
    public enum Path {
        DMANAGER("dmanager"),
        TIMEPLAN("timeplan"),
        DEVICE("device"),
        RESOURCE("resource"),
        USER("user"),
        ROLE("role"),
        LOG("log"),
        DENY("deny"),
        VIEWCLASS("viewclass"),
        VIEWCLASSDEVICE("viewclassdevice"),
        HTPROBJECTOR("htprojector"),//投影仪设备控制界面
        HTPROBJECTORDEVICE("htprojectordevice"),//投影仪设备管理界面
        HHTWBOBJECTOR("hhtwbobjector"),//白板一体机设备控制界面
        HHTWBOBJECTORDEVICE("hhtwbobjectordevice");//白板一体机设备管理界面
        private String path;

        Path(String path) {
            this.path = path;
        }

        @Override
        public String toString() {
            return this.path;
        }
    }

    private LoginPathManager() {

    }

    /**
     * 清空cookie
     */
    public static String getLoginPath(HttpServletRequest request) {
        HongheDeviceService hongheDeviceService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HongheDeviceService.class);
        UserService userService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(UserService.class);
        User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
        Map<String, String> userMap = userService.getRoleMapByUserid(user.getUserId());
        if (user.getUser_salt().equals("true")) {
            CookieKeeplogin cookieKeeplogin = ContextLoaderListener.getCurrentWebApplicationContext().getBean(CookieKeeplogin.class);
            List deviceList = cookieKeeplogin.getDeviceList();
            int sumNumberHHTCHost = hongheDeviceService.getHostCount(CommonName.DEVICE_TYPE_SCREEN);//大屏数量
            int sumNumberHtprHost = hongheDeviceService.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);//投影仪数量
            int sumNumberhhtwbHost = hongheDeviceService.getHostCount(CommonName. DEVICE_TYPE_WHITEBOARD);//电子白板数量
            //判断是否为录播管理员或者只是录播设备
            if (userMap.containsValue(Role.Role_HhrecManager.toString())|| ConfigureUtil.isOnlyHhrec()) {
//                if (deviceList == null || deviceList.isEmpty()) {
//                    return Path.VIEWCLASSDEVICE.toString();
//                } else {
                    return Path.VIEWCLASS.toString();//暂时对跳转到设备管理界面功能屏蔽，之后会考虑添加（现直接跳转到巡课界面）
//                }
            } if (userMap.containsValue(Role.Role_HhtwbManager.toString())|| ConfigureUtil.isOnlyHhtwb()) {
                if (sumNumberhhtwbHost <= 0) {
                    return Path.HHTWBOBJECTORDEVICE.toString();
                } else {
                    return Path.HHTWBOBJECTOR.toString();
                }
            } else if(userMap.containsValue(Role.Role_ProjectorManger.toString())|| ConfigureUtil.isOnlyHtpr()){//判断是否为投影仪管理员或者只是投影仪设备
                if (sumNumberHtprHost <= 0) {
                    return Path.HTPROBJECTORDEVICE.toString();
                } else {
                    return Path.HTPROBJECTOR.toString();
                }
            }
            if (sumNumberHHTCHost <= 0) {
                return Path.DEVICE.toString();
            }
        } else {
            if (userMap.containsValue(Role.Role_Viewclass.toString())) {
                return Path.VIEWCLASS.toString();
            }else if(userMap.containsValue(Role.Role_ProjectorManger.toString())){
                return Path.HTPROBJECTOR.toString();
            }else if(userMap.containsValue(Role.Role_HhtwbControl.toString())){
                return Path.HHTWBOBJECTOR.toString();
            }
        }
        return Path.DMANAGER.toString();
    }
}
