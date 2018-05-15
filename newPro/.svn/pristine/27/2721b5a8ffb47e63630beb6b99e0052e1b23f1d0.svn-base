package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TResourceCatalogDao;
import com.honghe.recordhibernate.entity.TResourceCatalog;
import com.honghe.recordhibernate.entity.TResourceIndex;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
@Repository
public class TResourceCatalogDaoImpl extends BaseDao implements TResourceCatalogDao {
    private final static Logger logger = Logger.getLogger(TResourceCatalogDaoImpl.class);

    /**
     * todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog>getResourceCatalogsBySql(String sql) throws Exception{
        List<TResourceCatalog> result=new ArrayList<>();
        final String sqlstr = sql;
        result = (List<TResourceCatalog>)this.getHibernateTemplate().find(sql);
//        result = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
//            @Override
//            public List doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery(sqlstr).addEntity(TResourceCatalog.class).list();
//            }
//        });
        return result;
    }

    /**
     * todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceIndex>getResourceIndexBySql(String sql) throws Exception{
        List<TResourceIndex> result=new ArrayList<>();
        final String sqlstr = sql;
        result = (List<TResourceIndex>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sqlstr).addEntity(TResourceIndex.class).list();
            }
        });
        return result;
    }

    /**
     * todo 加注释
     * @param propertyname
     * @param value
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog> findByProperty(String propertyname,Object value) throws Exception{
        List<TResourceCatalog> result = new ArrayList<>();
        try {
            String querystring = "from TResourceCatalog as r where r."
                    + propertyname + " = ? and r.delFlag = 0 ";
			/* System.out.println(value + "valuevaluevaluevaluevalue"); */
            result = (List<TResourceCatalog>)this.getHibernateTemplate().find(querystring, value);
        } catch (RuntimeException e) {
//            System.out.println(e.toString() + "//////////方法哦");
            //e.printStackTrace();
            logger.error("", e);
        }
        return result;
    }

    /**
     * todo 加注释
     * @param propertyname
     * @param value
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog> findByPropertyWidthOrder(String propertyname,Object value) throws Exception{
        List<TResourceCatalog> result = null;
        String querystring = "select r.* from t_resource_catalog  r left join t_resource_index i on r.resourceIndex_id=i.id left join t_resource_status s on i.resouceStatus_id=s.id where r."
                + propertyname + " = '"+value+"' and r.delFlag = 0 and (s.uploadstatus=3 or s.uploadstatus is null) and r.status=2 order by r.type,CONVERT(r.name USING gbk)";
        final String sqlstr = querystring;
        result = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sqlstr).addEntity(TResourceCatalog.class);
                return  query.list();
                //return session.createSQLQuery(sqlstr).addEntity(TResourceCatalog.class).list();
            }
        });
        //result = (List<TResourceCatalog>)this.getHibernateTemplate().find(querystring);
        return result;
    }

    /**
     * todo 加注释
     * @param type
     * @param name
     * @param virtual
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog> findByQueryWithOrder(String type, String name, String virtual) throws Exception{
        List<TResourceCatalog> result = new ArrayList<>();
        String querystring = "";
        int types = 0;
        if(type.trim().equals("all") && StringUtils.isNotBlank(name))
        {
            querystring = "select r.* from t_resource_catalog r left join t_resource_index i on r.resourceIndex_id=i.id left join t_resource_status s on i.resouceStatus_id=s.id where r.name like  '%" + name
                    + "%'   and r.virtual like '%" + virtual + "%' and r.delFlag=0 and (s.uploadstatus=3 or s.uploadstatus is null) and r.status =2 order by r.type,CONVERT(r.name USING gbk)";
        }
        else
        {
            if(type.trim().equalsIgnoreCase("video"))
            {
                types=1;
            }
            else if(type.trim().equalsIgnoreCase("word"))
            {
                types=2;
            }
            else if(type.trim().equalsIgnoreCase("excel"))
            {
                types=3;
            }
            else if(type.trim().equalsIgnoreCase("img"))
            {
                types=4;
            }
            else if(type.trim().equalsIgnoreCase("pdf"))
            {
                types=5;
            }
            else if(type.trim().equalsIgnoreCase("ppt"))
            {
                types=6;
            }
            else if(type.trim().equalsIgnoreCase("flash"))
            {
                types=7;
            }
            else if(type.trim().equalsIgnoreCase("audio"))
            {
                types=8;
            }
            querystring = "select r.* from t_resource_catalog r left join t_resource_index i on r.resourceIndex_id=i.id left join t_resource_status s on i.resouceStatus_id=s.id where r.name like '%"+name+"%' and r.type = '" + types
                    + "' and r.virtual like '%" + virtual + "%' and r.delFlag =0 and (s.uploadstatus=3 or s.uploadstatus is null) and r.status =2 order by r.type,CONVERT(r.name USING gbk)";
        }
        final String resultQuery=querystring;

        result = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(resultQuery).addEntity(TResourceCatalog.class).list();
            }
        });
        return result;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TResourceCatalog findById(int id) throws Exception{
        return this.getHibernateTemplate().get(TResourceCatalog.class, id);
    }

    /**
     * todo 加注释
     * @param tResourceCatalog
     * @throws Exception
     */
    @Override
    public void update(TResourceCatalog tResourceCatalog) throws Exception{
        this.getHibernateTemplate().update(tResourceCatalog);
    }

    /**
     * todo 加注释
     * @param tResourceCatalog
     * @throws Exception
     */
    @Override
    public void saveOrUpdate(TResourceCatalog tResourceCatalog) throws Exception{
        this.getHibernateTemplate().saveOrUpdate(tResourceCatalog);
    }

    /**
     * todo 加注释
     * @param name
     * @param type
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog>getResourceCatalogByName(String name,int type,String fileName) throws Exception{
        final String creator=name;

        final String fileNam=fileName;
        final int typ=type;
        String hqlStr=" select * from t_resource_catalog as c left join t_resource_index t on c.resourceIndex_id=t.id left join t_resource e on t.resource_id=e.id  where e.creator=:creator and c.delFlag =0 ";
        if(type!=0){
            hqlStr=hqlStr+"and c.type=:type ";
        }
        if(fileName!=""){
            hqlStr=hqlStr+" and c.name like '%"+fileName+"%'";
        }
	   /* hqlStr+="order by c.name";*/
        hqlStr+="order by CONVERT(c.name USING gbk)";
        List<TResourceCatalog> result=null;
        //Session session=this.getCurrentSession();
