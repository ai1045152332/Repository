package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.VideoDao;
import com.honghe.recordhibernate.entity.Video;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by lichunming on 2015/3/24.
 */
@Repository
public class VideoDaoImpl extends BaseDao implements VideoDao {
    /**
     * 保存文件信息
     * @param video
     * @return
     * @throws Exception
     */
    @Override
    public int saveVideo(Video video) throws Exception {
        return (Integer) this.getHibernateTemplate().save(video);
    }

    /**
     * 删除文件信息
     * @param id
     * @throws Exception
     */
    @Override
    public void delVideo(int id) throws Exception {
        this.getHibernateTemplate().clear();
        Video video = new Video();
        video.setVideoId(id);
        this.getHibernateTemplate().delete(video);
    }

    /**
     * 修改文件信息
     * @param video
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateVideo(Video video) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().update(video);
        return true;
    }

    /**
     * 点击上传修改文件状态
     * @param videoId
     * @param videoUpload
     * @throws Exception
     */
    @Override
    public void updateVideoUpload(String videoId, int videoUpload) throws Exception {
        String sql = "update video set video_upload =" + videoUpload + " where video_id =" + videoId;
        this.updateBySql(sql);
    }

    /**
     * 根据id删除信息
     * @param rid
     * @throws Exception
     */
    public void delVideoByRid(int rid) throws Exception {
        String sql = "delete from video where res_id =" + rid;
        this.executeBySql(sql, "no data delete");
    }

    /**
     * 初始化所有未上传完成文件的状态
     * @throws Exception
     */
    @Override
    public void initialVideoUpload() throws Exception {
        String sql = "update video set video_upload =0 where video_upload = 1";
        this.updateBySql(sql);
    }

    /**
     * 根据条件获取文件列表
     * @param ResourceId
     * @param condition
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getVideoListFromSql(int ResourceId, String condition, int userId) throws Exception {
        String strSql = "select v.video_id,v.video_name,v.video_time,v.video_downloads,v.video_visits,v.video_size,v.video_status,v.video_thumb from video v";
        return null;
    }

    /**
     * 统计当前有多少文件
     * @return
     * @throws Exception
     */
    @Override
    public int countVideo() throws Exception {
        List<Video> list = (List<Video>) this.getHibernateTemplate().find(" from Video v");
        if (list != null && !list.isEmpty()) {
            return list.size();
        }
        return 0;
    }

    /**
     * 根据分类ID获取video
     * @param rid
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getVideoByRid(int rid) throws Exception {
        String strSql = "select distinct v.video_id,v.video_name,v.video_time,v.video_downloads,v.video_visits,v.video_size,v.video_status,v.video_thumb,v.resid from video";
        if (rid > 0) //分类有效
        {
            strSql += "where v.res_id=" + rid + "order by v.video_time desc";
        }
        final String sql = strSql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * 根据id获取video信息
     * @param rId
     * @return
     * @throws Exception
     */
    @Override
    public Video getVideoInfoByRid(int rId) throws Exception {
        return (Video) this.getHibernateTemplate().get(Video.class, rId);
    }

    /**
     * todo 加注释
     * @param page
     * @param rid
     * @param order
     * @throws Exception
     */
    @Override
    public void getVideoListByPageSql(Page page, int rid, String order) throws Exception {
        int countNum = 0;
        String sqlCont = "select count(DISTINCT v.video_id) from video v where v.video_status = 0 and v.res_id =" + rid;
        String sqlQuery = "select v.video_id,v.video_name,CONVERT(v.video_time,char(16)) as video_time,v.video_downloads,v.video_visits,v.video_size,v.video_status,v.video_upload,v.video_url,v.video_path,v.video_thumb "
                + "from video v where v.video_status = 0 and v.res_id =" + rid;
        if (order != null && !order.trim().equals("")) {
            sqlQuery += " order by v." + order + " desc";
        } else {
            sqlQuery += " order by v.video_time desc";
        }
        countNum = this.countBySql(sqlCont);
        if (countNum > 0) {
            List<Object[]> resultList = this.getResultsBySql(sqlQuery);
            List<Map<String, Object>> mapList = new LinkedList<Map<String, Object>>();
            for (Object[] objArr : resultList) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("video_id", objArr[0]);
                map.put("video_name", objArr[1]);
                map.put("video_time", objArr[2]);
                map.put("video_downloads", objArr[3]);
                map.put("video_visits", objArr[4]);
                map.put("video_size", objArr[5]);
                map.put("video_status", objArr[6]);
                map.put("video_upload", objArr[7]);
                map.put("video_url", objArr[8]);
                map.put("video_path", objArr[9]);
                map.put("video_thumb", objArr[10]);
                mapList.add(map);
            }
            page.setTotalPageSize(countNum);
            page.setResult(mapList);
        }
    }

    @Override
    public Video getVideo(int res_id) throws Exception {
        return (Video) this.getHibernateTemplate().find("from Video v where v.resource.resId=?", res_id).get(0);
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public int countUploadNumber() throws Exception {
        String sql = "select count(*) from video where video_upload =1";
        return this.countBySql(sql);
    }

    /**
     * todo 加注释
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
     * todo 加注释
     * @param sql
     * @return
     * @throws Exception
     */
    private List<Object[]> getResultsBySql(final String sql) throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * todo 加注释
     * @param sql
     * @throws Exception
     */
    private void updateBySql(final String sql) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int intResult = session.createSQLQuery(sql).executeUpdate();
                if (intResult == 0) throw new HibernateException("no required data has been found! ");
                return intResult;
            }
        });
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
                if (intResult == 0) throw new HibernateException(exceptionStr);
                return intResult;
            }
        });
    }
}

