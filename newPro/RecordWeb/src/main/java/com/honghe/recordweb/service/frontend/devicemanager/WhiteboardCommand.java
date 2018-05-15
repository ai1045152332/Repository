package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyx on 2016-05-20.
 * 用于处理白板一体机的相关操作及状态获取
 */
@Component
public class WhiteboardCommand extends VirtualCommand {
    private final Logger logger = Logger.getLogger(WhiteboardCommand.class);

    private String commonName = CommonName.METHOD_TYPE_WHITEBOARD;

    @Override
    protected String getCommandName() {
        return CommonName.METHOD_TYPE_WHITEBOARD;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    /**
     * 一体机一键锁定、解除锁定
     *
     * @param ip
     * @param cmdCode 一键锁定：”Lock”，一键解除锁定：”Unlock”
     * @return
     */
    public Boolean setBoardOneKeyLock(String ip, String cmdCode) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardOneKeyLock, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机一键锁定、解除锁定,ip:" + ip + " cmdCode:" + cmdCode + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 一体机设置静音状态
     *
     * @param ip
     * @param cmdCode Value：静音：”Mute”, 解除静音：“UnMute
     * @return
     */
    public Boolean setBoardMuteState(String ip, String cmdCode) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardMuteState, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机设置静音状态,ip:" + ip + " cmdCode:" + cmdCode + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }

    /**
     * 一体机设置音量值
     *
     * @param ip
     * @param val 音量值：0-100
     * @return
     */
    public Boolean setBoardVolume(String ip, String val) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("val", val);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardVolume, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机设置音量,ip:" + ip + " val:" + val + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }

    /**
     * 一体机OPS重启
     *
     * @param ip
     * @return
     */
    public Boolean setBoardOpsRestart(String ip) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardOpsRestart, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机OPS重启,ip:" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }

    /**
     * 一体机设置投影机待机
     *
     * @param ip
     * @param cmdCode Value：待机：”Standby” ,正常：”Normal”
     * @return
     */
    public Boolean setBoardProjectorStandby(String ip, String cmdCode) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardProjectorStandby, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机设置投影机待机,ip:" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }

    /**
     * 一体机投影机电源
     *
     * @param ip
     * @param cmdCode Value：开机：”TurnOn”, 关机：“TurnOff”
     * @return
     */
    public Boolean setBoardProjectorTurn(String ip, String cmdCode) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardProjectorTurn, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机投影机电源,ip:" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }

    /**
     * 一体机投影机省电
     *
     * @param ip
     * @param cmdCode Value：省电：”Energy” ,标准：”Standard”
     * @return
     */
    public Boolean setBoardProjectorEnergy(String ip, String cmdCode) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(commonName, DeviceServiceClient.Method.Device_HhtwbCommand_SetBoardProjectorEnergy, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        logger.debug("一体机投影机省电,ip:" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());

        return re_value;
    }


}
