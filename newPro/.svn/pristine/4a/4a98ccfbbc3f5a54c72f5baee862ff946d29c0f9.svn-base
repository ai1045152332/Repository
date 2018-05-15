package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Dspecification;

import java.util.List;

/**
 * Created by Moon on 2014/9/11.
 */
public interface SpecDao {
    public int getDiveceType(int specid);

    public List getSpecList() throws Exception;

    public List<Object[]> getSpecListByPage(Page page) throws Exception;

    public List getSpecByName(String name);

    public Dspecification getSpecById(int id) throws Exception;

    public boolean save(Dspecification dspecification);

    public boolean delete(int id);

    public boolean updata(Dspecification dspecification) throws Exception;

    public boolean hasDtype(int dtype_id) throws Exception;

    public List getSpecRelation(int specid) throws Exception;

    public Object[] getSpec(String name) throws Exception;
}
