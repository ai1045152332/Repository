package com.honghe.recordweb.service.frontend.DBHelper;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by hthwx on 2015/3/12.
 */
public class SqlLiteTool {
    private final static Logger logger = Logger.getLogger(SqlLiteTool.class);
    private String DB_PATH = "jdbc:sqlite:/G:/DevManager/jkManager/trunk/WebPlatform/DManager/out/artifacts/RecordWeb_war_exploded/cmdSql/";
    private final String DB_DRIVER = "org.sqlite.JDBC";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rSet = null;
    private boolean isAutoCommit = false;

    public String getDB_PATH() {
        return DB_PATH;
    }

    public void setDB_PATH(String DB_PATH) {
        this.DB_PATH = "jdbc:sqlite:/" + DB_PATH;
    }

    /**
     * 获取数据连接
     * @param dbName
     * @param autoCommit
     * @return
     */
    public Connection sqLiteConn(String dbName, boolean autoCommit) {
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_PATH);
            if (autoCommit == true) {
                this.isAutoCommit = true;
            }
            conn.setAutoCommit(isAutoCommit);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            logger.error("", e);
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        return conn;
    }

    /**
     * 获取command
     * @param dbName
     * @param autoCommit
     * @return
     */
    public Statement sqLiteStatement(String dbName, boolean autoCommit) {
        if (conn == null) {
            sqLiteConn(dbName, autoCommit);
        }
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        return stmt;
    }

    /**
     * sql语句执行
     * @param sql
     * @param isCloseConn
     * @return
     */
    public boolean sqLiteExecuteSql(String sql, boolean isCloseConn) {
        boolean result = false;
        try {
            if (stmt == null) {
                if (conn != null) {
                    stmt = conn.createStatement();
                } else {
                    logger.info("sqLite is not connect!");
                    return result;
                }
            }
            result = stmt.execute(sql);
            if (!isAutoCommit) {
                conn.commit();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error("", e);
            logger.info("exec failed with some reasons!");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            logger.info("exec failed!");
        } finally {
            if (isCloseConn) {
                try {
                    conn.commit();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    logger.info("SQLException in finally (conn close)", e);
                }
            }
        }
        return result;
    }

    /**
     * 获取数据
     * @param sql
     */
    public void sqLiteExecuteSqlSimple(String sql) {
        //sql语句执行no commit,no close
        if (stmt != null) {
            try {
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            this.sqLiteExecuteSql(sql, false);
        }
    }

    /**
     * conn提交
     */
    public void sqLiteConnCommit() {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * todo 加注释
     * @param tbName
     * @param sql
     * @param isCloseConn
     * @return
     */
    public boolean sqLiteCreateTable(String tbName, String sql, boolean isCloseConn) {
        boolean bool = false;
        if (stmt != null) {
            try {
                stmt.execute("DROP TABLE IF EXISTS " + tbName);
                stmt.execute(sql);
                conn.commit();
                bool = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (isCloseConn) {
                    sqLiteClose();
                }
            }
        }
        return bool;
    }

    /**
     * todo 加注释
     */
    public void sqLiteClose() {
        try {
            if (rSet != null) rSet.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {

            //e.printStackTrace();
            logger.error("", e);
        }
    }
}
