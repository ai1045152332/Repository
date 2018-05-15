package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Classtime;

import java.util.Date;
import java.util.List;

/**
 * Created by lch on 2014/9/26.
 */
public interface ClasstimeDao {
    //public List<Classtime> getClasstimeListByHostId(String hostId) throws Exception;
    public List<Classtime> getClasstimeList()throws Exception;

    public boolean save(Classtime classtime) throws Exception;

    public boolean update(Classtime classtime) throws Exception;

    public boolean delete(int id) throws Exception;

    public Classtime getInfo(int id) throws Exception;

    public List<Object[]> getClasstimeList(int hostId,int weekId) throws Exception;

    public List<Object[]> getClasstimeListNoGroup(int weekId) throws Exception;

    public List<Integer> getSectionList() throws Exception;

    public List<Integer> getSectionListNoGroup(int hostId) throws Exception;

    public Classtime getClassTime(int section, int classtimPloy) throws Exception;

    public void deleteClasstime2Group(Integer timePloyId) throws Exception;

    public List<Object[]> getClasstimeListWithCirriculum(int hostId,int weekId) throws Exception;

    public List<Object[]> getClasstimeListWithCirriculum(int hostId,Date date) throws Exception;

    public List<Object[]> getClasstimeListWithCirriculumNoGroup(int hostId,int weekId) throws Exception;

    public List<Object[]> getClasstimeListWithCirriculumNoGroup(int hostId,Date date) throws Exception;

//  ********************************* add by lichong *************************************

    //add by lichong
    public List<Integer> getSectionList(int groupId) throws Exception;
    // add by lichong
    public List<Object[]> getClasstimeListWithCirriculum(int groupId, int hostId, int weekId) throws Exception;
    // add by lichong
    public List<Object[]> getClasstimeListWithCirriculum(int groupId, int hostId, Date date) throws Exception;
    //add by lichong
    public List<Object[]> getClasstimeListByGroup(int weekId,int groupId) throws Exception;
}
