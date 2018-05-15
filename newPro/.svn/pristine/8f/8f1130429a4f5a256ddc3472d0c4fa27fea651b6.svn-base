package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Policy;

import java.util.HashMap;
import java.util.List;

public interface PolicyDao {

    public Policy getPolicy(int id)  throws Exception;

    public List<Object[]> getPolicyById(final int id ) throws Exception;

    public HashMap<String,Integer> getPolicyHosts(Policy policy) throws Exception;

    public List<Policy> getPolicyList() throws Exception;

    public List<Object[]> getPolicyListByUser(Page page,int uid, String type) throws Exception;

    public boolean savePolicy(Policy policy) throws Exception;

    public boolean updatePolicy(Policy policy)  throws Exception;

    public boolean delPolicy(Policy policy)  throws Exception;

    public boolean isPolicyHostExists(final int id) throws Exception;

    public boolean isPolicyHostExistsByHost(final int hid) throws Exception;

    public void addPolicyToHost(final int policyId, final int hostId,final String hostName) throws Exception;

    public void delPolicyHostByPolicy(final int id) throws Exception;

    public void delPolicyHostByHost(final int id) throws Exception;

}
