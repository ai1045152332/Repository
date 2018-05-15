package com.honghe.recordweb.action.frontend.install;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.install.InstallService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzz on 2015/8/10.
 */
@Controller
@Scope(value = "prototype")
public class InstallAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(InstallAction.class);
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private InstallService installService;
    @Resource
    private UserService userService;
    private String filePath;
    private String fileName;
    private String hostIps;
    private File myfile;
    private String myfileName;
    private String deleteName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String gethostIps() {
        return hostIps;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHostIps() {
        return hostIps;
    }

    public void setHostIps(String hostIps) {
        this.hostIps = hostIps;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }

    public String getMyfileName() {
        return myfileName;
    }

    public void setMyfileName(String myfileName) {
        this.myfileName = myfileName;
    }

    public File getMyfile() {
        return myfile;
    }

    public void setMyfile(File myfile) {
        this.myfile = myfile;
    }


    /**
     * 返回软件安装列表 *
     */
    public String installList() {
        HashMap<String, Integer> roleMap = new HashMap<String, Integer>(0);
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        userId = userService.getAuthorityValueByUserid(userId);
        //获取分组信息
        if (userId == 0) {
            final int uid = userId;
            Map<String, Object> result = super.getTreeMap(uid, deviceType, hostgroupService);
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
        }
      /* else if(userId.equals("1") || userId.equals("2")  || userId.equals("3") || userId.equals("4") ) {
            Collection<Callable<Map<String, Object>>> thread = new ArrayList();
            thread.add(new Callable<Map<String, Object>>() {
                @Override
                public Map<String, Object> call() throws Exception {
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("groupTree", hostgroupService.getGroupService(0, CommonName.DEVICE_TYPE_SCREEN));
                    return result;
                }
            });
            thread.add(new Callable<Map<String, Object>>() {
                @Override
                public Map<String, Object> call() throws Exception {
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("unknowGroup", hostgroupService.getUnknowGroup(CommonName.DEVICE_TYPE_SCREEN));
                    return result;
                }
            });
            Map<String, Object> result = ThreadUtil.invokeAll(2, thread);
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
        } */
        else {
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(userId, deviceType));
        }
        if (filePath == null) {
            filePath = "";
        } else {
            try {
                filePath = URLDecoder.decode(filePath);
            } catch (Exception e) {
                filePath = "";
            }
        }
        ActionContext.getContext().put("installMap", installService.readFile(filePath));
        return "installList";
    }


//    /**
//     *  根据路径删除指定的目录或文件，无论存在与否
//     *@return 删除成功返回 true，否则返回 false。
//     */
//    public void deleteFolder() {
//        JSONObject json = new JSONObject();
//        try {
////            String path = java.net.URLDecoder.decode(filePath,"UTF-8");
//            String path = ServletActionContext.getServletContext().getRealPath("/upload") + URIEncoderDecoder.decode(filePath);
//            File file = new File(path);
//            // 判断目录或文件是否存在
//            if (!file.exists()) {  //
//                json.put("success", false);
//                json.put("msg", "文件不存在");
//            } else {
//                // 判断是否为文件
//                if (file.isFile()) {  // 为文件时调用删除文件方法
//                    if(installService.deleteFile(path))
//                    {
//                        json.put("success", true);
//                        json.put("msg", "删除成功");
//                    }
//                    else
//                    {
//                        json.put("success", false);
//                        json.put("msg", "删除失败或文件不存在");
//                    }
//                } else {  // 为目录时调用删除目录方法
//                    if(installService.deleteDirectory(path))
//                    {
//                        json.put("success", true);
//                        json.put("msg", "删除成功");
//                    }
//                    else
//                    {
//                        json.put("success", false);
//                        json.put("msg", "删除失败或文件不存在");
//                    }
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            json.put("success", false);
//            json.put("msg", "删除失败");
//        }
//
//        writeJSON(json.toString());
//    }

    /**
     * 一键安装 *
     */
    public void oneKeyInstall() {
        JSONObject json = new JSONObject();
        try {
            fileName = URLDecoder.decode(fileName);

            filePath = URLDecoder.decode(filePath);
            String serverIp = hostgroupService.getIp();
            String port = "21";
            String[] ip_array = hostIps.split(":");
            int ipcount = ip_array.length;
            int res = installService.oneKeyInstallService(hostIps, fileName, filePath, serverIp, port);
            if (res == 0) {
                json.put("success", true);
                json.put("msg", "一键安装命令下发成功");
            } else if (res == -1) {
                json.put("success", false);
                json.put("falsecount", 0);
                json.put("msg", "一键安装命令下发失败");
            } else {
                json.put("success", false);
                json.put("falsecount", res);
                json.put("msg", res + "个设备一键安装命令下发失败," + (ipcount - res) + "个成功");
            }
        } catch (Exception e) {
            logger.error("一键安装失败：", e);
            json.put("success", false);
            json.put("msg", "一键安装命令下发失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 一键卸载 *
     */
    public void oneKeyuninstall() {
        JSONObject json = new JSONObject();
        try {
            fileName = URLDecoder.decode(fileName);
            String[] ip_array = hostIps.split(":");
            int ipcount = ip_array.length;
            int res = installService.oneKeyuninstallService(hostIps, fileName);
            if (res == 0) {
                json.put("success", true);
                json.put("msg", "一键卸载命令下发成功");
            } else if (res == -1) {
                json.put("success", false);
                json.put("falsecount", 0);
                json.put("msg", "一键卸载命令下发失败");
            } else {
                json.put("success", false);
                json.put("falsecount", res);
                json.put("msg", res + "个设备一键卸载命令下发失败," + (ipcount - res) + "个成功");
            }
        } catch (Exception e) {
            logger.error("一键卸载失败：", e);
            json.put("success", false);
            json.put("msg", "一键卸载命令下发失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 上传文件
     * ajax方法
     */
    public void uploadFile() {
        JSONObject json = new JSONObject();
        String path = ServletActionContext.getServletContext().getRealPath("/uploadsoft");
        try {
            installService.uploadFile(myfile, myfileName, path);
            json.put("success", true);
            json.put("message", "上传成功");
        } catch (Exception e) {
            json.put("success", false);
            json.put("message", "上传失败");
        }
        writeJson(json);
    }

    /**
     * 删除文件
     * ajax方法
     */
    public void deleteFile() {
        JSONObject json = new JSONObject();
        String path = ServletActionContext.getServletContext().getRealPath("/uploadsoft");
        try {
            installService.deleteFile(deleteName, path);
            json.put("success", true);
            json.put("message", "删除成功");
        } catch (Exception e) {
            json.put("success", false);
            json.put("message", "删除失败");
        }
        writeJson(json);
    }
}
