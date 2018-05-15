package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordhibernate.entity.Device;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by lch on 2014/9/23.
 */
@Repository
public class DeviceDaoImpl extends BaseDao implements DeviceDao {

    private final static  Logger logger = Logger.getLogger(DeviceDaoImpl.class);

    /**
     * 存在设备
     * @param hostId
     * @param deviceName
     * @return
     * @throws Exception
     */
    @Override
    public boolean isDevice(final int hostId, final String deviceName) throws Exception {
        BigInteger bigInteger = (BigInteger) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from service.device where host_id=" + hostId + " and device_name='" + deviceName + "'").uniqueResult();
            }
        });
        if (bigInteger.intValue() == 0) return false;
        return true;
    }


//    @Override
//    public void updateDevice(final int deviceId, final int isRecord) throws Exception {
//        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int n = session.createSQLQuery("update device set is_record=" + isRecord + " where device_id=" + deviceId).executeUpdate();
//                if (n == 0) throw new HibernateException("update failure");
//                return null;
//            }
//        });
//    }

    /**
     * 获取token
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getToken(final int hostId) throws Exception {
        Object obj = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select device_name, device_maintoken,device_subtoken,device_mainstream,device_substream from service.device where host_id=" + hostId).list();
            }
        });
        if (obj != null) {
            return (List<Object[]>) obj;
        }
        return Collections.EMPTY_LIST;

    }

    /**
     * 更新device表
     * @param device
     * @return
     * @throws Exception
     */
    @Override
    public Boolean updateDevice(Device device) throws Exception {
        try {
            this.getHibernateTemplate().merge(device);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 更新device表
     * @param deviceId
     * @param deviceName
     * @param deviceToken
     * @param hostId
     * @throws Exception
     */
    @Transactional
    @Override
    public void updateDevice(final int deviceId, final String deviceName, final String deviceToken, final int hostId) throws Exception {
        Object obj = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("update service.device set device_name='" + deviceName +
                        "', host_id=" + hostId + ",token='" + deviceToken + "' where device_id=" + deviceId).executeUpdate();
                if (result == 0) return false;
                return true;
            }
        });
        Boolean flag = (Boolean) obj;
        if (!flag.booleanValue()) {
            new Exception("update fail");
        }
    }

    /**
     * 获取设备列表
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getDeviceList(final int hostId) throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select device_id,device_name,host_id,device_maintoken,device_subtoken,device_mainstream,device_substream from service.device where host_id=" + hostId).list();

            }
        });
        // return (List<Device>) this.getHibernateTemplate().find("from Device d where d.host.hostId=" + hostId);
    }

    /**
     * 获取设备列表
     * @return
     * @throws Exception
     */
    @Override
    public List getDeviceList() throws Exception {
        String sql = "select host_id from service.device_host";
        return (List) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
    }

    /**
     * 根据ID获取设备信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Device getDeviceInfo(int id) throws Exception {
        return (Device) this.getHibernateTemplate().get(Device.class, id);
    }

    /**
     * 添加设备
     * @param device
     * @throws Exception
     */
    @Override
    public void addDevice(Device device) throws Exception {
        this.getHibernateTemplate().save(device);
    }


    /**
     * 删除设备信息
     * @param id
     * @throws Exception
     */
    @Override
    public void delDevice(int id) throws Exception {
        Device device = new Device();
        device.setDeviceId(id);
        this.getHibernateTemplate().delete(device);
    }


//    @Override
//    public void updateDevicceViewClass(final int deviceId,final int viewClass) throws Exception {
//        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int n = session.createSQLQuery("update device set is_viewclass=" + viewClass + " where device_id=" + deviceId).executeUpdate();
//                if (n == 0) throw new HibernateException("update failure");
//                return null;
//            }
//        });
//    }

    /**
     * 根据hostid获取maintoken
     * @param hostid
     * @param deviceName
     * @return
     * @throws Exception
     */
    @Override
    public String getMainTokenByHostid(final int hostid, final String deviceName) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select device_maintoken from service.device where host_id = " + hostid + " and device_name = '" + deviceName + "'").uniqueResult();
            }
        });
        // System.out.println(result.getClass().getName()+"----------");
        String res = result == null ? "" : result.toString();
        return res;
    }

    /**
     * 通过hostid获取subtoken
     * @param hostid
     * @param deviceName
     * @return
     * @throws Exception
     */
    @Override
    public String getSubTokenByHostid(final int hostid, final String deviceName) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select device_subtoken from service.device where host_id = " + hostid + " and device_name = '" + deviceName + "'").uniqueResult();
            }
        });
        // System.out.println(result.getClass().getName()+"----------");
        String res = result == null ? "" : result.toString();
        return res;
    }

    /**
     * 通过设备名称获取设备列表
     * @param specid
     * @return
     * @throws Exception
     */
    @Override
    public List getDeviceBySpec(final int specid) throws Exception {

        return (List) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select device_id,device_name,dspec_id,host_id,device_maintoken,device_subtoken from service.Device where dspec_id=" + specid).list();

            }
        });

    }

    /**
     * 获取学生教师流
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getTeachAndStudentStream(final int hostId) throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("SELECT device_name,device_mainstream,device_substream FROM service.device where (device_name='教师全景' or device_name='学生全景') and host_id=" + hostId).list();
            }
        });

    }

    /**
     * 获取直播流
     * @param hostId
     * @param deviceName
     * @return
     * @throws Exception
     */
    @Override
    public Object[] getStream(final int hostId, final String deviceName) throws Exception {
        return (Object[]) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("SELECT device_mainstream,device_substream FROM service.device where host_id=" + hostId + " and device_name='" + deviceName + "'").uniqueResult();
            }
        });
    }

