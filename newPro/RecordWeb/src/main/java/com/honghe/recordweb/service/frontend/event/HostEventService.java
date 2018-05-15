package com.honghe.recordweb.service.frontend.event;

import com.honghe.recordhibernate.dao.HostEventDao;
import com.honghe.recordhibernate.entity.Hostevent;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hthwx on 2015/2/3.
 */
@Service("hostEventService")
public class HostEventService {
    private final static Logger logger = Logger.getLogger(HostEventService.class);
    @Resource
    private HostEventDao hostEventDao;
    @Resource
    private HostgroupService hostgroupService;

    /**
     * 获取所有数据的list
     *
     * @return List<Hostevent>
     */
    public List<Hostevent> getListService() {
        try {
            return hostEventDao.getHostEventList();
        } catch (Exception e) {
            logger.error("", e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 获取所有事件信息的map
     *
     * @return Map<String,String>
     */
    public Map<String, String> getMapService() {
        Map<String, String> heMap = new HashMap<String, String>();
        List<Hostevent> heList = getListService();
        if (heList != null && heList.size() > 0) {
            for (Hostevent he : heList) {
                heMap.put(he.getEventName(), String.valueOf(he.getEventId()));
            }
        }
        return heMap;
    }
}
