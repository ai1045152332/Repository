package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Programme;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lyx on 2015-08-22.
 */
public interface ProgrammeDao {

    public Programme getProgramme(int id) throws Exception;

    public List<Object[]> getProgrammeById(final int id) throws Exception;

    public HashMap<String, Integer> getProgrammeHosts(Programme programme) throws Exception;

    public void getProgrammeList(final Page<Programme> page) throws Exception;

    public List<Programme> getProgrammeList() throws Exception;

    public List<Object[]> getProgrammeListByUser(Page page, int uid) throws Exception;

    public boolean saveProgramme(Programme programme) throws Exception;

    public boolean updateProgramme(Programme programme) throws Exception;

    public void delProgramme(int id) throws Exception;

    public boolean delProgramme(Programme programme) throws Exception;

    public void delProgrammeHostBySql(final int id) throws Exception;

    public boolean isProgrammeHostExists(final int id) throws Exception;

    public boolean isProgrammeHostExistsByHost(final int hid) throws Exception;

    public void addProgrammeToHost(final int programmeId, final int hostId,final String hostName) throws Exception;

    public void delProgrammeHostByProgramme(final int id) throws Exception;

    public void deProgrammeHostBySql(final int pid, final int hid) throws Exception;

    public void delProgrammeHostByHost(final int id) throws Exception;
}
