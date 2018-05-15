package com.honghe.recordweb.action;

import javax.servlet.http.HttpSession;

/**
 * Created by wj on 2014-09-30.
 */
public final class SessionManager {


    public enum Key {
        USER("user"), ROLE("role"), Authority("authority"), USERBACK("userback"), AuthorityBACK("authorityback"), DeviceType("devicetype");
        private String key;

        Key(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return this.key;
        }
    }

    private SessionManager() {

    }

//    public final static<T> void add(HttpSession session,T obj){
//        session.setAttribute(obj.getClass().getSimpleName(),obj);
//    }
//
//    public final static<T> T get(HttpSession session, Class c){
//        return (T)session.getAttribute(c.getSimpleName());
//    }

    public final static void add(HttpSession session, Key key, Object obj) {
        session.setAttribute(key.toString(), obj);
    }

    public final static <T> T get(HttpSession session, Key key) {
        return (T) session.getAttribute(key.toString());
    }

    public final static void del(HttpSession session, Key key) {
        session.removeAttribute(key.toString());
    }
}
