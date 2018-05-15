package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.CommandDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Command;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Moon on 2014/9/16.
 */
@Repository
public class CommandDaoImpl extends BaseDao implements CommandDao {
    private final static  Logger logger = Logger.getLogger(CommandDaoImpl.class);

    /**
     * 通过id获取命令
     * @param id
     * @return
     */
    @Override
    public Command getCommandById(int id) {
        Command command = new Command();
        command.setCmdId(id);

        List<Command> list = null;
        try {
            //List<Command> list_1 = this.getHibernateTemplate().findByExample(command);
            list = (List<Command>) this.getHibernateTemplate().find("from Command where cmdId = ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (list.size() >= 1) {
            return list.get(0);
        }

        return null;
    }

    /**
     * 获取命令列表
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Command> getDeviceCommList(final Page page) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Command> result = null;
        int totalCount = hibernateTemplate.find("from Command").size();
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            String str_sql = "select dspec_id,dspec_name,dtype_id,connect_param from dspecification limit " + page.getFirstResult() + "," + page.getPageSize();
            final String sql = str_sql;
            final String hql = "from Command";
            result = (List<Command>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    org.hibernate.Query query = session.createQuery(hql).setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize());
                    List<Command> list = query.list();
                    return list;
                }
            });

        }

        return result;
    }

    /**
     * 获取主机列表
     * @return
     */
    @Override
    public List<Command> getHostCommList() {
        Command command = new Command();
        command.setCmdFlag("10");

        List<Command> list = null;

        try {
            list = this.getHibernateTemplate().findByExample(command);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }

        return list;
    }

    /**
     * 通过命令名称获取设备命令
     * @param name
     * @return
     */
    @Override
    public List<Command> getDeviceCommByName(String name) {
        Command command = new Command();
        command.setCmdFlag("01");//todo 加注释
        command.setCmdName(name);

        List<Command> list = null;

        try {
            list = this.getHibernateTemplate().findByExample(command);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }

        return list.size() == 0 ? null : list;
    }

    /**
     * 通过hex码获取设备命令
     * @param hex
     * @return
     */
    @Override
    public List<Command> getDeviceCommByHex(String hex) {
        Command command = new Command();
        command.setCmdFlag("01");
        command.setCmdHex(hex);

        List<Command> list = null;

        try {
            list = this.getHibernateTemplate().findByExample(command);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
        }

        return list.size() == 0 ? null : list;
    }

    /**
     * 保存命令
     * @param command
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(Command command) throws Exception {
        try {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(command);
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("", e);
            return false;
        }

        return true;
    }

    /**
     * 修改命令
     * @param command
     * @return
     * @throws Exception
     */
    public boolean updata(Command command) throws Exception {
        try {
            this.getHibernateTemplate().update(command);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除命令
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(final int id) throws Exception {
        Command command = new Command();
        command.setCmdId(id);
        try {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);

            int spec2command = Integer.parseInt(this.spec2command(id));
            if (spec2command > 0) {
                this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        Integer datacount = session.createQuery("delete from Spec2command where cmd_id = " + id).executeUpdate();
                        if (datacount > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
            }
            this.getHibernateTemplate().delete(command);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param commandid
     * @return
     */
    private String spec2command(final int commandid) {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from spec2command where cmd_id=" + commandid).uniqueResult();
            }
        }).toString();
        return result;
    }

    /**
     * 获取命令列表
     *
     * @return List 类型数据
     * @throws Exception
     */
    @Override
    public List<Command> getCommandList() throws Exception {
        List<Command> list = null;

        try {
            list = (List<Command>) this.getHibernateTemplate().find("from Command");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return list;
    }

    /**
     * 根据设备型号获取设备支持的功能
     *
     * @param dspecid
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getSepcCmd(String dspecid) throws Exception {
        List<Object[]> result = null;
        String str_sql = "select GROUP_CONCAT(cmd_name order by cmd_name asc) codetype,b.cmd_group, GROUP_CONCAT(code_code order by cmd_name asc) code_cmd FROM command_code a,command b" +
                " where a.code_type = b.cmd_hex and b.cmd_group is not NULL and dspec_id = '" + dspecid + "' GROUP BY cmd_group order BY cmd_name";
        final String sql = str_sql;

        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * 获取所有命令
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getAllCmd() throws Exception {
        List<Object[]> result = null;
        String str_sql = "select GROUP_CONCAT(DISTINCT b.cmd_name order by b.cmd_name asc) codetype, b.cmd_group, GROUP_CONCAT(DISTINCT a.code_code order by b.cmd_name asc) code_cmd FROM command_code a, command b " +
                " where a.code_type = b.cmd_hex and b.cmd_group is not NULL GROUP BY cmd_group order BY cmd_name";
        final String sql = str_sql;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }
}
