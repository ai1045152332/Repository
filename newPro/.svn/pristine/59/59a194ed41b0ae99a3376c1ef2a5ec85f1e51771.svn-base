package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.util.CommonName;
import jodd.util.ObjectUtil;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyx on 2016-05-24.
 */
public class WhiteboardCommandTest {
    Logger logger = Logger.getLogger(ComputerCommandTest.class);
    private WhiteboardCommand whiteboardCommand;
    Map<String, Object> par_map = new HashMap<>();
    private HostgroupService hostgroupService;

    @Before
    public void setUp() throws Exception {
        whiteboardCommand = DaoFactory.getDao(WhiteboardCommand.class);
        par_map.clear();
    }
    public void out(Map _parameter, Object _value) {
        System.out.println("------------------------------------------------");
        System.out.println(" _parameter:" + _parameter.toString());
        System.out.println("_value:" + _value.toString());
        System.out.println("------------------------------------------------");
    }
    /**
     *开机
     * @throws Exception
     */
    @Test
    public void testStart() throws Exception {
        String ip = "192.168.18.21";
        boolean b = whiteboardCommand.start(ip);
        System.out.println(b);
    }
    /**
     *关机
     * @throws Exception
     */
    @Test
    public void testShutdown() throws Exception {

        String ip = "192.168.18.21";
        String cmdcode = "ShutDown";
        boolean b = whiteboardCommand.shutdown(ip,cmdcode);
        System.out.println(b);
    }
    /**
     *信号切换
     * @throws Exception
     */
    //todo 出现错误，需要修改(参数命名)
    @Test
    public void testChangSignal() throws Exception {
        String ip = "192.168.18.44";
        String cmdCode = "HDMI1";
        boolean b = whiteboardCommand.changSignal(ip,cmdCode);
        System.out.println(b);
    }
    /**
     *依据ip获取设备信息
     * @throws Exception
     */
    @Test
    public void testGetInfo() throws Exception {

        String ip = "192.168.18.21";
        String cmdCode = "ShutDown";
        whiteboardCommand.getInfo(ip);

    }
    /**
     *发送消息
     * @throws Exception
     */
    @Test
    public void testSendMessage() throws Exception {
        String ip = "192.168.18.21";
        Map map = new HashMap();
        map.put("map","tttt");
        boolean b = whiteboardCommand.sendMessage(ip,map);
        System.out.println(b);
    }
    /**
     *获取白板一体机桌面截图
     * @throws Exception
     */
    @Test
    public void testGetPictrue() throws Exception {

        String ip = "192.168.18.21";
        Object obj = whiteboardCommand.getPictrue(ip);
        System.out.println(obj);
    }
    /**
     *更新设备的命令表的地址
     * @throws Exception
     */
    @Test
    public void testUpdateCmdCodeSql() throws Exception {
        String ip = "192.168.18.21";
        String sqlStr = "insert into";
        boolean b = whiteboardCommand.updateCmdCodeSql(ip,sqlStr);
        System.out.println(b);
    }
    /**
     *获取设备的命令表是否过期标识符
     * @throws Exception
     */
    @Test
    public void testIsDeviceCmdCodeTimeOut() throws Exception {
        String ip = "192.168.18.21";
        boolean b = whiteboardCommand.isDeviceCmdCodeTimeOut(ip);
        //todo 任何情况都返回true
        System.out.println(b);
    }
    /**
     *推送订阅地址
     * @throws Exception
     */
    @Test
    public void testSendAddress() throws Exception {
        String ip = "192.168.18.21";
        String addrStr = "";
        boolean b = whiteboardCommand.sendAddress(ip, addrStr);
        System.out.println(b);
    }
    /**
     *清理垃圾
     * @throws Exception
     */
    @Test
    public void testCleanRubbish() throws Exception {
        String hostip = "192.168.18.21";
        boolean b = whiteboardCommand.cleanRubbish(hostip);
        System.out.println(b);
    }

