package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.CmdCodeDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.CommandCode;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lch on 2015/3/20.
 */
@Repository
public class CmdCodeDaoImpl extends BaseDao implements CmdCodeDao{

    /**
     * 根据id获取编码命令
     * @param cmdCodeId
     * @return
     * @throws Exception
     */
    @Override
    public CommandCode getCommandCodeInfo(int cmdCodeId) throws Exception{
        CommandCode commandCode=new CommandCode();
        commandCode.setCodeId(cmdCodeId);
        List<CommandCode> list=null;
        try
        {
            list=(List<CommandCode>)this.getHibernateTemplate().find("from CommandCode where codeId=?",cmdCodeId);
            if (!list.isEmpty())
            {
                return list.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return null;
    }


    /**
     * 获取某一类型设备所有编码命令
     * @param despId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCommandCodesListByDesp(int despId) throws Exception{
        List<Object[]> result = null;
        String str_sql = "select GROUP_CONCAT(cmd_name order by cmd_name asc) code_type,b.cmd_group, GROUP_CONCAT(code_code order by cmd_name asc) code_cmd,GROUP_CONCAT(code_name order by cmd_name asc) code_name FROM command_code a,command b" +
                " where a.code_type = b.cmd_hex and b.cmd_group is not NULL and dspec_id = '"+despId+"' GROUP BY cmd_group order BY cmd_name";
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
     * 获取所有编码命令
     * @return
     * @throws Exception
     */
    @Override
    public List<CommandCode> getCommandCodesListAll() throws Exception{
        return null;
    }


    /**
     * 新加方法实现
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List getDeviceCodeList(final Page page)throws Exception{
        HibernateTemplate hibernateTemplate=this.getHibernateTemplate();
        List result=null;
        int totalCount=hibernateTemplate.find("from CommandCode").size();
        page.setTotalPageSize(totalCount);
        if (totalCount!=0)
        {
            final String sql ="select code_id,code_name,code_type,code_remark," +
                    "code_instruction,code_code,code_flag,code_group,d.dspec_name from command_code c " +
                    "LEFT JOIN dspecification d on c.dspec_id = d.dspec_id order by code_id DESC " +
                    "limit "+page.getFirstResult()+","+page.getPageSize();

            result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    System.out.println(sql);
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        return result;
    }

    /**
     * 保存到数据库
     * @param commandCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(CommandCode commandCode) throws Exception{
        try
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(commandCode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除命令
     * @param CodeId
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(int CodeId) throws Exception{
        CommandCode commandCode=this.getCommandCodeInfo(CodeId);
        try{
           this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().delete(commandCode);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改命令
     * @param commandCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean updata(CommandCode commandCode) throws Exception{
        try
        {
            this.getHibernateTemplate().update(commandCode);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过设备名称来查询命令编码
     * @param name
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCodeBydespName(String name,Page page) throws Exception {
        final String s_sql = "select count(*) from command_code c,dspecification d where c.dspec_id=d.dspec_id  and d.dspec_name='"+name+"'";
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> commandCodes = null;
        List countList = null;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {

                return session.createSQLQuery(s_sql).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            final String sql = "select c.code_id,c.code_name,c.code_type,c.code_remark,c.code_instruction,code_code,c.code_flag,c.code_group,d.dspec_name from command_code c,dspecification d where c.dspec_id=d.dspec_id  and d.dspec_name='"+name+"'"
                        + " limit "+page.getFirstResult()+ " , " +page.getPageSize();
            System.out.print(page.getFirstResult());
            commandCodes = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    System.out.println(sql);
                    return session.createSQLQuery(sql).list();
                }
            });
        }else{
            commandCodes=null;
        }
        return commandCodes;
    }

    /**
     * 通过命令名称来查询命令编码
     * @param name
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCodeBycmdName(String name,Page page) throws Exception {
        final String s_sql = "select count(*) from command_code c,command cd where cmd_name='"+name+"' and c.code_type=cd.cmd_hex ";
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List commandCodes = null;
        List countList = null;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {

                return session.createSQLQuery(s_sql).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            final String sql = "select c.code_id,c.code_name,c.code_type,c.code_remark,c.code_instruction,c.code_code,c.code_flag,c.code_group,d.dspec_name from command_code c,dspecification d, command cd where c.code_type=cd.cmd_hex  and cmd_name='"+name+"' and c.dspec_id=d.dspec_id"
                        + " limit " +page.getFirstResult()+ " , " +page.getPageSize();
            commandCodes = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    System.out.println(sql);
                    return session.createSQLQuery(sql).list();
                }
            });
        }else {
            commandCodes=null;
        }
        return commandCodes;
    }

    /**
     * 通过命令名称获取编码
     * @param dname
     * @param cname
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCodeByname(String dname,String cname,Page page) throws Exception{
        final String s_sql;
            s_sql=" select count(*) from command_code c,dspecification d,command cd where c.dspec_id=d.dspec_id and c.code_type=cd.cmd_hex and d.dspec_name='"+dname+"' and cd.cmd_name='"+cname+"'"
                    + " limit " +page.getFirstResult()+ " , " +page.getPageSize();
        HibernateTemplate hibernateTemplate=this.getHibernateTemplate();
        List commandCodes=null;
        List countList=null;
        countList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql).list();
            }
        });
        int totalCount=Integer.parseInt(countList.get(0).toString());
        if (totalCount!=0) {
            final String sql  = "select c.code_id,c.code_name,c.code_type,c.code_remark,c.code_instruction,code_code,c.code_flag,c.code_group,d.dspec_name " +
                        "from command_code c,dspecification d,command cd where c.dspec_id=d.dspec_id and c.code_type=cd.cmd_hex and d.dspec_name='"+dname+"' and cd.cmd_name='"+cname+"'"
                        + " limit " + page.getFirstResult() + " , " + page.getPageSize();
            commandCodes = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    System.out.println(sql);
                    return session.createSQLQuery(sql).list();
                }
            });
        }else {
            commandCodes=null;
        }
        return commandCodes;
    }

    /**
     * 通过设备名称获取命令名称
     * @param dspecname
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCmdNameBydespc(final String dspecname) throws Exception{
        List result=null;
        final String sql1="select DISTINCT cmd_name from command cd,dspecification d,command_code c where d.dspec_id=c.dspec_id and c.code_type=cd.cmd_hex ";
        final String sql2="select DISTINCT cmd_name from command cd,dspecification d,command_code c where d.dspec_id=c.dspec_id and c.code_type=cd.cmd_hex and d.dspec_name='"+dspecname+"'";
        result=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                if (dspecname.equals("")){
                System.out.println(sql1);
                return session.createSQLQuery(sql1).list();
                }else{
                    System.out.println(sql2);
                    return session.createSQLQuery(sql2).list();
                }
            }
        });
       return result;
    }

    /**
     * 获取命令名称相对应的命令编码列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getCmdName() throws Exception{
        List<Object[]> result=null;
        final String sql="select DISTINCT cmd_name,cmd_hex from command  ";
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                System.out.println(sql);
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * 获取设备名称对应的命令编码列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getDspeName() throws Exception{
        List<Object[]> result=null;
        final String sql="select dspec_name ,dspec_id from dspecification ";
        result=(List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                System.out.println(sql);
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }
}
