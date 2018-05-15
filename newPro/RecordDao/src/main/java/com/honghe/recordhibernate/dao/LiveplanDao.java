package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordhibernate.entity.Liveplan;
import com.honghe.recordhibernate.entity.Timeplan;

import java.util.Date;
import java.util.List;

/**
 * Created by wzz on 2016/7/22.
 */
public interface LiveplanDao {

    public Liveplan getLiveplan(int liveplan_id)  throws Exception;

    List<Integer> getAllPan() throws Exception;

    public List<Liveplan> getLiveplanList(Byte week_id) throws Exception;

    public boolean save(Liveplan liveplan) throws Exception;

    public boolean update(Liveplan liveplan) throws Exception;

    public boolean delete(int id) throws Exception;

    public boolean clearPlan(int id) throws Exception;

    public boolean isHavePlan(int hostid) throws Exception;

    public void delHostPlan(final int hostid) throws Exception;

    public List<Object[]> getAllPlanHost() throws Exception;

    public Integer getLiveplan(int hostid, int curSection, String date) throws Exception;

    public Integer getLiveplan(int hostid, int curSection, int week_id) throws Exception;

    public String getChannel(int hostid, int curSection, String date) throws Exception;

    public String getChannel(int hostid, int curSection, int week_id) throws Exception;

    public int update(String channels, Integer liveplanId) throws Exception;

    public Integer getLiveplanId(int hostid, int curSection, int week_id) throws Exception;

    public Integer getLiveplanId(int hostid, int curSection, String date) throws Exception;

    public void clearPlan() throws Exception;
    //add by xinye
    public List<Object[]> getLivePlanListByHostId(int hostId) throws Exception;
    //add by xinye end


    //************************************************* cancel by lichong ***************************************
//    public List<Object[]> getLiveplanList(int hostId, int weekId) throws Exception;
//    public List<Object[]> getLiveplanByHost(final int hostId,final String type,Date curDate) throws Exception;
//    public List<Object[]> getLiveplanEndByHost(final int hostId,final String type,Date curDate,int flag) throws Exception;
//    public List<Object[]> getLiveplanList(int liveplanid,String type) throws Exception;
//    public List<Object[]> getLiveplanList(int hostId, Date date) throws Exception;

    //************************************************* add by lichong ***************************************
    public List<Object[]> getLiveplanList(int groupId, int hostId, int weekId) throws Exception;
    public List<Object[]> getLiveplanByHost(int groupId, int hostId, String type,Date curDate) throws Exception;
    public List<Object[]> getLiveplanEndByHost(int groupId, int hostId, String type,Date curDate,int flag) throws Exception;
    public List<Object[]> getLiveplanList(int groupId, int liveplan_id,String type) throws Exception;
    public List<Object[]> getLiveplanList(int groupId, int hostId, Date date) throws Exception;
}
