package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TResourceDao;
import com.honghe.recordhibernate.entity.TResource;
import com.honghe.recordhibernate.entity.TResourceCatalog;
import com.honghe.recordhibernate.entity.form.JsonForm;
import com.honghe.recordhibernate.entity.form.ResourceForm;
import com.honghe.recordhibernate.entity.tools.GlobalParameter;
import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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
public class TResourceDaoImpl extends BaseDao implements TResourceDao {
    private final static Logger logger = Logger.getLogger(TResourceDaoImpl.class);
//    @Resource
//    private TResourceCatalogDao catalogDao;
//    @Resource
//    private TResourceIndexDao resourceIndexDao;

    /**
     * todo 加注释
     * @param rid
     * @return
     * @throws Exception
     */
    @Override
    public List<ResourceForm> selectResource(int rid) throws Exception {
        List<ResourceForm> resList = new ArrayList<ResourceForm>();

        String hql = "select rc.id,rc.fid,rc.name,ri.path,rc.type,rc.virtual,rc.format from ResourceCatalog rc,t_resource_index ri where rc.md5=ri.md5 and rc.id=" + rid;
        List<?> list = new ArrayList<>();
        final String sql = hql;
        list = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ResourceForm resourceForm = new ResourceForm();

                int id = (Integer) obj[0];
                int fid = (Integer) obj[1];
                String name = (String) obj[2];
                int type = (Integer) obj[4];
                String virtual = (String) obj[5];
                String suffix = ((String) obj[3]).substring(((String) obj[3]).lastIndexOf("."));

                String extName = GlobalParameter.getExtName(type, suffix);

                String srcPath = ((String) obj[3]).replace("..\\webapps\\ManagementCenter\\data\\", SearchPathTools.host + "/");
                srcPath = srcPath.replaceAll("\\\\", "/");
                String path = srcPath.substring(0, srcPath.lastIndexOf("."));

                resourceForm.setId("" + id);
                resourceForm.setPid("" + fid);
                resourceForm.setName(name);
                virtual = virtual + name + "." + (String) obj[6];
                resourceForm.setPath(virtual);
                resourceForm.setSnap(path + "/thumb.png");
                resourceForm.setMapped(path + "/" + extName);
                resList.add(resourceForm);
            }
        }
        return resList;
    }

    /**
     * todo 加注释
     * @param pCatalog
     * @param myType
     * @param srhText
     * @return
     * @throws Exception
     */
    @Override
    public List<JsonForm> selectResources(String pCatalog, String myType, String srhText) throws Exception {
        List<JsonForm> rtList = new ArrayList<JsonForm>();
        if (pCatalog == null || "".equals(pCatalog)) pCatalog = "/root/";
        String typeStr = " OR rc.type>0";
        if (myType != null && myType.indexOf(",") > 0) {
            typeStr = " OR rc.type IN (" + myType + ")";
        } else if (myType != null && !myType.startsWith("-")) {
            typeStr = " OR rc.type=" + myType;
        }
        String catalogStr = "rc.virtual='" + pCatalog + "' AND ";
        if (srhText != null && !"".equals(srhText)) {
            catalogStr = "rc.virtual LIKE '" + pCatalog + "%' AND ";
        }
        String nameStr = "";
        if (srhText != null && !"".equals(srhText)) {
            nameStr = " AND ((rc.type=0 AND rc.name LIKE '%" + srhText + "%') OR (rc.type>0 AND CONCAT(rc.name, '.', rc.format) LIKE '%" + srhText + "%'))";
        }
        /*String hql = "FROM ResourceCatalog rc   WHERE " + catalogStr + "delFlag=0 and status = 2   AND (type=0" + typeStr + ")" + nameStr;*/
        final String sql = "select  * from t_resource_catalog rc left join t_resource_index  ri  on rc.resourceIndex_id = ri.id  left join t_resource_status rs  on     ri.resouceStatus_id=rs.id" + "  WHERE " + catalogStr + "rc.delFlag=0 and (rs.uploadStatus=3 or  rc.resourceIndex_id is null ) and rc.status = 2   AND (rc.type=0" + typeStr + ")" + nameStr;
        //List<TResourceCatalog> list = catalogDao.getResourceCatalogsBySql(sql);
        //List<TResourceCatalog> list = (List<TResourceCatalog>)this.getHibernateTemplate().find(sql);
        List<TResourceCatalog> list = null;
        list = (List<TResourceCatalog>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).addEntity(TResourceCatalog.class).list();
            }
        });
        for (TResourceCatalog catalog : list) {
            int duration = 0;
            if (!"".equals(catalog.getMd5())) {
                final String sqlri = "select  id,t_name from t_resource_index ri where ri.t_md5 = '" + catalog.getMd5() + "'";
                //List<TResourceIndex> listri = catalogDao.getResourceIndexBySql(sqlri);
                List<Object[]> listri = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
                    @Override
                    public List doInHibernate(Session session) throws HibernateException {
                        return session.createSQLQuery(sqlri).list();
                    }
                });
                if (listri != null && listri.size() > 0) {
                    String sqlr = "select  * from t_resource r where r.id = '" + listri.get(0)[0].toString() + "'";
                    com.honghe.recordhibernate.entity.TResource resource = this.findObjectByID(Integer.parseInt(listri.get(0)[0].toString()));
                    if (resource != null) {
                        if (resource.getDuration() != null) {
                            duration = resource.getDuration();
                        }
                        catalog.setDuration(duration);
                    }
                }
            }
            int id = catalog.getId();
            int fid = catalog.getFid() == null ? 0 : catalog.getFid();
            String name = catalog.getName() + "." + catalog.getFormat();
            int type = catalog.getType();
            String virtual = catalog.getVirtual();
            String url = "";
            int width = 0;
            int height = 0;
            if (catalog.getResourceIndex_id() == null) {
                url = "";
            } else {
                String path = "";
                try {
                    //TResourceIndex tResourceIndex = this.getHibernateTemplate().get(TResourceIndex.class,catalog.getResourceIndex_id());
                    List<Object[]> result = null;
                    String querystring = "select t_path,id FROM t_resource_index where id=" + catalog.getResourceIndex_id();
                    final String sqlstr = querystring;
                    result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
                        @Override
                        public List<Object[]> doInHibernate(Session session) throws HibernateException {
                            return session.createSQLQuery(sqlstr).list();
                        }
                    });
                    //path = this.getResourceIndexById(catalog.getResourceIndex_id()).gettPath();
                    path = result.get(0)[0].toString();
                } catch (Exception e) {
                    //e.printStackTrace();
                    logger.error("", e);
                }
                int startPos = path.indexOf("\\data\\resources\\");
                if (startPos < 0) startPos = path.indexOf("/data/resources/");
                String webPath = "/msgResource" + path.substring(startPos).replace("\\", "/");
                url = webPath;
                try {
                    List<com.honghe.recordhibernate.entity.TResource> resourceList = this.find("From TResource AS R WHERE R.id IN (SELECT I.resourceId FROM TResourceIndex AS I WHERE I.id=" + catalog.getResourceIndex_id() + ")");
                    if (resourceList.size() > 0) {
                        com.honghe.recordhibernate.entity.TResource r = (com.honghe.recordhibernate.entity.TResource) resourceList.get(0);
                        width = resourceList.get(0).getWidth();
                        height = resourceList.get(0).getLength();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    logger.error("", e);
                }
            }
            JsonForm jsonForm = new JsonForm();
            jsonForm.setDuration(duration);
            jsonForm.setId(id);
            jsonForm.setType(type);
            jsonForm.setName(name);
            jsonForm.setVirtual(virtual);
            jsonForm.setPath(url);
            jsonForm.setUploader(width + "*" + height);
            rtList.add(jsonForm);
        }
        return rtList;
    }

    public TResource findObjectByID(int id) {
        return this.getHibernateTemplate().get(TResource.class, id);
    }

    public List<TResource> find(String sql) {
        return (List<TResource>) this.getHibernateTemplate().find(sql);
    }

    //    public TResourceIndex getResourceIndexById(int indexId) throws Exception {
//        return resourceIndexDao.findById(indexId);
//    }

    /**
     * todo 加注释
     * @param hql
     * @return
     * @throws Exception
     */
    @Override
    public Integer executeHql(String hql) throws Exception {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        int id = session.createQuery(hql).executeUpdate();
        session.flush();
        session.close();
        return id;
    }

    /**
     * todo 加注释
     * @param tResource
     * @return
     * @throws Exception
     */
    @Override
    public Integer save(TResource tResource) throws Exception {
        return (Integer) this.getHibernateTemplate().save(tResource);
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TResource findById(int id) throws Exception {
        return this.getHibernateTemplate().get(TResource.class, id);
    }
}
