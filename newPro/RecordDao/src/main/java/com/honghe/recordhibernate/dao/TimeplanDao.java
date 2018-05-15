package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordhibernate.entity.Timeplan;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lch on 2014/9/26.
 */
public interface TimeplanDao {

    public Integer getTimeplan(int hostid, int curSection, String date) throws Exception;

    public Integer getTimeplan(int hostid, int curSection, int week_id) throws Exception;

    public List<Timeplan> getTimeplanList(Byte week_id) throws Exception;
//    public List<Classtime> getClasstimeList() throws Exception;

    public boolean save(Timeplan timeplan) throws Exception;

    public boolean update(Timeplan timeplan) throws Exception;

    public boolean delete(int id) throws Exception;
//    public List getHostTimeplan(Byte week_id) throws Exception;

    public boolean clearPlan(int id) throws Exception;

    public boolean isHavePlan(int hostid) throws Exception;


    public void delPlanByHost(int hostid) throws Exception;

    public List getTimeplanByClasstimeid(int classtimeid) throws Exception;

    public void delHostPlan(final int hostid) throws Exception;
//    public List<Object[]> getAllPlanHost() throws Exception;

    public List<Integer> getAllPan()throws Exception;

    public void delPlanByHostAndDate(String hostId, String date) throws Exception;

    public void delPlanByHostAndWeek(String hostId, int week) throws Exception;

//********************************************* cancel by lichong ***********************************

//    public List<Object[]> getTimeplanByHost(final int hostId,final String type,Date curDate) throws Exception;

//    public List<Object[]> getTimeplanList(final int hostId,Date date) throws Exception;

//    public List<Object[]> getTimeplanList(final int hostId,int weekId) throws Exception;

//********************************************* add by lichong ***********************************

    //add by lichong
    public List<Object[]> getTimeplanList(int groupId, int hostId,int weekId) throws Exception;
    //add by lichong
    public List<Object[]> getTimeplanByHost(int groupId, int hostId, String type,Date curDate) throws Exception;
    //add by lichong
    public List<Object[]> getTimeplanList(int groupId, int hostId, Date date) throws Exception;
}
