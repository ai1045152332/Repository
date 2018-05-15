package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxy on 2015/12/24.
 * 用于处理投影机的相关操作及状态获取
 */
@Component
public class HTProjectorCommand extends VirtualCommand {
    private final static Logger logger = Logger.getLogger(HTProjectorCommand.class);

    @Override
    protected String getCommandName() {
        return CommonName.METHOD_TYPE_PROJECTORCOMMAND;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    /**
     * 投影机终端开关机命令
     *
     * @param _ip      主机设备的ip地址
     * @param _cmdCode ”TurnOff”:关机；”TurnOn”:开机；
     * @return
     */
    public boolean setProjectorTurnState(String _ip, String _cmdCode) {
        boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", _ip);
            params.put("cmdCode", _cmdCode);
            Result result = remoteInvocation(CommonName.METHOD_TYPE_PROJECTORCOMMAND, DeviceServiceClient.Method.Device_Command_SetProjectorTurnState, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
            }
            logger.debug("关机命令已发出，ip为" + _ip + ",关机命令为" + _cmdCode + "结果为" + re_value + ",getCode为" + result.getCode());
        } catch (Exception e) {
            logger.error("发送关机命令异常,ip为" + _ip + ",关机命令为" + _cmdCode, e);
        }
        return re_value;
    }


}
