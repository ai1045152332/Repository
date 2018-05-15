package com.honghe.recordweb.util;

import com.honghe.recordhibernate.entity.User;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lyx on 2015-12-02.
 * <p/>
 * 输出实体类的所有属性值
 */
public class ModelPrinter {
    private final static Logger logger = Logger.getLogger(CommonName.class);

    /**
     * 输出一个实体类的全部属性
     *
     * @param model 实体类对象
     */
    public static String print(Object model) {
        Class cls = model.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuffer strb = new StringBuffer();
        strb.append(model.getClass().getName() + "：{\n");
        for (Field field : fields) {
            char[] buffer = field.getName().toCharArray();
            buffer[0] = Character.toUpperCase(buffer[0]);
            String mothodName = "get" + new String(buffer);
            try {
                Method method = cls.getDeclaredMethod(mothodName);
                Object resutl = method.invoke(model, null);
                strb.append(field.getName() + ": " + resutl + "\n");
            } catch (Exception e) {
                logger.error("输出实体类属性异常", e);
            }
        }
        strb.append("}");
        return strb.toString();
    }

    /**
     * 输出实体类指定的属性
     *
     * @param model 实体类对象
     * @param fields 指定属性值
     */
    public static String print(Object model, String[] fields) {
        Class cls = model.getClass();
        StringBuffer strb = new StringBuffer();
        strb.append(model.getClass().getName() + "：{\n");
        for (String field : fields) {
            char[] buffer = field.toCharArray();
            buffer[0] = Character.toUpperCase(buffer[0]);
            String mothodName = "get" + new String(buffer);
            try {
                Method method = cls.getDeclaredMethod(mothodName);
                Object resutl = method.invoke(model, null);
                strb.append(field + ": " + resutl + "\n");
            } catch (Exception e) {
                logger.error("输出实体类属性异常", e);
            }
        }
        strb.append("}");
        return strb.toString();
    }


    /**
     * 测试
     * @param arg
     */
    public static void main(String[] arg) {
        User user = new User();
        user.setUserId(1000);
        user.setUserAddress("123456");
        user.setUserEmail("12");
        logger.info(ModelPrinter.print(user));
    }
}
