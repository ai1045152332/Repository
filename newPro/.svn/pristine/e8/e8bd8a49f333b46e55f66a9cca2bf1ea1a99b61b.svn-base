package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Touchscreen;

import javax.crypto.interfaces.PBEKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sky on 2016/5/19.
 */
public interface TouchscreenDao {

   public List<Object[]> getTouchScreenListByUser(Page page,int uid,String type )throws Exception;

   public List<Touchscreen> getTouchScreenlist()throws Exception;

   public HashMap<String, Integer> getTouchScreenHosts(Touchscreen touchscreen)throws Exception;

   public boolean save(Touchscreen touchscreen)throws Exception;

   public void addTouchToHost(final int touchId,final int hostId,final String hostName)throws Exception;

   public Touchscreen getTouchListById(int id)throws Exception;

   public boolean updateTouch(Touchscreen touchscreen)throws Exception;

   public boolean isTouchHostExists(final int id)throws Exception;

   public void delTouchHostByTouch(final int id)throws Exception;

   public boolean delTouchscreen(Touchscreen touchscreen)throws Exception;

   public boolean isTouchHostExistsByHost(final int id) throws Exception;

   public void delTouchHostByHost(final int id) throws Exception;

}

