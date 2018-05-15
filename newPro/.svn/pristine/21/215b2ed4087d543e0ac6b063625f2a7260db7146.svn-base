package com.honghe.recordweb.service.frontend.event;

import com.honghe.recordhibernate.dao.EventFieldDao;
import com.honghe.recordhibernate.entity.Eventfield;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * Created by hthwx on 2015/2/3.
 */
@Service("eventfieldService")
public class EventFieldService {
    private final static Logger logger = Logger.getLogger(EventFieldService.class);
    @Resource
    private EventFieldDao eventFieldDao;

    /**
     * 获取所有的事件字段的信息list
     *
     * @return
     */
    public List<Eventfield> getListService() {
        try {
            return eventFieldDao.getEventFieldList();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取事件字段的信息list异常", e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 根据当前指定的belong类型，获取list，对应的是 事件的主键
     * @param belong
     * @return
     */
    public List<Object[]> getListByBelongService(String belong) {
        try {
            return eventFieldDao.getEventFieldListByBelong(belong);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return Collections.EMPTY_LIST;
        }
    }
}
