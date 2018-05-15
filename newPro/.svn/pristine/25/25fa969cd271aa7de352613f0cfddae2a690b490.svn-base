package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DTypeDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Dtype;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Moon on 2014/9/16.
 */
@Repository
public class DTypeDaoImpl extends BaseDao implements DTypeDao {
    private final static  Logger logger = Logger.getLogger(DTypeDaoImpl.class);

    /**
     * todo 加注释
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public boolean isDType(final String name) throws Exception {
        Object obj = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from dtype where dtype_name='" + name + "'").uniqueResult();
            }
        });
        BigInteger bigInteger = (BigInteger) obj;
        if (bigInteger.intValue() == 0) return false;
        return true;
    }

    /**
     * todo 加注释
     * @param page
     * @throws Exception
     */
    @Override
    public void getDTypeList(Page<Dtype> page) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        Object obj = hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createQuery("select count(*) from Dtype ").uniqueResult();

            }
        });
        int totalCount = 0;
        if (obj != null) {
            totalCount = Integer.parseInt(obj.toString());
        }
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            DetachedCriteria crit = DetachedCriteria.forClass(Dtype.class);
            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
            page.setResult(result);
        }
    }

    /**
     * todo 加注释
     * @return
     */
    public List getTypeList() {
        List<Dtype> list = null;

        try {
            list = (List<Dtype>) this.getHibernateTemplate().find("from Dtype");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return list;
    }

    /**
     * todo 加注释
     * @param dtype
     * @throws Exception
     */
    public void delete(Dtype dtype) throws Exception {

        this.getHibernateTemplate().delete(dtype);
    }

    /**
     * todo 加注释
     * @param dtype
     * @throws Exception
     */
    public void updata(Dtype dtype) throws Exception {
        this.getHibernateTemplate().update(dtype);
    }

    /**
     * todo 加注释
     * @param dtype
     * @throws Exception
     */
    public void save(Dtype dtype) throws Exception {
        this.getHibernateTemplate().save(dtype);

    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    @Override
    public Dtype getTypeById(int id) {
        List<Dtype> list = null;

        Dtype dtype = new Dtype();
        dtype.setDtypeId(id);
        try {
//            list =
            dtype = this.getHibernateTemplate().get(Dtype.class, id);//findByExample(dtype);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return dtype;
//        return list.size()==0?null:list.get(0);
    }

    /**
     * todo 加注释
     * @param name
     * @return
     */
    public List getTypeByName(String name) {
        List list = null;

        Dtype dtype = new Dtype();
        dtype.setDtypeName(name);
        try {
            list = this.getHibernateTemplate().findByExample(dtype);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return list.size() == 0 ? null : list;
    }
}
