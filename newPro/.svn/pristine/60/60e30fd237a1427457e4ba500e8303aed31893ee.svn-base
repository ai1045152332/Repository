package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Signalplan;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lch on 2015/6/18.
 */
public interface SignalplanDao {
    public Signalplan getSignalplan(int id) throws Exception;

    public List<Object[]> getSignalplanById(final int id) throws Exception;

    public HashMap<String, Integer> getSingnalHosts(Signalplan signalplan) throws Exception;

    //    public void getSignalplanList(final Page<Signalplan> page) throws Exception;
    public List<Signalplan> getSignalplanList() throws Exception;

    public List<Object[]> getSignalplanListByUser(Page page, int uid, String type) throws Exception;

    public boolean saveSignalplan(Signalplan signalplan) throws Exception;

    public boolean updateSignalplan(Signalplan signalplan) throws Exception;

    //    public void delSignalplan(int id)  throws Exception;
    public boolean delSignalplan(Signalplan signalplan) throws Exception;

    //    public void delSignalplanHostBySql(final int id) throws Exception;
    public boolean isSignalplanHostExists(final int id) throws Exception;

    //    public boolean isSignalplanHostExistsByHost(final int hid) throws Exception;
    public void addSignalplanToHost(final int signalplanId, final int hostId, final String hostName) throws Exception;

    public void delSignalplanHostBySignalplan(final int id) throws Exception;

    //    public void delSignalplanHostBySql(final int pid,final int hid) throws Exception;
    public void delSignalplanHostByHost(final int id) throws Exception;
}