//    @Override
//    public void updateTBOXDeviceData(String device_name, String device_maintoken) throws Exception {
//        final String sql = "UPDATE `device` SET device_name = '"+device_name+"' WHERE device_maintoken = '"+device_maintoken+"'";
//        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                session.createSQLQuery(sql).executeUpdate();
//                return null;
//            }
//        });
//    }

    @Override
    public void updateTBOXDeviceName(String deviceName, Integer deviceId) throws Exception {
        final String sql = "UPDATE service.device set device_name=? WHERE device_id=? ";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter(0, deviceName).setParameter(1, deviceId).executeUpdate();
    }

    @Override
    public Integer getTBOXDeviceId(String token) throws Exception {
        final String sql = "SELECT device_id FROM service.device WHERE device_maintoken=? or device_subtoken=? ";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter(0, token).setParameter(1, token).uniqueResult();

    }
/*---------------------------------------------------------------跨库访问设备信息------------------------------------------------------------*/


    //通过设备id跨域获取设备信息
    @Override
    public Map getHostInfoById(String hostId) throws Exception {
        final String sql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_serialno,h.host_desc," +
                "h.host_username,h.host_password,h.dspec_id,h.host_factory,d.dtype_id," +
                "t.dtype_name,h.host_mac,h.host_port,h.host_systype,h.host_channel from service.device_host h " +
                "left join service.device_specification d on h.dspec_id = d.dspec_id " +
                "left join service.device_type t on t.dtype_id = d.dtype_id " +
                "where h.host_id=" + hostId;
        return (Map) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return (Map)session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
            }
        });
}

    //通过设备ip跨域获取设备信息
    @Override
    public Map<String, Object> getHostInfoByIp(String ip) throws Exception {

        final String sql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_serialno,h.host_desc," +
                "h.host_username,h.host_password,h.dspec_id,h.host_factory,d.dtype_id,t.dtype_name," +
                "h.host_mac ,h.host_hhtcmac,h.host_port,h.host_channel,h.host_systype from service.device_host h " +
                "left join service.device_specification d on h.dspec_id = d.dspec_id " +
                "left join service.device_type t on t.dtype_id = d.dtype_id " +
                "where h.host_ipaddr='" + ip + "'";
        return (Map<String, Object>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return (Map)session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
            }
        });
    }

    /**
     * 获取某设备类型的设备数量
     * @param type 设备类型
     * @return
     */
    public int getHostCount(String type) {
        String sql = "select count(*) as count from service.device_host h " +
                "left join service.device_specification d on h.dspec_id = d.dspec_id " +
                "left join service.device_type t on t.dtype_id = d.dtype_id ";
        int count = 0;
        if (type.toUpperCase().equals("HHREC")) {
            sql += "where t.dtype_name in ('NVR','IPC','AREC')";
        } else if (type.toUpperCase().equals("OTHER")) {
            sql += "t.dtype_name = 'UNKNOWN' ";
        } else {
            sql += "where t.dtype_name = '" + type.toUpperCase() + "'";
        }

            Map map = (Map) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
        return  Integer.parseInt(map.get("count").toString());
    }

    /**
     * update by zlj on 2018/04/12
     * 通过设备类型获取设备id信息
     * @param deviceType 设备信息
     * @return
     */
    public List<Map<String, String>> getHostByType(String deviceType) {
        String sql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_serialno,h.host_desc,h.host_username,h.host_password,h.dspec_id," +
                "d.dtype_id,t.dtype_name,h.host_port,h.host_channel  " +
                "from service.device_host h " +
                "left join service.device_specification d on h.dspec_id = d.dspec_id " +
                "left join service.device_type t on t.dtype_id = d.dtype_id ";
        if (deviceType.equals("")) {
            sql += " order by h.host_name";
        } else {
            if (deviceType.toUpperCase().equals("HHREC")) {
                sql += " where t.dtype_name in('NVR','IPC','AREC') order by h.host_name";
            } else if (deviceType.toUpperCase().equals("OTHER")) {
                sql += " where t.dtype_name = 'UNKNOWN' order by h.host_name";
            } else {
                sql += " where t.dtype_name = '" + deviceType.toUpperCase() + "' order by h.host_name";
            }
        }
        return  this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
    }

//    @Override
//    public Integer getTBOXDeviceConfigId(String token) throws Exception {
//        final String sql = "SELECT id FROM deviceconfig WHERE main=? or sub=? ";
//        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
//                .setParameter(0, token).setParameter(1, token).uniqueResult();
//    }
//
//    @Override
//    public void updateTBOXDeviceConfigName(String deviceName, Integer deviceId) throws Exception {
//        final String sql = "UPDATE deviceconfig set name=? WHERE id=? ";
//        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
//                .setParameter(0, deviceName).setParameter(1, deviceId).executeUpdate();
//    }
}
