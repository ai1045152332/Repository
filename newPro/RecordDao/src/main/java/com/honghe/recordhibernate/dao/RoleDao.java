package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Role;

import java.util.List;

/**
 * Created by Moon on 2014/9/15.
 */
public interface RoleDao
{
    public List<Role> getRoleList() throws Exception;

    public List<Role> getRoleByName(String name) throws Exception;

    public List<Role> getRoleList(final String conditions) throws Exception;

    public boolean saveRole(Role role) throws Exception;

    public Role getRoleById(int id) throws Exception;

    public boolean updataRole(Role role) throws Exception;

    public boolean delRole(int id) throws Exception;

    public boolean delRole(Role role) throws Exception;

    public void delRoleAuthBySql(final int id) throws Exception;

    public boolean isRoleAuthExsist(final int id) throws Exception;

    public boolean isRoleUserExsist(final int id) throws Exception;

    public void addAuthToRole(final int roleId, final int authId) throws Exception;

    public void getRoleListBackendByPage(Page page) throws Exception;

    public void getRoleListFrontendByPage(final String conditions,Page page) throws Exception;
}


