package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.InitializationDao;
import com.honghe.recordhibernate.entity.Initialization;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Techer on 2016/7/6.
 */
@Repository
public class InitializationDaoimpl extends BaseDao implements InitializationDao {
    /**
     * 初始化
     * @return
     * @throws Exception
     */
    @Override
    public List<Initialization> getInitializationList() throws Exception {
        return (List<Initialization>) this.getHibernateTemplate().find(" from Initializations ");
    }

    /**
     * 通过类型获取默认通道
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public Initialization getInitializationListbytype(String type) throws Exception {
        List<Initialization> initialist =(List<Initialization>)this.getHibernateTemplate().find("from Initialization where type='"+type+"'");
        if (!initialist.isEmpty()){
            return initialist.get(0);
        }

        return null;
    }

    /**
     * 更新默认信息数据库
     * @param initialization
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateInitializationInfo(Initialization initialization)throws Exception{
     try {
         this.getHibernateTemplate().update(initialization);
         return true;
     }catch (Exception e){
         e.printStackTrace();
         return false;
     }
    }

    /**
     * 获取设备通道信息
     * @param devicetype
     * @return
     * @throws Exception
     */
    @Override
    public List getChannel(String devicetype)throws Exception{
        List channelist =null;
        int type = 0;
        if ("hhtc".equals(devicetype)) {
            type = 4;//大屏
        } else if ("hhtwb".equals(devicetype)){
            type = 6;//白板
        }
        final String sql = "select cmd_name from command where cmd_hex in (select code_type from command_code where code_group = 'Channel' and dspec_id in (select dspec_id from dspecification where dtype_id = '"+type+"') group by code_type having count(code_type)=(select count(*) from command_code where code_group = 'Channel' and dspec_id in (select dspec_id from dspecification where dtype_id = '"+type+"') group by code_type order by count(*) desc limit 0, 1))";
        channelist = (List) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();

            }
        });
        return channelist;
    }
}
