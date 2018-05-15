package com.honghe.recordweb.service.util;

import com.honghe.recordweb.util.RWProperties;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

/**
 * Created by lch on 2015/1/16.
 */
public class RWPropertiesTest {
    //private RWProperties rwProperties;
//    @Before
//    public void setUp() throws Exception {
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.honghe");
//        rwProperties = annotationConfigApplicationContext.getBean(RWProperties.class);
//        //deviceDao = annotationConfigApplicationContext.getBean(DeviceDao.class);
//
//    }
    @Test
    public void testReadPropertiesFile(String filename){
        filename = "system.properties";
        Properties properties = RWProperties.readPropertiesFile(filename);
        System.out.print(properties.getProperty("NetworkInterface"));
    }
}
