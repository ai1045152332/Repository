package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.ResourceDao;
import com.honghe.recordhibernate.entity.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hthwx on 2015/3/24.
 */
@Repository
public class ResourceDaoImpl extends BaseDao implements ResourceDao
{
    /**
     * 获取资源列表
     * @param page
     * @param str
     * @param order
     * @throws Exception
     */
    @Override
    public void getResourceListByPageSql(Page page, String str,String order) throws Exception {
        int countNum = 0;
        String sqlCont = "select count(DISTINCT a.res_id) from resource a where a.res_status = 0";
        String sqlQuery = "select a.res_id,a.res_name,a.host_name,a.host_ip,a.res_title,a.res_path,a.res_grade,a.res_subject,a.res_course,"
                + "a.res_speaker,a.res_school,a.res_size,CONVERT(a.res_updatetime,char(19)) as res_updatetime,a.res_thumb from resource a where a.res_status = 0";
        if(str != null && !str.trim().equals(""))
        {
            sqlCont += " and (a.host_name like '%" + str + "%' or a.res_speaker like '%" + str + "%' or a.res_subject like '%" + str + "%' or a.res_course like '%" + str + "%') ";
            sqlQuery += " and (a.host_name like '%" + str + "%' or a.res_speaker like '%" + str + "%' or a.res_subject like '%" + str + "%' or a.res_course like '%" + str + "%') ";
        }
        if(order != null && !order.trim().equals(""))
        {
            sqlQuery += " order by a." + order + " desc";
        }
        else
        {
            sqlQuery += " order by a.res_updatetime desc";
        }
        sqlQuery += " limit " + page.getFirstResult() +"," + page.getPageSize();
        countNum = this.countBySql(sqlCont);
        if(countNum > 0)
        {
            List<Object[]> resultList = this.getResultsBySql(sqlQuery);
            List<Map<String,Object>> mapList = new LinkedList<Map<String,Object>>();
            for(Object[] objArr : resultList)
            {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("res_id",objArr[0]);
                map.put("res_name",objArr[1]);
                map.put("host_name",objArr[2]);
                map.put("host_ip",objArr[3]);
                map.put("res_title",objArr[4]);
                map.put("res_path",objArr[5]);
                map.put("res_grade",objArr[6]);
                map.put("res_subject",objArr[7]);
                map.put("res_course",objArr[8]);
                map.put("res_speaker",objArr[9]);
                map.put("res_school",objArr[10]);
                map.put("res_size",objArr[11]);
                map.put("res_updatetime",objArr[12]);

//                File directory = new File("");
//                File file=new File(directory.getAbsolutePath());
//                if(!file.exists())
//                {
                map.put("res_thumb",objArr[13]);
//                }
//                else {
//                    map.put("res_thumb", objArr[13]);
//
//                }
                mapList.add(map);
            }
            page.setTotalPageSize(countNum);
            page.setResult(mapList);
        }
    }

    /**
     * 获取资源列表
     * @param page
     * @param str
     * @param order
     * @param ip
     * @param beginDate
     * @param endDate
     * @throws Exception
     */
    @Override
    public void getResourceListByPageWithIpSql(Page page, String str,String order,String ip,String beginDate,String endDate) throws Exception {
        int countNum = 0;
        String sqlCont = "select count(DISTINCT a.res_id) from resource a where a.res_status = 0 ";
        String sqlQuery = "select a.res_id,a.res_name,a.host_name,a.host_ip,a.res_title,a.res_path,a.res_grade,a.res_subject,a.res_course,"
                + "a.res_speaker,a.res_school,a.res_size,a.res_updatetime,a.res_thumb from resource a where a.res_status = 0 ";
        if(ip != null && !ip.trim().equals(""))
        {
            sqlCont += " and a.host_ip='" + ip +"'";
            sqlQuery += " and a.host_ip='" + ip +"'";
        }
        if(str != null && !str.trim().equals(""))
        {
            sqlCont += " and (a.host_name like '%" + str + "%' or a.res_speaker like '%" + str + "%' or a.res_subject like '%" + str + "%' or a.res_course like '%" + str + "%') ";
            sqlQuery += " and (a.host_name like '%" + str + "%' or a.res_speaker like '%" + str + "%' or a.res_subject like '%" + str + "%' or a.res_course like '%" + str + "%') ";
        }
        if(beginDate != null && !beginDate.equals(""))
        {
            sqlCont += " and res_updatetime >='" + beginDate + "'";
            sqlQuery += " and res_updatetime >='" + beginDate + "'";
        }
        if(endDate != null && !endDate.equals(""))
        {
            sqlCont += " and res_updatetime <='" + endDate + "'";
            sqlQuery += " and res_updatetime <='" + endDate + "'";
        }
        if(order != null && !order.trim().equals(""))
        {
            sqlQuery += " order by a." + order + " desc";
        }
        else
        {
            sqlQuery += " order by a.res_updatetime desc";
        }
        sqlQuery += " limit " + page.getFirstResult() +"," + page.getPageSize();
        countNum = this.countBySql(sqlCont);
        if(countNum > 0)
        {
            List<Object[]> resultList = this.getResultsBySql(sqlQuery);
            List<Map<String,Object>> mapList = new LinkedList<Map<String,Object>>();
            for(Object[] objArr : resultList)
            {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("res_id",objArr[0]);
                map.put("res_name",objArr[1]);
                map.put("host_name",objArr[2]);
                map.put("host_ip",objArr[3]);
                map.put("res_title",objArr[4]);
                map.put("res_path",objArr[5]);
                map.put("res_grade",objArr[6]);
                map.put("res_subject",objArr[7]);
                map.put("res_course",objArr[8]);
                map.put("res_speaker",objArr[9]);
                map.put("res_school",objArr[10]);
                map.put("res_size",objArr[11]);
                map.put("res_updatetime",objArr[12]);

//                File directory = new File("");
//                File file=new File(directory.getAbsolutePath());
//                if(!file.exists())
//                {
                map.put("res_thumb",objArr[13]);
//                }
//                else {
//                    map.put("res_thumb", objArr[13]);
//
//                }
                mapList.add(map);
            }
            page.setTotalPageSize(countNum);
            page.setResult(mapList);
        }
    }

