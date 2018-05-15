package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Ringbell;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lch on 2015/6/18.
 */
public interface RingbellDao {

    public Ringbell getRingbell(int id)  throws Exception;

    public List<Object[]> getRingbellById(final int id ) throws Exception;

    public HashMap<String,Integer> getRingbellHosts(Ringbell ringbell) throws Exception;

    public List<Ringbell> getRingbellList() throws Exception;

    public List<Object[]> getRingbellListByUser(Page page,int uid,String type) throws Exception;

    public boolean saveRingbell(Ringbell ringbell) throws Exception;

    public boolean updateRingbell(Ringbell ringbell)  throws Exception;

    public boolean delRingbell(Ringbell ringbell)  throws Exception;

    public boolean isRingbellHostExists(final int id) throws Exception;

    public void addRingbellToHost(final int ringbellId, final int hostId,final String hostName) throws Exception;

    public void delRingbellHostByRingbell(final int id) throws Exception;

    public void delRingbellHostByHost(final int id) throws Exception;
}


