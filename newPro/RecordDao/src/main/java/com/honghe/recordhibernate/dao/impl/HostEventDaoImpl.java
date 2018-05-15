package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.HostEventDao;
import com.honghe.recordhibernate.entity.Hostevent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hthwx on 2015/2/3.
 */
@Repository
public class HostEventDaoImpl extends BaseDao implements HostEventDao {
    /**
     * 获取主机事件列表
     * @param heId
     * @return
     * @throws Exception
     */
    @Override
    public Hostevent getHostEventInfo(int heId) throws Exception {
        return null;
    }

    /**
     * 获取主机事件列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Hostevent> getHostEventList() throws Exception {
        return (List<Hostevent>) this.getHibernateTemplate().find("from Hostevent");
    }
}
