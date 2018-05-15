package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Command;

import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/9/16.
 */
public interface CommandDao
{
    public Command getCommandById(int id);

    public List<Command> getDeviceCommList(Page page)throws Exception;

    public List<Command> getHostCommList();

    public List<Command> getDeviceCommByName(String name);

    public List<Command> getDeviceCommByHex(String hex);

    public boolean save(Command command) throws Exception;

    public boolean updata(Command command) throws Exception;

    public boolean delete(int id) throws Exception;

    public List<Command> getCommandList() throws Exception;

    public List<Object[]> getSepcCmd(String dspecid) throws Exception;

    public List<Object[]> getAllCmd() throws Exception;
}
