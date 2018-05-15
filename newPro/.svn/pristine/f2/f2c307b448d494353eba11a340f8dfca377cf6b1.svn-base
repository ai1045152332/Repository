package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Dtype;

import java.util.List;

/**
 * Created by Moon on 2014/9/16.
 */
public interface DTypeDao
{
    public List getTypeList();

    public void delete(Dtype dtype)throws Exception;

    public void updata(Dtype dtype)throws Exception;

    public void save(Dtype dtype)throws Exception;

    public Dtype getTypeById(int id);

    public List getTypeByName(String name);

    public void getDTypeList(Page<Dtype> page)throws Exception;

    public boolean isDType(String name)throws Exception;
}
