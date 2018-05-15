package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.CommandCode;

import java.util.List;

/**
 * Created by hthwx on 2015/2/12.
 */
public interface CmdCodeDao
{
    //根据id获取编码命令
    public CommandCode getCommandCodeInfo(int cmdCodeId) throws Exception;
    //获取某一类型设备所有编码命令
    public List<Object[]> getCommandCodesListByDesp(int despId) throws Exception;
    //获取所有编码命令
    public List<CommandCode> getCommandCodesListAll() throws Exception;
   //新加的方法
    public List<Object[]> getDeviceCodeList(Page page)throws Exception;//以分页形式获取列表中所有数据

    public boolean save(CommandCode commandCode) throws Exception;//保存添加的数据

    public boolean updata(CommandCode commandCode) throws Exception;//修改数据

    public boolean delete(int CodeId) throws Exception;//删除数据

    public List<Object[]> getCodeBydespName(String name,Page page) throws Exception;//根据设备查询数据

    public List<Object[]> getCodeBycmdName(String name,Page page) throws Exception;//根据命令来查询数据

    public List<Object[]> getCodeByname(String dname,String cname,Page page) throws Exception;//根据设备名称由命令来查询数据

    public List<Object[]> getCmdNameBydespc(String dspecname) throws Exception;//根据设备名称来查询命令名称

    public List<Object[]> getCmdName() throws Exception;//获取命令名称相对应的命令编码列表

    public List<Object[]> getDspeName() throws Exception;//获取设备名称对应的命令编码列表
}
