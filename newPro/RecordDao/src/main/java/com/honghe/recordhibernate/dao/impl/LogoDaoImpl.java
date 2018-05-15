package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.LogoDao;
import com.honghe.recordhibernate.entity.Logo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wzz on 2015/01/13.
 */
@Repository
public class LogoDaoImpl extends BaseDao implements LogoDao {
    private final static Logger logger = Logger.getLogger(LogoDaoImpl.class);

    /**
     * 修改logo
     * @param logo
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateLogo(Logo logo) throws Exception {
        try {
            this.getHibernateTemplate().update(logo);
            return true;
        }
        catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取logo列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Logo> getLogoList() throws Exception {
        try
        {
            List<Logo> list = (List<Logo>) this.getHibernateTemplate().find("from Logo order by logoId desc");
            return list;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

//    @Override
//    public Logo getUsingLogo() throws Exception {
//        try
//        {
//            List<Logo> list = (List<Logo>) this.getHibernateTemplate().find("from Logo where logoUsing = 1");
//            return list.get(0);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 保存用户信息
     * @param logo Logo 需要保存的用户信息
     * @throws Exception
     */
    @Override
    public boolean saveLogo(Logo logo) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(logo);
            return true;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

}
