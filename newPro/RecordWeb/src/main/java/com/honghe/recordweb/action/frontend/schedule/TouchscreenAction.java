package com.honghe.recordweb.action.frontend.schedule;


import com.alibaba.fastjson.JSONObject;
import com.honghe.recordhibernate.entity.Touchscreen;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.touchscreen.TouchscreenService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * Created by sky on 2016/5/18.
 */
@Controller
@Scope(value = "prototype")
public class TouchscreenAction extends BaseAction {
    private int userId;
    private int currentPage;
    private int pageCount;
    private int touchid;
    private int touchstype;
    private int touchloop;
    private String tlooptype;
    private int touchdate;
    private int touchweek;
    private String touchtime;
    private String tsingletime;
    private String tdevicetype;
    private String hostStr;
    private String hostNameStr;
    private String hostIpStr;
    private String userName;//用户名
    private String userid;//用户名id
    private final String TOUCHSCREEN_LIST = "touchscreenList";
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    private UserService userService;
    @Resource
    private TouchscreenService touchscreenService;
    @Resource
    HostgroupService hostgroupService;

    public String getHostIpStr() {
        return hostIpStr;
    }

    public void setHostIpStr(String hostIpStr) {
        this.hostIpStr = hostIpStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getTouchid() {
        return touchid;
    }

    public void setTouchid(int touchid) {
        this.touchid = touchid;
    }

    public int getTouchstype() {
        return touchstype;
    }

    public void setTouchstype(int touchstype) {
        this.touchstype = touchstype;
    }

    public int getTouchloop() {
        return touchloop;
    }

    public void setTouchloop(int touchloop) {
        this.touchloop = touchloop;
    }

    public String getTlooptype() {
        return tlooptype;
    }

    public void setTlooptype(String tlooptype) {
        this.tlooptype = tlooptype;
    }

    public int getTouchdate() {
        return touchdate;
    }

    public void setTouchdate(int touchdate) {
        this.touchdate = touchdate;
    }

    public int getTouchweek() {
        return touchweek;
    }

    public void setTouchweek(int touchweek) {
        this.touchweek = touchweek;
    }

    public String getTouchtime() {
        return touchtime;
    }

    public void setTouchtime(String touchtime) {
        this.touchtime = touchtime;
    }

    public String getTsingletime() {
        return tsingletime;
    }

    public void setTsingletime(String tsingletime) {
        this.tsingletime = tsingletime;
    }

    public String getHostStr() {
        return hostStr;
    }

    public void setHostStr(String hostStr) {
        this.hostStr = hostStr;
    }

    public String getHostNameStr() {
        return hostNameStr;
    }

    public void setHostNameStr(String hostNameStr) {
        this.hostNameStr = hostNameStr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTdevicetype() {
        return tdevicetype;
    }

    public void setTdevicetype(String tdevicetype) {
        this.tdevicetype = tdevicetype;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 返回定时信息列表
     */
    public String touchscreenList(){
        int pagesize=5;
        //获取当前设备的类型
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user= SessionManager.get(ServletActionContext.getRequest().getSession(),SessionManager.Key.USER);
        userId=user.getUserId();
        ActionContext.getContext().put("userId",userId);
        int userid= userService.getAuthorityValueByUserid(userId);
        this.currentPage=this.currentPage==0?1:this.currentPage;
        ActionContext.getContext().put("touchscreenList",touchscreenService.touchscreenService(currentPage,pagesize,userid,deviceType));
       //获取分组信息
        if(0==userid){
            final int uid=userid;
            Map<String,Object> result=super.getTreeMap(uid, deviceType, hostgroupService);
            if(result.isEmpty()){
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            }else{
                List<Map> groupTree=(List<Map>)result.get("groupTree");
                Map unknowGroup = (Map) result.get("unknowGroup");
                List<Map> hostList =(List<Map>) unknowGroup.get("host_list");
                if (!hostList.isEmpty()){
                    groupTree.add(0,unknowGroup);
                }
                ActionContext.getContext().put("groupTree",groupTree);
            }
        }else{
            ActionContext.getContext().put("groupTree",hostgroupService.getGroupService(userid,deviceType));
        }

        return TOUCHSCREEN_LIST;
    }
    /**
     * app添加定时信息及班级关系 *
     */
    public void addTouchScreen(){
        JSONObject json=new JSONObject();
        try {
        Touchscreen touchscreen=new Touchscreen();
        touchscreen.settLooptype(tlooptype);
        touchscreen.setTouchtype(touchstype);
        touchscreen.setTouchLoop(touchloop);
        touchscreen.setTouchDate(touchdate);
        touchscreen.setTouchWeek(touchweek);
        touchscreen.setTouchTime(touchtime);
        touchscreen.settDevicetype(tdevicetype);

        if (!"".equals(tsingletime)) {
            touchscreen.settSingletime(df.parse(tsingletime));
        }
        if (app_source()) {
            userid = String.valueOf(this.userId);
            userName = this.userName;
         } else{
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                userid = String.valueOf(user.getUserId());
                userName = user.getUserName();
           }
            touchscreen.setTouchUid(Integer.valueOf(userid));
            Date now = new Date();
            touchscreen.settCreatetime(df.parse(df.format(now)));
            touchscreen.setTouchUname(userName);
            boolean result = touchscreenService.addTouchScreenList(touchscreen, hostStr, hostNameStr,hostIpStr);
            if (result) {
                json.put("success", true);
                json.put("msg", "添加成功");
            } else {
                json.put("success", false);
                json.put("msg", result);
            }

            } catch (Exception e) {
                e.printStackTrace();
            json.put("success", false);
            json.put("msg", "添加失败");
            }

        writeJSON(json.toString());

    }
    /**
     * 修改定时信息及班级关系 *
     */
    public void  alterTouchScreen(){
        JSONObject json=new JSONObject();
        try {
            Touchscreen touchscreen=touchscreenService.getTouchListById(touchid);
            touchscreen.settLooptype(tlooptype);
            touchscreen.setTouchtype(touchstype);
            touchscreen.setTouchLoop(touchloop);
            touchscreen.setTouchDate(touchdate);
            touchscreen.setTouchWeek(touchweek);
            touchscreen.setTouchTime(touchtime);
            touchscreen.settDevicetype(tdevicetype);
                if (!"".equals(tsingletime)) {

                    touchscreen.settSingletime(df.parse(String.valueOf(tsingletime)));
                }
                if (app_source()) {
                    userid = String.valueOf(this.userId);
                    userName = this.userName;
                } else {
                    User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                    userid = String.valueOf(user.getUserId());
                    userName = user.getUserName();
                }
                    touchscreen.setTouchUid(Integer.valueOf(userid));
                    Date now = new Date();
                    touchscreen.settCreatetime(df.parse(df.format(now)));
                    touchscreen.setTouchUname(userName);
                    boolean result = touchscreenService.updateTouchList(touchscreen, hostStr, hostNameStr,hostIpStr);
                 if (result) {
                    json.put("success", true);
                    json.put("msg", "修改成功");
                } else {
                    json.put("success", false);
                    json.put("msg", result);
                }

            } catch (Exception e) {
                e.printStackTrace();
            json.put("success", false);
            json.put("msg", "修改失败");
            }
        writeJSON(json.toString());
    }
    /**
     * 删除定时信息及班级关系 *
     */
    public void delTouchScreen(){
        JSONObject json=new JSONObject();
        try{
            Touchscreen touchscreen=touchscreenService.getTouchListById(touchid);
            if (touchscreen==null){
                json.put("success",true);
                json.put("msg","删除成功");
            }else{
                if(hostStr==null){
                    hostStr="";
                }
               boolean result = touchscreenService.delTouchService(touchscreen,hostStr);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "删除成功");
                } else {
                    json.put("success", false);
                    json.put("msg", result);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }
}
