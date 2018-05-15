package com.honghe.recordweb.action.frontend.alarm;

import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by yanxue on 2015-07-06.
 */
@Controller
@Scope("prototype")
public class AlarmAction  extends BaseAction {

    @Resource
    private SyslogService syslogService;
    private String hostIp; //设备IP
    private String alarmInfo; //告警信息

    /**
     * //todo 加注释
     */
    public void alarm() {
            syslogService.addDeviceLog(hostIp, alarmInfo, String.valueOf(SyslogService.LogType.WARING));
    }
}
