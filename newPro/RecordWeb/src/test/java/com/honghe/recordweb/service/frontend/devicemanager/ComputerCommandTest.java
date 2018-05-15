package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.util.CommonName;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ComputerCommandTest {

    Logger logger = Logger.getLogger(ComputerCommandTest.class);
    private ComputerCommand computerCommand;
    Map<String, Object> par_map = new HashMap<>();
    private HostgroupService hostgroupService;

    @Before
    public void setUp() throws Exception {
        computerCommand = DaoFactory.getDao(ComputerCommand.class);
        par_map.clear();
    }


    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }

    @Test
    /**
     * 开机
     */
    public void testStart() throws Exception {
        String ip = "192.168.18.21";
        boolean b = computerCommand.start(ip);
        System.out.println(b);
    }

    @Test
    /**
     * 关机
     */
    public void testShutdown() throws Exception {
        String ip = "192.168.18.21";
        String cmdCode = "ShutDown";
        boolean b = computerCommand.shutdown(ip, cmdCode);
        System.out.println(b);
    }

    @Test
    /**
     * 信号切换
     */
    public void testChangSignal() throws Exception {
        String ip = "192.168.18.21";
        String signal = "HDMI1";
        boolean b = computerCommand.changSignal(ip, signal);
        System.out.println(b);
    }


    @Test
    /**
     *音响模式切换
     */
    public void testChangeAudioMode() throws Exception {
        String ip = "192.168.18.21";
        String audio_mode = "Sport";
        boolean b = computerCommand.changeAudioMode(ip, audio_mode);
        System.out.println(b);
    }

    @Test
    /**
     * 音量调节
     */
    public void testChangeAudioControl() throws Exception {
        String ip = "192.168.18.21";
        String audio = "20";
        boolean b = computerCommand.changeAudioControl(ip, audio);
        System.out.println(b);
    }


    @Test
    /**
     * 远程节能
     */
    public void testChangeRemoteEnergy() throws Exception {
        String ip = "192.168.18.21";
        String energy_Mode = "PowerSaving";
        boolean b = computerCommand.changeRemoteEnergy(ip, energy_Mode);
        System.out.println(b);
    }

    @Test
    /**
     *依据ip获取设备信息
     */
    public void testGetInfo() throws Exception {
        String ip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip", ip);
        Map map = computerCommand.getInfo(ip);
        out(par_map, map);

    }

    @Test
    /**
     * 发送消息
     */
    public void testSendMessage() throws Exception {
        String ip = "192.168.18.21";
        Map map = new HashMap();
        map.put("123", "456");
        boolean b = computerCommand.sendMessage(ip, map);
        System.out.println(b);
    }

    @Test
    /**
     * 获取大屏桌面截图
     *
     */
    public void testGetPictrue() throws Exception {
        String ip = "192.168.18.21";
        Object obj = computerCommand.getPictrue(ip);
        System.out.println(obj);
    }


    @Test
    /**
     * 更新设备的命令表的地址
     */
    public void testUpdateCmdCodeSql() throws Exception {
        String ip = "192.168.18.21";
        String sqlStr = "insert into";
        boolean b = computerCommand.updateCmdCodeSql(ip, sqlStr);
        System.out.println(b);
    }

    @Test
    /**
     *获取设备的命令表是否过期标识符
     */
    public void testIsDeviceCmdCodeTimeOut() throws Exception {
        String ip = "192.168.18.21";
        boolean b = computerCommand.isDeviceCmdCodeTimeOut(ip);
        //todo 任何情况都返回true
        System.out.println(b);
    }

    @Test
    /**
     *推送订阅地址
     */
    public void testSendAddress() throws Exception {
        String ip = "192.168.18.21";
        String addrStr = "";
        boolean b = computerCommand.sendAddress(ip, addrStr);
        System.out.println(b);
    }

    @Test
    /**
     * 清理垃圾
     */
    public void testCleanRubbish() throws Exception {
        String ip = "192.168.18.21";
        boolean b = computerCommand.cleanRubbish(ip);
        System.out.println(b);
    }

    @Test
    /**
     * 打铃
     */
    public void testRing() throws Exception {
        String ip = "192.168.18.21";
        boolean b = computerCommand.ring(ip);
        System.out.println(b);
    }

    @Test
    /**
     *系统备份
     */
    public void testBackup() throws Exception {
        String ip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip", ip);
        boolean re_value = computerCommand.backup(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     *  系统还原
     */
    public void testRecovery() throws Exception {
        String ip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip", ip);
        boolean re_value = computerCommand.recovery(ip);
        out(par_map, re_value);
    }

    @Test
    /**
     * 一键安装软件
     */
    public void testOneKeyInstall() throws Exception {
        String ip = "192.168.18.2159";
        String softName = "QQ";
        String softPath = "c:";
        String serverIp = "192.168.1.100";
        String port = "51111";
        boolean b = computerCommand.oneKeyInstall(ip, softName, softPath, serverIp, port);
        System.out.println(b);
    }

    @Test
    /**
     * 切换频道
     */
    public void testAtvOrDtv() throws Exception {
        String ip = "192.168.18.21";
        String _cmdCode = "1";
        String _channelName = "ATV";
        par_map.clear();
        par_map.put("ip", ip);
        par_map.put("cmd_Code", _cmdCode);
        par_map.put("channelName", _channelName);
        boolean re_value = computerCommand.atvOrDtv(ip, _cmdCode, _channelName);
        out(par_map, re_value);
    }


    @Test
    /**
     * 获取设备命令
     */
    public void testGetDspecCommand() throws Exception {
        Map map = computerCommand.getDspecCommand(CommonName.DEVICE_TYPE_SCREEN);
        out(par_map, map);
    }

}