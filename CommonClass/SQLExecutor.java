package com.honghe.managerTool.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

public class SQLExecutor {
    private String type;
    private String url;
    private String userName;
    private String password;
    private String delimiter;

    public SQLExecutor(String type, String url, String userName, String password, String delimiter) {
        this.type = type;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.delimiter = delimiter;
    }

    public SQLExecutor(String type, String url, String userName, String password) {
        this(type, url, userName, password, ";");
    }

    public void importFile(String sqlContent) throws Exception{
        SQLExec sqlExec = new SQLExec();
        sqlExec.setDriver(type);
        sqlExec.setDelimiter(this.delimiter);
        sqlExec.setUrl(this.url);
        sqlExec.setUserid(this.userName);
        sqlExec.setPassword(this.password);
        sqlExec.addText(sqlContent);
        sqlExec.setOnerror((SQLExec.OnError)((SQLExec.OnError) EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project());
        try{
            sqlExec.execute();
        }catch (Exception e){
            System.out.println(e);
        }


    }

}