    /**
     * 通过id获取资源信息
     * @param rId
     * @return
     * @throws Exception
     */
    @Override
    public Resource getResourceInfoById(int rId) throws Exception {
        return (Resource) this.getHibernateTemplate().get(Resource.class,rId);
    }

    /**
     * 添加资源
     * @param res
     * @return
     * @throws Exception
     */
    @Override
    public int addResource(Resource res) throws Exception {
        return (Integer) this.getHibernateTemplate().save(res);
    }

    /**
     * 修改资源信息
     * @param res
     * @throws Exception
     */
    @Override
    public void updateResourceInfo(Resource res) throws Exception {
        this.getHibernateTemplate().update(res);
    }

    /**
     * 修改资源通过sql
     * @param sql
     * @throws Exception
     */
    @Override
    public void updateResourceInfoBySql(String sql) throws Exception {
        String eMessage = "no data updated for this sql (" + sql + ")";
        this.executeBySql(sql,eMessage);
    }

    /**
     * 删除资源
     * @param rId
     * @throws Exception
     */
    @Override
    public void delResource(int rId) throws Exception {
        /*this.getHibernateTemplate().clear();
        Resource res = new Resource();
        res.setResId(rId);
        this.getHibernateTemplate().delete(res);*/
        final String sql = "DELETE FROM resource WHERE res_id= ?";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).
                setParameter(0, rId).executeUpdate();
    }

    /**
     * 统计当前有多少资源
     * @return
     * @throws Exception
     */
    @Override
    public int countResource() throws Exception {
        List<Resource> list = (List<Resource>) this.getHibernateTemplate().find(" from Resource h");
        if(list != null && !list.isEmpty())
        {
            return list.size();
        }
        return 0;
    }

    /**
     * 统计当前符合条件的资源有多少
     * @param sql
     * @return
     * @throws Exception
     */
    @Override
    public int countResourceBySql(String sql) throws Exception {
        return this.countBySql(sql);
    }

    /**
     * 查询最新的一条非空资源数据信息的res_path路径字段
     * @return
     * @throws Exception
     */
    @Override
    public Object queryResourcePath() throws Exception{
        final String sql = "select res_path from resource where res_path is not null && res_path<>'' order by res_updatetime desc";
        List<Object> list = (List<Object>)this.getResultBySql(sql);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        else
            return null;
    }

    /**
     * 根据文件路径获取id
     * @param res
     * @return
     * @throws Exception
     */
    @Override
    public Object getIdByPath(Resource res) throws Exception {
        String name = res.getResName();
        String ip = res.getHostIp();
        final String sql = "select res_id from resource where res_name = '"+name+"' and host_ip = '"+ip+"'";
        List<Object> list = (List<Object>)this.getResultBySql(sql);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        else
            return null;
    }

    /**
     * 判断mp4文件是否存在
     * @param name
     * @param res_id
     * @return
     * @throws Exception
     */
    @Override
    public Object getDataexist(String name,String res_id) throws Exception {
        final String sql = "select video_id from video where res_id = '"+res_id+"' and video_name = '"+name+"'";
        List<Object> list = (List<Object>)this.getResultBySql(sql);
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        else
            return null;
    }

    /**
     * 获取某个nas下资源时间超过约定自动删除时间的所有资源
     * @param nasIp
     * @param dateTime
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getResourceNasDelTime(String nasIp,String dateTime) throws Exception
    {
        // final String resPath = "\\\\\\\\\\\\\\\\" + nasIp + "\\\\\\\\res";
        final String resPath = nasIp;
        final String sql = "select res_id,res_path from resource where res_updatetime < '" + dateTime + "' and res_path like '" + resPath + "%'";
        System.out.println(sql);
        return this.getResultsBySql(sql);
    }

    /**
     * todo 加注释
     * @param sql
     * @param exceptionStr
     * @throws Exception
     */
    private void executeBySql(final String sql, final String exceptionStr) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int intResult = session.createSQLQuery(sql).executeUpdate();
                if( intResult == 0) throw new HibernateException(exceptionStr);
                return intResult;
            }
        });
    }

    /**
     *  todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    private int countBySql(final String sql) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).uniqueResult();
            }
        }).toString();
        return Integer.parseInt(result);
    }

    /**
     *  todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    private List<Object[]> getResultsBySql(final String sql) throws Exception
    {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     *  todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    private List<Object> getResultBySql(final String sql) throws Exception
    {
        return (List<Object>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    public static void main(String[] args) {
        String nasIp = "192.168.20.26";
        String dateTime = "2012-12-12";
        final String resPath = "\\\\\\\\\\\\\\\\" + nasIp + "\\\\\\\\res";
        final String sql = "select res_id,res_path from resource where res_updatetime < '" + dateTime + "' and res_path like '" + resPath + "%'";
        System.out.println(sql);
    }
}
