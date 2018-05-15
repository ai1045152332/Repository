package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Nas;

import java.util.List;

/**
 * Created by zhanghongbin on 2014/12/8.
 */
public interface NasDao {


    public List<Object[]> getHostAndNasListFromSql(final int hostgroupId, String condition, final int userId) throws Exception;

    public Nas findNas(String hostId) throws Exception;

    public void updateNas(Nas nas) throws Exception;

    public Integer saveNas(Nas nas) throws Exception;

    public void deleteFromHost(final String hostId) throws Exception;

    public void deleteFromNas(final String nasId) throws Exception;

    public List<Object[]> getNasList() throws Exception;

    public void deleteNas(final String nasId) throws Exception;

    public List<Object[]> getNas2HostList() throws Exception;

    public void saveNas2Host(String[] hostId,Integer nasId) throws Exception;

    public List<Object[]> getNasByPath(String nasPath) throws Exception;
}
