package com.honghe.recordweb.action.frontend.programme;

import com.honghe.recordhibernate.entity.Programme;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.programme.ProgrammeService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lyx on 2015-08-22.
 */
@Controller
@Scope(value = "prototype")
public class ProgrammeAction extends BaseAction implements ModelDriven<Programme> {
    private final static Logger logger = Logger.getLogger(ProgrammeAction.class);
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private final String GROUPTREE = "groupTree";
    private final String UNKNOWGROUP = "unknowGroup";
    private final String HOST_LIST = "host_list";
    protected Programme pro;
    private String hostStr;
    private String hostIpStr;
    private String hostNameStr;
    private int pageCount;
    private int currentPage;
    private int userId;
    private String userName; //用户名

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

    public int getUserId() {
        return userId;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }

    public String getHostStr() { return hostStr; }

    public void setHostStr(String hostStr) { this.hostStr = hostStr; }

    public String getHostNameStr() { return hostNameStr; }

    public void setHostNameStr(String hostNameStr) { this.hostNameStr = hostNameStr; }

    public int getPageCount() { return pageCount; }

    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public int getCurrentPage() { return currentPage; }

    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    @Resource
    private ProgrammeService programmeService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private UserService userService;


    @Override
    public Programme getModel() {
        if (pro == null) {
            pro = new Programme();
        }
        return pro;
    }

    /**
     * 返回定时切换信号信息列表
     *
     * @return
     */
    public String programmeList() {

        int pagesize = 5;

        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId();

        ActionContext.getContext().put("userId", userId);

        int userid = userService.getAuthorityValueByUserid(userId);

        this.currentPage = this.currentPage == 0 ? 1 : this.currentPage;

        ActionContext.getContext().put("programmeList", programmeService.programmeListService(currentPage, pagesize, userid));

        //获取分组信息
        if (0 == userid) {
            final int uid = userid;
            Map<String, Object> result = super.getTreeMap(uid, CommonName.DEVICE_TYPE_SCREEN, hostgroupService);
            if (result.isEmpty()) {
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            } else {
                List<Map> groupTree = (List<Map>) result.get("groupTree");
                Map unknowGroup = (Map) result.get("unknowGroup");
                List<Map> hostList = (List<Map>) unknowGroup.get("host_list");
                if (!hostList.isEmpty()) {
                    groupTree.add(0, unknowGroup);
                }
                ActionContext.getContext().put("groupTree", groupTree);
            }
        }  else {
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(userid, CommonName.DEVICE_TYPE_SCREEN));
        }
        return "programmeList";
    }


    /**
     * 删除定时信息及班级关系 *
     */
    public void delProgramme() {
        JSONObject json = new JSONObject();
        try {
            Programme programme = programmeService.getProgrammeService(pro.getProId());

            if (programme == null) {
                json.put("success", true);
                json.put("msg", "删除成功");
            } else {
                if (hostStr == null) {
                    hostStr = "";
                }
                boolean result = programmeService.delProgrammeService(programme, hostStr);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "删除成功");
                } else {
                    json.put("success", false);
                    json.put("msg", result);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 添加定时信息及班级关系 *
     */
    public void addProgramme() {
        JSONObject json = new JSONObject();
        try {
            int uid = 0;
            String uname = "";

            if (app_source()) {
                uid = this.userId;
                uname = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                uid = user.getUserId();
                uname = user.getUserName();
            }

            pro.setProUid(uid);
            pro.setProUname(uname);
            pro.setProCreatetime(df.parse(df.format(new Date())));

            boolean result = programmeService.addProgrammeService(pro, hostStr, hostNameStr,hostIpStr);

            if (result) {
                json.put("success", true);
                json.put("msg", "添加成功");
            } else {
                json.put("success", false);
                json.put("msg", result);
            }
        } catch (Exception e) {
            logger.error("", e);
            //   e.printStackTrace();
            json.put("success", false);
            json.put("msg", "添加失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 修改定时信息及班级关系 *
     */
    public void alterProgramme() {
        JSONObject json = new JSONObject();
        try {

            int uid = 0;
            String uname = "";

            if (app_source()) {
                uid = this.userId;
                uname = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                uid = user.getUserId();
                uname = user.getUserName();
            }

            pro.setProUid(uid);
            pro.setProUname(uname);
            pro.setProCreatetime(df.parse(df.format(new Date())));

            boolean result = programmeService.updateProgrammeService(pro, hostStr, hostNameStr,hostIpStr);

            if (result) {
                json.put("success", true);
                json.put("msg", "修改成功");
            } else {
                json.put("success", false);
                json.put("msg", "修改失败");
            }
        } catch (Exception e) {
            logger.error("", e);
            //  e.printStackTrace();
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        writeJSON(json.toString());
    }

}
