package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.NewsDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.News;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lch on 2014/9/23.
 */
@Repository
public class NewsDaoImpl extends BaseDao implements NewsDao {

    private final static Logger logger = Logger.getLogger(NewsDaoImpl.class);

    /**
     * 获取消息列表
     * @return
     * @throws Exception
     */
    @Override
    public List<News> getNewsList() throws Exception {
        return (List<News>) this.getHibernateTemplate().find(" from News ");
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getNewsObjectList() throws Exception {
        final String sql = "select a.n_id,a.n_cont,a.n_begintime,a.n_endtime,a.username,a.n_showtype,"
                + "a.n_fontsize,a.n_fontcolor,a.n_timeline,b.school_name from news a left join user b on a.uid = b.user_id";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * 分页获取数据
     *
     * @param page
     * @param groupIds 如果为admin用户，则groupIds= "0";如果用户只有一个分组，则groupIds = " = ? ";如果用户有多个分组，则groupIds = " in (?,?,?) ";
     * @throws Exception
     */
    @Override
    public void getNewsListByPage(Page page, String groupIds, String newsType, String deviceType) throws Exception {
        int countNum = 0;
        String sql = "SELECT DISTINCT b.n_id,b.n_cont,b.n_begintime,b.n_endtime,b.username,b.uid,b.n_timeline,GROUP_CONCAT(a.host_id),GROUP_CONCAT(a.host_name),b.n_showtype,b.n_fontsize,b.n_fontcolor,b.n_start,b.n_finish,b.n_week,b.n_month,b.n_font,b.n_loop,b.n_devicetype " +
                " from host2news a RIGHT JOIN news b on a.n_id = b.n_id ";
        String sqlCount = " select count(DISTINCT b.n_id) from host2news a RIGHT JOIN news b on a.n_id = b.n_id  where  a.status < 2 and b.n_type=" + newsType;

        //如果是非管理员用户，则需要判断分组权限
        if (!groupIds.equals("0")) {
            sql += " LEFT JOIN group2host d on a.host_id = d.host_id where d.group_id =" + groupIds + " AND ";
            sqlCount = " select count(DISTINCT b.n_id) from host2news a RIGHT JOIN news b on a.n_id = b.n_id " +
                    " LEFT JOIN group2host d on a.host_id = d.host_id" +
                    " where d.group_id " + groupIds + " AND  a.status < 2 and b.n_type=" + newsType+ " AND b.n_devicetype= '" + deviceType+ "'";
        } else {
            sql += " where ";
        }
        //使用group_concat需要后面加groupby；已删除的无需显示
        final String sqlStr = sql + " b.n_devicetype= '"+deviceType+"'"+" AND a.status < 2 and b.n_type=" + newsType + "  GROUP  BY a.n_id ORDER By b.n_timeline desc,b.n_id desc limit " + page.getFirstResult() + " , " + page.getPageSize();
        countNum = countNewsBySql(sqlCount);
        if (countNum > 0) {
            page.setTotalPageSize(countNum);

            List<Object[]> resultList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sqlStr).list();
                }
            });
            if (resultList != null && resultList.size() > 0) {
                List<Map<String, String>> newList = new LinkedList<Map<String, String>>();
                for (Object[] objArr : resultList) {
                    Map<String, String> resultMap = new LinkedHashMap<String, String>();
                    resultMap.put("n_id", objArr[0].toString());
                    resultMap.put("n_cont", objArr[1] == null ? "" : objArr[1].toString());
                    resultMap.put("n_begintime", objArr[2] == null ? "" : objArr[2].toString());
                    resultMap.put("n_endtime", objArr[3] == null ? "" : objArr[3].toString());
                    String duration = sumTime(resultMap.get("n_begintime"), resultMap.get("n_endtime"));
                    resultMap.put("duration", duration);
                    resultMap.put("username", objArr[4] == null ? "" : objArr[4].toString());
                    resultMap.put("uid", objArr[5] == null ? "" : objArr[5].toString());
                    resultMap.put("n_timeline", objArr[6] == null ? "" : objArr[6].toString());
                    resultMap.put("hostIds", objArr[7] == null ? "" : objArr[7].toString());
                    resultMap.put("hostNames", objArr[8] == null ? "" : objArr[8].toString());
                    resultMap.put("showtype", objArr[9] == null ? "" : objArr[9].toString());
                    resultMap.put("fontsize", objArr[10] == null ? "" : objArr[10].toString());
                    resultMap.put("fontcolor", objArr[11] == null ? "" : objArr[11].toString());
                    resultMap.put("start", objArr[12] == null ? "" : objArr[12].toString());
                    resultMap.put("finish", objArr[13] == null ? "" : objArr[13].toString());
                    resultMap.put("week", objArr[14] == null ? "" : objArr[14].toString());
                    resultMap.put("month", objArr[15] == null ? "" : objArr[15].toString());
                    resultMap.put("font", objArr[16] == null ? "" : objArr[16].toString());
                    resultMap.put("loop", objArr[17] == null ? "" : objArr[17].toString());
                    resultMap.put("n_devicetype", objArr[18] == null ? "" : objArr[18].toString());
                    //b.n_showtype,b.n_fontsize,b.n_fontcolor,b.n_start,b.n_finish,b.n_week,b.n_month,b.n_font
                    newList.add(resultMap);
                }
                page.setResult(newList);
            }
        }
    }

    /**
     *  根据id获取消息的详细信息
     * @param newId
     * @return
     * @throws Exception
     */
    @Override
    public News getNewsInfoById(int newId) throws Exception {
        return (News) this.getHibernateTemplate().get(News.class, newId);
    }

    /**
     * 修改某条消息
     * @param news
     * @throws Exception
     */
    @Override
    public void updateNews(News news) throws Exception {
        this.getHibernateTemplate().update(news);
    }

    /**
     * 添加消息
     * @param news
     * @return
     * @throws Exception
     */
    @Override
    public int addNews(News news) throws Exception {
        //Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        //this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        int newsId = (Integer)this.getHibernateTemplate().save(news);
        //int newsId = (int) session.save(news);
//        session.flush();
//        session.close();
        return newsId;
    }

   /* @Override
    public void updateNewsBySql(final String sql) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int intResult = session.createSQLQuery(sql).executeUpdate();
                if( intResult == 0) throw new HibernateException("no required data has been found! ");
                return intResult;
            }
        });
    }*/

    /**
     * 根据id删除某条信息
     *
     * @param newId
     * @throws Exception
     */
    @Override
    public void delNews(int newId) throws Exception {
        this.getHibernateTemplate().clear();
        News news = new News();
        news.setnId(newId);
        this.getHibernateTemplate().delete(news);
    }

    /**
     * 发送信息
     *
     * @param newId  对应的信息id
     * @param hostId 对应的设备id
     * @param status 该条信息的发送状态 0 失败；1 成功
     * @throws Exception
     */
    @Override
    public void sendNews(final int newId, final int hostId, final String hostName, final int status) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
               /* if(session.createSQLQuery("SELECT count(*) from host2news where host_id =" + hostId +" and n_id=" + newId).uniqueResult()== "0") {
               */
                //session.createSQLQuery("UPDATE host2news set status=2 where n_id="+newId).executeUpdate();
                session.createSQLQuery("INSERT INTO host2news (host_id, n_id,host_name,status) VALUES (" + hostId + "," + newId + ",'" + hostName + "'," + status + ")").executeUpdate();
               /* }
                else
                {
                    session.createSQLQuery("UPDATE host2news set status =" + status + " where host_id =" + hostId +" and n_id=" + newId).executeUpdate();
                }*/
                return null;
            }
        });
    }

    /**
     * 执行sql语句操作
     *
     * @param sql          sql语句内容
     * @param exceptionStr 执行结果为0时抛出的异常信息
     * @throws Exception
     */
    @Override
    public void executeBySql(final String sql, final String exceptionStr) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int intResult = session.createSQLQuery(sql).executeUpdate();
                //if( intResult == 0) throw new HibernateException(exceptionStr);
                return intResult;
            }
        });
    }

    /**
     * 根据newId获取该条信息的所有设备名称集合
     *
     * @param newId
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> getHostsByNewId(int newId) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        final String sql = " select a.n_id,group_concat(DISTINCT a.host_id),group_concat(DISTINCT a.host_name) from  host2news a "
                + " where status < 2 and  a.n_id = " + newId
                + " GROUP BY a.n_id";
        List<Object[]> resultList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        if (resultList != null && resultList.size() > 0) {
            Object[] tmpData = resultList.get(0);
            resultMap.put("newId", tmpData[0]);
            resultMap.put("hostIds", tmpData[1]);
            resultMap.put("hostNames", tmpData[2]);
        }
        return resultMap;
    }

    /**
     * 根据sql语句获取当前的数量
     *
     * @param sql
     * @return int sql语句查出了几条记录
     * @throws Exception
     */
    @Override
    public int countNewsBySql(final String sql) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).uniqueResult();
            }
        }).toString();
        return Integer.parseInt(result);
    }

    /**
     * 根据sql语句查询满足条件的结果集
     *
     * @param sql
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> querryNewsBySql(final String sql) throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();

            }
        });
    }

    /**
     * 时间差计算方法
     *
     * @param beginTime 开始时间字符串格式
     * @param endTime   结束时间字符串格式
     * @return 返回间隔多少天，24小时以内都算为一天
     */
    private String sumTime(String beginTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (beginTime != null && !beginTime.equals("") && endTime != null && !endTime.equals("")) {
                Date d1 = df.parse(beginTime);
                Date d2 = df.parse(endTime);
                long diff = d2.getTime() - d1.getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                return (int) (Math.ceil(days + 1)) + " 天";
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        return "无";
    }

    /**
     * 通过用户id获取富文本消息
     * @param page
     * @param userId
     * @param newsType
     * @param deviceType
     * @return
     * @throws Exception
     */
    public Page getRichNewsByUserId(Page page, String userId, String newsType, String deviceType) throws Exception {
        int countNum = 0;
        String sql = "SELECT n_id,n_status,uid,n_timeline from news where uid =" + userId + " and n_type=" + newsType + " and n_status !=-1" + " and n_devicetype = '"+deviceType+"'"+
                " ORDER By n_timeline desc, n_id desc limit " + page.getFirstResult() + " , " + page.getPageSize();
        String sqlCount = " select count(*) from news where uid =" + userId + " and n_type=" + newsType + " and n_status !=-1"+" and n_devicetype ='"+deviceType+"'";

        final String sqlStr = sql;
        countNum = countNewsBySql(sqlCount);
        if (countNum > 0) {
            page.setTotalPageSize(countNum);

            List<Object[]> resultList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sqlStr).list();
                }
            });
            if (resultList != null && resultList.size() > 0) {
                page.setResult(resultList);
            }
        }
        return page;
    }

    /**
     * 获取消息信息
     * @param newsId
     * @return
     * @throws Exception
     */
    public Map<String, Object> getNewsInfo(String newsId) throws Exception {
        News news = this.getNewsInfoById(Integer.parseInt(newsId));
        final String sql = "select host_id,host_name from host2news  where n_id = " + newsId + " and status = 1";
        List<Object[]> resultList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        Map<String, Object> map = new HashMap();
        map.put("news", news);
        if (resultList != null && resultList.size() > 0) {
            map.put("hostList", resultList);
        } else {
            map.put("hostList", new ArrayList());
        }


        return map;
    }

    /**
     * todo 加注释
     * @param hostId
     * @return
     * @throws Exception
     */
    public Boolean isNewsHostExists(int hostId) throws Exception {
        final String sql = "select count(*) from host2news where host_id = " + hostId + " and status not in(0,1)";
        List<Object[]> resultList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        if (resultList != null && resultList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * todo 加注释
     * @param hostId
     * @return
     * @throws Exception
     */
    public Boolean delNewsHostByHost(int hostId) throws Exception {
        final String sql = "update host2news set status = 2 where host_id=" + hostId + " and status in (0,1)";
        Object res = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int intResult = session.createSQLQuery(sql).executeUpdate();
                //if( intResult == 0) throw new HibernateException(exceptionStr);
                return intResult;
            }
        });
        if (res != null && Integer.parseInt(res.toString()) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
