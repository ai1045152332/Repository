package com.honghe.recordweb.action.frontend.tempplan;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.Temporaryplan;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.tempplan.TemporaryplanService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by honghe on 2016/9/5.
 */
@Controller
@Scope(value="prototype")
public class TemporaryplanAction extends BaseAction{
    private final static Logger logger = Logger.getLogger(TemporaryplanAction.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    @Resource
    private TemporaryplanService temporaryplanService;
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private SettingsService settingsService;
    @Resource
    private HostgroupService hostgroupService;
    private Integer tempId;
    private List<Temporaryplan> tempList;
    private Page<Temporaryplan> page;

    public Integer getTempId() {
        return tempId;
    }

    public void setTempId(Integer tempId) {
        this.tempId = tempId;
    }

    public List<Temporaryplan> getTempList() {
        return tempList;
    }

    public void setTempList(List<Temporaryplan> tempList) {
        this.tempList = tempList;
    }

    /**
     * 查询临时计划列表
     * @return
     */
    public String list(){
        try{
            page = new Page<Temporaryplan>(0);
            temporaryplanService.tempplanList(page);
            tempList = page.getResult();
        }catch(Exception e){
            logger.error("查询临时计划列表异常",e);
        }
        return "tempplanlist";
    }

    /**
     * 新增临时计划
     * Author xinye
     */
    public void addTemp(){
        JSONObject jsonResult = new JSONObject();
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            String plan_name = request.getParameter("plan_name");
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String hostIds = request.getParameter("hostIds");
            String[] hostId_array = hostIds.split(";");
            Temporaryplan temporaryplan = new Temporaryplan();
            temporaryplan.setName(plan_name);
            temporaryplan.setTimeEnd(end_time);
            temporaryplan.setTimeStart(start_time);
            temporaryplanService.addPlan(temporaryplan);
            for(String hostId:hostId_array){
                boolean isOK = temporaryplanService.addRelation(temporaryplan.getTemporaryplanId(),Integer.parseInt(hostId));
            }
            setTempPlan(hostId_array);
            jsonResult.put("msg","新增临时计划成功");
            jsonResult.put("temp_plan",JSONObject.fromObject(temporaryplan));
        }catch(Exception e){
            logger.error("",e);
            e.printStackTrace();
            jsonResult.put("msg","新增临时计划失败");
        }
        this.writeJSON(jsonResult.toString());
    }

    /**
     * 修改临时计划
     * Author xinye
     */
    public void modifyTemp(){
        JSONObject jsonResult = new JSONObject();
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            String tempId = request.getParameter("tempId");
            final int temp_id = Integer.parseInt(tempId);
            String plan_name = request.getParameter("plan_name");
            String start_time = request.getParameter("start_time");
            String end_time = request.getParameter("end_time");
            String hostIds = request.getParameter("hostIds");
            String[] hostId_array = hostIds.split(";");
            final List<Integer> oldlist = temporaryplanService.getHostListInTemp(temp_id);//修改前关联的主机列表
            //开始 先清除原数据在设备上的计划
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i =0 ,j=oldlist.size();i<j;i++){
                        Map<String,Object> host_map = timeplanService.getHost(oldlist.get(i));
                        nvrCommand.delTempPlanCommand(oldlist.get(i),temp_id,host_map.get("host_ipaddr").toString());
                    }
                }
            });
            //结束
            Temporaryplan temporaryplan = new Temporaryplan();
            temporaryplan.setTemporaryplanId(temp_id);
            temporaryplan.setName(plan_name);
            temporaryplan.setTimeEnd(end_time);
            temporaryplan.setTimeStart(start_time);
            temporaryplanService.updatePlan(temporaryplan);
            for(String hostId:hostId_array){
                boolean isOK = temporaryplanService.addRelation(temporaryplan.getTemporaryplanId(),Integer.parseInt(hostId));
            }
            setTempPlan(hostId_array);
            jsonResult.put("msg","修改临时计划成功");
            jsonResult.put("temp_plan",JSONObject.fromObject(temporaryplan));
        }catch(Exception e){
            logger.error("",e);
            e.printStackTrace();
            jsonResult.put("msg","修改临时计划失败");
        }
        this.writeJSON(jsonResult.toString());
    }

    /**
     * 返回封装好的主机列表json字符串
     * Author xinye
     */
    public void host_list(){
        JSONObject jsonResult = new JSONObject();
        JSONObject jsonData = new JSONObject();
        try{
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;
            }
            List<Object[]> list = temporaryplanService.hostlist(uid);
            for(Object[] obj:list){
                obj[2]=settingsService.hasSettingNas(Integer.parseInt(obj[0].toString()))||!obj[3].equals("5");
            }
            JSONArray jsonArray = JSONArray.fromObject(list);
            jsonResult.put("status",0);
            jsonData.put("hostList",jsonArray);
            jsonResult.put("data",jsonData);

        }catch(Exception e){
            logger.error("",e);
            jsonResult.put("status",1);
        }
        this.writeJSON(jsonResult.toString());
    }

    /**
     * 查看指定临时计划的详情
     * Author xinye
     */
    public void showTempInfo(){
        JSONObject jsonResult = new JSONObject();
        JSONObject jsonData = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            Integer tempId = Integer.parseInt(request.getParameter("tempId"));
            List<Object[]> list = temporaryplanService.getGroupNameAndHostNameByTempId(tempId);
            for(int i = 0,j=list.size();i<j;i++){
                Object[] object = list.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("group_name",object[1]);
                int hostId = Integer.parseInt(object[0].toString());

                map.put("host_id",hostId);
                Map<String,Object> host_map =timeplanService.getHost(hostId);
                map.put("host_name",host_map.get("host_name"));
                jsonArray.add(map);
            }
            jsonResult.put("status","0");
            jsonData.put("hosts",jsonArray);
            jsonResult.put("data",jsonData);
        }catch(Exception e){
            logger.error("",e);
            jsonResult.put("status","1");
            jsonResult.put("msg","查询失败");
            this.writeJSON(jsonResult.toString());
        }
        this.writeJSON(jsonResult.toString());
    }

    /**
     * 删除临时计划
     * Author xinye
     */
    public void delTemp_plan(){
        JSONObject jsonResult = new JSONObject();
        try{
            HttpServletRequest request = ServletActionContext.getRequest();
            String tempId = request.getParameter("tempId");
            boolean isOK = temporaryplanService.deletePlan(Integer.parseInt(tempId));
            if(isOK){
                jsonResult.put("status","0");
                jsonResult.put("msg","删除临时计划成功");
            }else{
                jsonResult.put("status","1");
                jsonResult.put("msg","删除临时计划失败");
            }
        }catch(Exception e){
            logger.error("",e);
            jsonResult.put("status","1");
            jsonResult.put("msg","删除临时计划失败");
            this.writeJSON(jsonResult.toString());
        }
        this.writeJSON(jsonResult.toString());
    }
    private void setTempPlan(final String[] hostId_array){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                temporaryplanService.setTempPlan(hostId_array);
            }
        });
    }

}
