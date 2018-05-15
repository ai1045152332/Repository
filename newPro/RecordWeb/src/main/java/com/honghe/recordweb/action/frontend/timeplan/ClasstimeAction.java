package com.honghe.recordweb.action.frontend.timeplan;

import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lch on 2014/9/28.
 */
@Controller
@Scope(value = "Prototype")
public class ClasstimeAction extends BaseAction {

    @Resource
    private ClasstimeService classtimeService;

    private List classtimelist;

    private String[] starttime;

    private String[] starttime_1;

    private Integer res ;//记录保存是否成功,在jsp页面是否弹出保存成功,0为失败,1为成功

    private String[] endtime;

    private String[] endtime_1;

    private String[] classtimeId;

    private String[] weekId;

    public Map<Integer, String> getIntToUpper() {
        return intToUpper;
    }

    public void setIntToUpper(Map<Integer, String> intToUpper) {
        this.intToUpper = intToUpper;
    }

    private Map<Integer,String> intToUpper;

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public String[] getEndtime() {
        return endtime;
    }

    public void setEndtime(String[] endtime) {
        this.endtime = endtime;
    }

    public String[] getStarttime() {
        return starttime;
    }

    public void setStarttime(String[] starttime) {
        this.starttime = starttime;
    }

    public List getClasstimelist() {
        return classtimelist;
    }

    public void setClasstimelist(List classtimelist) {
        this.classtimelist = classtimelist;
    }

    public String[] getClasstimeId() {
        return classtimeId;
    }

    public void setClasstimeId(String[] classtimeId) {
        this.classtimeId = classtimeId;
    }

    public String[] getStarttime_1() {
        return starttime_1;
    }

    public void setStarttime_1(String[] starttime_1) {
        this.starttime_1 = starttime_1;
    }

    public String[] getEndtime_1() {
        return endtime_1;
    }

    public void setEndtime_1(String[] endtime_1) {
        this.endtime_1 = endtime_1;
    }

    public String[] getWeekId() {
        return weekId;
    }

    public void setWeekId(String[] weekId) {
        this.weekId = weekId;
    }

    /**
     * 上课时间列表
     * @return
     */
    public String classtime()
    {
        this.intToUpper = classtimeService.intToUpper();
        classtimelist = classtimeService.classtimeList();
        return "classtimemanager";
    }

    /**
     * todo 加注释
     * @return
     */
    public String classtimeadd()
    {
        this.intToUpper = classtimeService.intToUpper();
        classtimelist = classtimeService.classtimeList();
        return "classtimeadd";
    }

    /**
     * 上课时间列表
     */
    public void classtimeJSON()
    {
        List result = classtimeService.classtimeList();
        JSONArray jsonArray = JSONArray.fromObject(result);
        String jsonstr = jsonArray.toString();
        writeJSON(jsonstr);
    }

    /**
     * 设置上课时间
     */
    public void add()
    {
        Map<String,String> hashmap = new HashMap<String, String>();
        if(classtimeService.addClasstime(weekId,starttime,starttime_1,endtime,endtime_1))
        {
            //this.res = 1;//添加成功
            hashmap.put("success", "ok");
            hashmap.put("msg", "添加成功");
        }
        else
        {
            hashmap.put("msg", "添加失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 修改上课时间
     */
    public void update()
    {
        Map<String,String> hashmap = new HashMap<String, String>();
       // Classtime info = classtimeService.getInfo(classtimeId);
        int flag = classtimeService.updateClasstime(classtimeId,weekId,starttime,starttime_1,endtime,endtime_1);
        if( flag== 0)
        {
            hashmap.put("msg", "修改成功");
        }
        else if(flag == 1)
        {
            hashmap.put("msg", "有录像计划的上课时间不能修改");
        }
        else if(flag == 2)
        {
            hashmap.put("msg","修改成功");//清空上课时间
        }
        else
        {
            hashmap.put("msg", "修改失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 删除上课时间
     */
    public void delete()
    {
        Map<String,String> hashmap = new HashMap<String, String>();
        Integer classtimeId = Integer.parseInt(ServletActionContext.getRequest().getParameter("classtimeId"));
        if(classtimeService.delete(classtimeId))
        {
            hashmap.put("msg", "删除成功");
        }
        else
        {
            hashmap.put("msg", "删除失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }
}
