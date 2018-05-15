package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Authority;

import java.util.List;

/**
 * Created by Moon on 2014/8/13.
 */
public interface AuthorityDao
{
    public List getAuthorityList();

    public List getAuthorityFrontList(int userId) throws Exception;

    public List getAuthorityBackList(final int userId) throws Exception;

    public List getAuthorityNameList(String name);

    public List getAuthorityWordList(String word);

    public void saveAuthority(Authority authority);

    public void delAuthority(int id) throws Exception;

    public boolean delAuthority(Authority authority) throws Exception;

    public Authority getAluthorityById(int id);

    public boolean updatAuthority(Authority authority);

    public boolean isAuthRoleExsist(final int id) throws Exception;

    public void getAuthorityListBackendByPage(Page page) throws Exception;
}