//        Query query=session.createSQLQuery(hqlStr).addEntity(TResourceCatalog.class);
//
//        query.setParameter("creator", creator);
//        if(typ!=0){
//            query.setParameter("type", typ);
//        }
//        result=query.list();
        final String resultQuery = hqlStr;
        result = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(resultQuery).addEntity(TResourceCatalog.class);
                query.setParameter("creator", creator);
                if(typ!=0){
                    query.setParameter("type", typ);
                }
                return query.list();
            }
        });
        return result;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Boolean getItemByFid(int id) throws Exception{
        List<Object[]> result = null;
        final String sqlstr = "select * from t_resource_catalog where fid="+id;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sqlstr).list();
            }
        });
        if(result.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @param newName
     * @param oldName
     */
    @Override
    public void updateFileVirtual(int id,String newName,String oldName){
        final String sqlStr = "UPDATE t_resource_catalog SET virtual=REPLACE(virtual, '"+oldName+"', '"+newName+"') where fid="+id;
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery(sqlStr).executeUpdate();
                return null;
            }
        });
    }

    /**
     * todo 加注释
     * @param list
     * @param pathId
     * @return
     * @throws Exception
     */
    @Override
    public List<TResourceCatalog> findByPath(ArrayList list, String pathId) throws Exception{
        final Integer path=Integer.parseInt(pathId);
        final ArrayList name=list;
        List<TResourceCatalog> result=null;
        final String hql="from TResourceCatalog r where r.name in (:names) and r.fid=:pathId and r.delFlag=0";
        result = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List<TResourceCatalog> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(hql).addEntity(TResourceCatalog.class);
                query.setParameter("pathId", path);
                query.setParameterList("names", name);
                return query.list();
            }
        });
        return result;
    }

    /**
     * todo 加注释
     * @param name
     * @param pathId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> findFolderByPath(final String name, String pathId) throws Exception{
        List<Object[]> result=new ArrayList<>();
        final String sql ="select * from t_resource_catalog r where r.name ='"+name+"' and r.fid='"+pathId+"' and r.delFlag=0";
            result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        return result;
    }

    /**
     * todo 加注释
     * @param hql
     * @return
     * @throws Exception
     */
    @Override
    public List<T> find(String hql) throws Exception{
        return (List<T>)this.getHibernateTemplate().find(hql);
    }
}
