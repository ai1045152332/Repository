package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lch on 2015/2/12.
 * 用于大屏的相关操作及状态获取
 */
@Component
public final class ComputerCommand extends VirtualCommand {
    private final static Logger logger = Logger.getLogger(ComputerCommand.class);

    @Override
    protected String getCommandName() {
        return CommonName.METHOD_TYPE_SCREENCOMMAND;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }


    /**
     * 音响模式切换
     *
     * @param ip
     * @param audio_mode
     * @return
     */
    public boolean changeAudioMode(String ip, String audio_mode) {
        boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("cmdCode", audio_mode);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_Command_ChangeAudioMode, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
            }
            logger.debug("发送切换音响模式源命令,ip为" + ip + ",关机命令为" + audio_mode + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送切换音响模式源命令异常,ip为" + ip + ",关机命令为" + audio_mode, e);
        }
        return re_value;
    }

    /**
     * 音量调节
     *
     * @param ip    ip地址
     * @param audio 音量值
     * @return
     */
    public boolean changeAudioControl(String ip, String audio) {
        boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("cmdCode", audio);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_Command_ChangeAudioControl, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
                ;
            }
            logger.debug("发送音量调节源命令,ip为" + ip + ",关机命令为" + audio + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送音量调节源命令异常,ip为" + ip + ",关机命令为" + audio, e);
        }
        return re_value;
    }


    /**
     * 远程节能
     *
     * @param ip
     * @param energy_Mode
     * @return
     */
    public boolean changeRemoteEnergy(String ip, String energy_Mode) {
        boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("cmdCode", energy_Mode);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_Command_ChangeRemoteEnergy, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
            }
            logger.debug("发送远程节能命令,ip为" + ip + ",关机命令为" + energy_Mode + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送远程节能命令异常,ip为" + ip + ",关机命令为" + energy_Mode, e);
        }
        return re_value;
    }

    /**
     * 触控禁用启用设置
     *
     * @param ip
     * @param touch_mode " ScreenLock "：屏幕锁定；”ScreenUnlock”：屏幕未锁定
     * @return 返回状态：" ScreenLock "：屏幕锁定；”ScreenUnlock”：屏幕未锁定，“”：命令异常
     */
    public String changeTouchScreen(String ip, String touch_mode) {
        String re_value = "";
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("cmdCode", touch_mode);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_HhtcCommand_SetScreenLockMode, params);
            if (result.getCode() == 0 && result.getValue() != null && !"null".equals(result.getValue())) {
                Map value = (Map) result.getValue();
                re_value = (String) value.get("Value");
            }
            logger.debug("发送触控命令禁用启用命令,ip为" + ip + ",命令为" + touch_mode + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送触控命令禁用启用命令异常,ip为" + ip + ",命令为" + touch_mode, e);
        }
        return re_value;

    }


    /**
     * 获得触屏状态
     *
     * @param ip
     * @return 返回状态：" ScreenLock "：屏幕锁定；”ScreenUnlock”：屏幕未锁定，“”：命令异常
     */
    public String getTouchScreen(String ip) {
        String re_value = "";
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_HhtcCommand_GetScreenLockMode, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                Map state = (Map) result.getValue();
                re_value = (String) state.get("Value");
            }
            logger.debug("获取触控状态,ip为" + ip + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("获取触控状态异常,ip为" + ip, e);
        }
        return re_value;
    }

    /**
     * 大屏面板按键禁用启用设置
     *
     * @param ip         主机地址
     * @param touch_mode " PanelKeyLock "：面板按键锁定；” PanelKeyUnlock”： 面板按键未锁定
     * @return 返回状态：" PanelKeyLock "：面板按键锁定；”， PanelKeyUnlock”： 面板按键未锁定，“”：命令异常
     */
    public String changePanelKey(String ip, String touch_mode) {
        String re_value = "";
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("cmdCode", touch_mode);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_HhtcCommand_SetPanelKeyLockMode, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (String) result.getValue();
            }
            logger.debug("发送大屏面板按键禁用启用命令,ip为" + ip + ",命令为" + touch_mode + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送大屏面板按键禁用启用命令异常,ip为" + ip + ",命令为" + touch_mode, e);
        }
        return re_value;

    }

    /**
     * 获得触屏状态
     *
     * @param ip
     * @return 返回状态：" PanelKeyLock "：面板按键锁定；”， PanelKeyUnlock”： 面板按键未锁定，“”：命令异常
     */
    public String getPanelKey(String ip) {
        String re_value = "";
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_HhtcCommand_GetScreenLockMode, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = result.getValue().toString();
            }
            logger.debug("获取大屏面板按键状态,ip为" + ip + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("获取大屏面板按键状态异常,ip为" + ip, e);
        }
        return re_value;
    }

    /**
     * 切换频道
     *
     * @param _hostIp      大屏地址
     * @param _cmdCode     频道号
     * @param _channelName 信号源: 数字电视，模拟电视
     * @return
     */
    public Boolean atvOrDtv(String _hostIp, String _cmdCode, String _channelName) {
        boolean re_value = false;

        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", StringUtils.isEmpty(_hostIp) ? "" : _hostIp);
            params.put("cmdCode", StringUtils.isEmpty(_cmdCode) ? "" : _cmdCode);
            params.put("channelName", StringUtils.isEmpty(_channelName) ? "" : _channelName);

            Result result = remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_Command_AtvOrDtv, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
            }
            logger.debug("发送切换频道命令,ip为" + _hostIp + ",_cmdCode:" + _cmdCode + ",_channelName" + _channelName + ",结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送切换频道命令异常,ip为" + _hostIp + ",_cmdCode:" + _cmdCode + ",_channelName" + _channelName, e);
        }
        return re_value;
    }

}