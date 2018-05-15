package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.ClasstimePloy;

import java.util.List;

/**
 * Created by wzz on 2016/7/18.
 */
public interface ClasstimePloyDao {

    public List<ClasstimePloy> getClasstimePloyList() throws Exception;

    public boolean save(ClasstimePloy classtimePloy) throws Exception;

    public boolean update(ClasstimePloy classtimePloy) throws Exception;

    public boolean delete(int id) throws Exception;

    public ClasstimePloy getInfo(int id) throws Exception;

    public void getClasstimePloyListByPage(Page page) throws Exception;

    public List<Object[]> getClasstimePloyById(final int id ) throws Exception;

    public void delPloyGroupBySql(final int id) throws Exception;

    public boolean isPloyGroupExsist(final int id) throws Exception;

    public void addGroupToPloy(final int ployId, final int groupId) throws Exception;

    public void delGroup2Ploy(final int ployId, final int groupId) throws Exception;

    //获取所有组所对应的时间策略
    public List<Object[]> getAllClasstimeployGroup() throws Exception;

    //添加时间策略
    public void saveTimePloy(ClasstimePloy classtimePloy) throws Exception;

    //获取所有的时间策略
    public List getAllTimePloy() throws Exception;

    public Integer getTimePloyId(String timePloy) throws Exception;

    public void delTimePloy(String timePloy) throws Exception;

    public void delClassTime(Integer timePloyId) throws Exception;

    public void updateGroupIdAndPloyId(Integer groupId, Integer ployId) throws Exception;

    public void saveGroupIdAndPloyId(Integer groupId, Integer ployId) throws Exception;

    public void delSection(int week, int section) throws Exception;

    public List<Object[]> getAllClassTime(Integer ployId) throws Exception;

    public ClasstimePloy getTimePloyByName(String timePloy) throws Exception;

    public List getGroupIds() throws Exception;

    public Integer getP2gId(Integer groupId) throws Exception;

    public Integer getClassTimePloyId(String timePloy) throws Exception;
}
