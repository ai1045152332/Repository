package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordhibernate.entity.Spec2command;

import java.util.List;

/**
 * Created by lch on 2014/9/23.
 */
public interface Spec2commandDao
{
    public List getSpec2commandBySpecID(Dspecification dspecification) throws Exception;

    public void delete(Dspecification dspecification) throws Exception;

    public boolean save(Spec2command spec2command) throws Exception;

    public int deleteRelation(final int specid) throws Exception;
}
