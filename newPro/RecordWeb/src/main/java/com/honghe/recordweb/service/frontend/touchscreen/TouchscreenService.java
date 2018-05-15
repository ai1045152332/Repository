package com.honghe.recordweb.service.frontend.touchscreen;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.TouchscreenDao;
import com.honghe.recordhibernate.entity.Touchscreen;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sky on 2016/5/19.
 */
@Service
public class TouchscreenService {
    @Resource
    private TouchscreenDao touchscreenDao;
    @Resource
    private TouchscreenTimerService touchscreenTimerService;
    @Resource
    private SyslogService syslogService;

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param currentPage int,pageSize int
     * @return Map<String,Object>
     */
     @Transactional
    public Map<String,Object> touchscreenService(int currentPage,int pageSize,int uid,String type){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        try{
            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage,pageSize);
            List<Object[]> result = touchscreenDao.getTouchScreenListByUser(page,uid,type);
            map.put("touchscreenlist",result);
            map.put("pageCount",String.valueOf(page.getTotalPageSize()));
        }catch (Exception e){
            e.printStackTrace();

        }
        return map;
    }

    /**
     * 判断某一id的定时计划是否发布给班级
     *
     * @param id int 定时计划id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean touchHostExistsService(int id) {
        try {
            return touchscreenDao.isTouchHostExists(id);
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("判断某一id的定时计划是否发布给班级异常 id=" + id, e);
            return false;
        }
    }

    /**
     * 保存定时计划的信息
     *
     * @param touchscreen Touchscreen
     * @return
     */
    @Transactional
    public boolean addTouchScreenList(Touchscreen touchscreen,String hostid,String hostname,String hostIpStr){
        boolean flag=false;
        try {
            touchscreenDao.save(touchscreen);
            touchscreenTimerService.addTimerbyTouch(touchscreen,hostid);
            if (!hostid.equals("")) {
                String[] hosts = hostid.split(",");
                String[] hostNames = hostname.split(",");
                for (int i = 0; i < hosts.length; i++) {
                    addTouchToHostService(touchscreen.getTid(), Integer.parseInt(hosts[i]), hostNames[i]);
                }
            }
//            String log = "id:" + touchscreen.getTid() + "Type:" + touchscreen.getTouchtype() + "Loop:" + touchscreen.getTouchLoop() + "LoopType:" + touchscreen.gettLooptype()
//                    + "Date:" + touchscreen.getTouchDate() + "Week:" + touchscreen.getTouchWeek() + "Time:" + touchscreen.getTouchTime();
            syslogService.addDeviceLog(hostIpStr, hostIpStr + "添加了触控定时计划", "SYSTEM");
            flag=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 获取定时计划信息
     *
     * @param touchid
     * @return
     */
    @Transactional
    public Touchscreen getTouchListById(int touchid){
        Touchscreen touch=new Touchscreen();
        try {
            touch=touchscreenDao.getTouchListById(touchid);
            return touch;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 更新定时计划信息
     *
     * @param touchscreen Touchscreen自定义定时计划类型
     * @return
     */
    @Transactional
    public boolean updateTouchList(Touchscreen touchscreen,String hoststr,String hostname,String hostIpStr){
            try {
                if (touchscreenDao.updateTouch(touchscreen)){
                    if (hoststr!=null&&!"".equals(hoststr)){
                        if (touchHostExistsService(touchscreen.getTid())) {
                            touchscreenTimerService.delTimerbyTouch(touchscreen.getTid());
                            touchscreenTimerService.addTimerbyTouch(touchscreen, hoststr);
                            delTouchHostService(touchscreen.getTid());
                            String[] hostsid = hoststr.split(",");
                            String[] names = hostname.split(",");
                            for (int i = 0; i < hostsid.length; i++) {
                                int hostid = Integer.parseInt(hostsid[i]);
                                String hostName = names[i];
                                addTouchToHostService(touchscreen.getTid(), hostid, hostName);
                            }
                        }else {
                            touchscreenTimerService.addTimerbyTouch(touchscreen, hoststr);
                            String[] hostsid = hoststr.split(",");
                            String[] names = hostname.split(",");
                            for (int i = 0; i < hostsid.length; i++) {
                                int hostid = Integer.parseInt(hostsid[i]);
                                String hostName = names[i];
                                addTouchToHostService(touchscreen.getTid(), hostid, hostName);
                            }
                        }
                    }
                    String log = "id:" + touchscreen.getTid() + "Type:" + touchscreen.getTouchtype() + "Loop:" + touchscreen.getTouchLoop() + "LoopType:" + touchscreen.gettLooptype()
                            + "Date:" + touchscreen.getTouchDate() + "Week:" + touchscreen.getTouchWeek() + "Time:" + touchscreen.getTouchTime();
                    syslogService.addDeviceLog(hostIpStr, hostIpStr + "添加了触控定时计划", "SYSTEM");
                }

            return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }
    /**
     * 将定时计划发布给一个班级
     *
     * @param touchId int 定时计划id
     * @param hostId   int 班级id
     * @return
     */
    @Transactional

    private boolean addTouchToHostService(int touchId, int hostId,String hostName){
        try {
            touchscreenDao.addTouchToHost(touchId, hostId, hostName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    /**
     * 删除某班级的定时计划信息
     *
     * @param id int
     * @return
     */
    @Transactional
    public boolean delTouchByHostService(int id){
       try {
           touchscreenDao.isTouchHostExistsByHost(id);
           touchscreenTimerService.delTimerbyHost(id);
           touchscreenDao.delTouchHostByHost(id);
           return true;
       }catch (Exception e){
           e.printStackTrace();
//           logger.error("删除某班级的定时计划信息异常 hid=" + hid, e);
           return false;
       }
    }
    /**
     * 删除某定时计划的所有班级关系
     *
     * @param id int 定时计划id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    private boolean delTouchHostService(int id) {
        try {
            touchscreenDao.delTouchHostByTouch(id);
            return true;
        } catch (Exception e) {
//            logger.error("删除某定时计划的所有班级关系异常 id=" + id, e);
            return false;
        }
    }
    /**
     * 删除定时计划信息
     *
     * @param touchscreen Touchscreen
     * @return
     */
    @Transactional
    public  boolean delTouchService(Touchscreen touchscreen,String hostStr){
        try {
            int id = touchscreen.getTid();
            if (touchHostExistsService(id)) {
                touchscreenTimerService.delTimerbyTouch(id);
                touchscreenDao.delTouchHostByTouch(id);
                touchscreenDao.delTouchscreen(touchscreen);
            } else {
                touchscreenDao.delTouchscreen(touchscreen);
            }
            syslogService.addDeviceLog(hostStr, hostStr + "删除了定时计划:" + touchscreen.getTid() + "", "SYSTEM");
            return true;
        } catch (Exception e) {
              e.printStackTrace();
//            logger.error("删除定时计划信息异常", e);
            return false;
        }
    }
}
