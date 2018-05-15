package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Hostevent;

import java.util.List;

/**
 * Created by hthwx on 2015/2/3.
 */
public interface HostEventDao
{
    public Hostevent getHostEventInfo(int heId) throws Exception;

    public List<Hostevent> getHostEventList() throws Exception;
}
