package com.honghe.recordhibernate.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.honghe.dao.SQLExecuter;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Moon on 2014/8/15.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/jdbc.properties")
@ComponentScan("com.honghe")
public class DataSourceConfig {

    @Resource
    private Environment env;


    private DataSource getDataSource() {
        importSqlFile();
        com.alibaba.druid.pool.DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        basicDataSource.setInitialSize(1);
        basicDataSource.setTimeBetweenEvictionRunsMillis(60000);
        basicDataSource.setMinEvictableIdleTimeMillis(300000);
        basicDataSource.setUrl(env.getProperty("jdbc.url"));
        basicDataSource.setUsername(env.getProperty("jdbc.username"));
        basicDataSource.setPassword(env.getProperty("jdbc.password"));
        return basicDataSource;
    }
    private void importSqlFile(){
        String sqlContent = null;
        try {
            sqlContent = IOUtils.toString(DataSourceConfig.class.getResourceAsStream("/dmanager.sql"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sqlContent != null){
            SQLExecuter sqlExecuter = new SQLExecuter(SQLExecuter.Type.MYSQL, env.getProperty("jdbc.url").replaceAll("dmanager",""), env.getProperty("jdbc.username"),
                    env.getProperty("jdbc.password"));
            sqlExecuter.importFile(sqlContent);
        }
    }


    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getLocalSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.show_sql", "false");
//        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("com.honghe.recordhibernate");
//        localSessionFactoryBean.setAnnotatedClasses(User.class,Applog.class,Authority.class,Command.class,Device.class
//        ,Dspecification.class,Dtype.class,Host.class,User2Role.class,Hostgroup.class,News.class,
//                Policy.class,Role.class,Spec2command.class,Status.class,Applog.class);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(this.getLocalSessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }



}
