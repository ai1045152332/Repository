package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DModeDao;
import com.honghe.recordhibernate.entity.DMode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhanghongbin on 2014/11/10.
 */
@Repository
public class DModeDaoImpl extends BaseDao implements DModeDao {
    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<DMode> getDModeList() throws Exception {
        return this.getHibernateTemplate().findByExample(new DMode());
    }
}
