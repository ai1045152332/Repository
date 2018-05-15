package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Eventfield;

import java.util.List;

/**
 * Created by hthwx on 2015/2/3.
 */
public interface EventFieldDao {

    public Eventfield getEventFieldInfo (int efId) throws Exception;

    public List<Eventfield> getEventFieldList() throws Exception;

    public List<Object[]> getEventFieldListByBelong(String belong) throws Exception;
}
