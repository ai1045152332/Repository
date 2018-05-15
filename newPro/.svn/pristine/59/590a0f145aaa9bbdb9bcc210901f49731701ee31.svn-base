package com.honghe.recordhibernate.dao;
import com.honghe.recordhibernate.entity.Curriculum;

import java.util.Date;
import java.util.List;

/**
 * Created by wuzhenzhen on 2016/06/09.
 */
public interface CurriculumDao {

    public List<Curriculum> getCurriculumList()throws Exception;

    public List<Curriculum> getCurriculumList(int hostid) throws Exception;

    public List<Curriculum> getCurriculumList(int hostid, int week_id) throws Exception;

    public List<Curriculum> getCurriculumList(int hostid, String date) throws Exception;

    public Curriculum getCurriculum(int hostid, int classTime, int week_id) throws Exception;

    public List<Object[]> getCurriculum(Integer hostid, String startRecordTime,Integer curriculumType) throws Exception;

    public boolean save(Curriculum curriculum) throws Exception;

    public boolean update(Curriculum curriculum) throws Exception;

    public boolean updateWeekCurriculum(Curriculum curriculum) throws Exception;

    public boolean updateDateCurriculum(Curriculum curriculum) throws Exception;

    public boolean delete(int id) throws Exception;

    public Curriculum getInfo(int id) throws Exception;

    public Curriculum getCurriculum_term(Date curDate, int curSection, int curHost)  throws Exception;

    public Curriculum getCurriculum_week(int week, int curSection, int curHost)  throws Exception;

    public void trucateCurrriculum() throws Exception;

    public Curriculum getCurriculum(int hostid, int classtime, String date) throws Exception;

    public void delWeekCurriculum(int hostid, int curSection, int week_id) throws Exception;

    public void delDateCurriculum(int hostid, int curSection, String date) throws Exception;

    public List getCurriculum() throws Exception;
}