    /**
     * 打铃
     * @throws Exception
     */
    //todo 方法名称不对
    @Test
    public void testRing() throws Exception {
        String hostip = "192.168.18.21";
        boolean b = whiteboardCommand.ring(hostip);
        System.out.println(b);
    }

    /**
     * 系统备份
     * @throws Exception
     */
    @Test
    public void testBackup() throws Exception {
        String hostip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip",hostip);
        boolean b = whiteboardCommand.backup(hostip);
        out(par_map, b);
    }
    /**
     * 系统还原
     * @throws Exception
     */
    @Test
    public void testRecovery() throws Exception {
        String ip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip",ip);
        boolean b = whiteboardCommand.recovery(ip);
        out(par_map,b);
    }

    /**
     * 一键安装
     * @throws Exception
     */
    //todo 参数不一致
    @Test
    public void testOneKeyInstall() throws Exception {
        String ip = "192.168.18.21";
        String name = "qq";
        String softpath = "c:";
        String severip = "192.168.19.31";
        String port = "122445";
        boolean re_value = whiteboardCommand.oneKeyInstall(ip,name,softpath,severip,port);
        System.out.println(re_value);
    }

    /**
     * 一键卸载
     * @throws Exception
     */
    @Test
    public void testOneKeyuninstall() throws Exception {
        String ip = "192.168.18.21";
        String softname = "qq";
        boolean b = whiteboardCommand.oneKeyuninstall(ip,softname);
        System.out.println(b);
    }
    /**
     * 获取设备命令
     */
    // todo 是否添加
    @Test
    public void testGetDspecCommand() throws Exception {
        Map map = whiteboardCommand.getDspecCommand(CommonName.DEVICE_TYPE_WHITEBOARD);
        out(par_map,map);
    }
    /**
     * 一体机一键锁定、解除锁定
     */
    @Test
    public void testSetBoardOneKeyLock() throws Exception {
        String ip = "192.168.18.21";
        String cmdcode = "Lock";
        boolean b = whiteboardCommand.setBoardOneKeyLock(ip,cmdcode);
        System.out.println(b);

    }
    /**
     * 一体机设置静音状态
     */
    @Test
    public void testSetBoardMuteState() throws Exception {
        String ip = "192.168.18.21";
        String cmdcode = "Mute";
        boolean value = whiteboardCommand.setBoardMuteState(ip,cmdcode);
        System.out.println(value);
    }
    /**
     * 一体机设置音量值
     */
    @Test
    public void testSetBoardVolume() throws Exception {
        String ip = "192.168.18.21";
        String vol = "34";
        boolean value =whiteboardCommand.setBoardVolume(ip,vol);
    }
    /**
     * 一体机OPS重启
     *
     */
    @Test
    public void testSetBoardOpsRestart() throws Exception {
        String ip = "192.168.18.21";
        par_map.clear();
        par_map.put("ip",ip);
        boolean value =whiteboardCommand.setBoardOpsRestart(ip);
        out(par_map,value);
    }
    /**
     * 一体机设置投影机待机
     */
    //todo 方法传错
    @Test
    public void testSetBoardProjectorStandby() throws Exception {
        String ip = "192.168.18.21";
        String cmdcode = "Standby";
        boolean b = whiteboardCommand.setBoardProjectorStandby(ip,cmdcode);
        System.out.println(b);
    }
    /**
     * 一体机投影机电源
     */
    // todo 方法错误
    @Test
    public void testSetBoardProjectorTurn() throws Exception {
        String ip = "192.168.18.21";
        String cmdCode = "TurnOn";
        boolean b =whiteboardCommand.setBoardProjectorTurn(ip,cmdCode);
        System.out.println(b);
    }
    /**
     * 一体机投影机省电
     */
    // todo 方法错误
    @Test
    public void testSetBoardProjectorEnergy() throws Exception {

        String ip = "192.168.18.21";
        String cmdCode = "Energy";
        boolean b =whiteboardCommand.setBoardProjectorEnergy(ip, cmdCode);
        System.out.println(b);

    }
}