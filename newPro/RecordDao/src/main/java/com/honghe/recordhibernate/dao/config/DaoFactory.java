package com.honghe.recordhibernate.dao.config;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * Created by chby on 2014/9/26.
 */
public class DaoFactory {

    private DaoFactory() {

    }

    public final static <T> T getDao(Class c) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.honghe");
        return (T) annotationConfigApplicationContext.getBean(c);
    }

    public static void createTable(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.honghe");
        LocalSessionFactoryBean localSessionFactoryBean =  annotationConfigApplicationContext.getBean(LocalSessionFactoryBean.class);
        new SchemaExport(localSessionFactoryBean.getConfiguration()).create(true, false);
    }

    public static void main(String[] args) {

        createTable();
    }
}
