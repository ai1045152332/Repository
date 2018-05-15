package com.honghe.recordweb.action.project.device;

import com.honghe.recordhibernate.dao.CommandDao;
import com.honghe.recordhibernate.entity.Command;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.project.dtype.CommandService;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/9/19.
 */
public class DevicecommAction extends BaseAction {
    @Resource
    private CommandDao commandDao;
    @Resource
    private CommandService commandService;

    private int currentPage;
    private Map<String, Object> commandList;
    private Command comminfo;
    private int cmdId;
    private String cmdName;
    private String cmdGroup;
    private String cmdHex;
    private int cmdDefault;
    private String cmdFlag;
    private String cmdImage;

    public String getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(String cmdFlag) {
        this.cmdFlag = cmdFlag;
    }

    public String getCmdImage() {
        return cmdImage;
    }

    public void setCmdImage(String cmdImage) {
        this.cmdImage = cmdImage;
    }

    public int getCmdDefault() {
        return cmdDefault;
    }

    public void setCmdDefault(int cmdDefault) {
        this.cmdDefault = cmdDefault;
    }

    public String getCmdHex() {
        return cmdHex;
    }

    public void setCmdHex(String cmdHex) {
        this.cmdHex = cmdHex;
    }

    public String getCmdGroup() {
        return cmdGroup;
    }

    public void setCmdGroup(String cmdGroup) {
        this.cmdGroup = cmdGroup;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public int getCmdId() {
        return cmdId;
    }

    public void setCmdId(int cmdId) {
        this.cmdId = cmdId;
    }

    public Map<String, Object> getCommandList() {
        return commandList;
    }

    public void setCommandList(Map<String, Object> commandList) {
        this.commandList = commandList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Command getComminfo() {
        return comminfo;
    }

    public void setComminfo(Command comminfo) {
        this.comminfo = comminfo;
    }


    private String commandId; //命令id

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    //命令列表
    public String commList() {
        String cpage = String.valueOf(this.currentPage);
        currentPage = 1;
        if (cpage != null) {
            currentPage = Integer.parseInt(cpage);
        }
        Integer pagesize = 14;
        commandList = commandService.getDcommList(currentPage, pagesize);

        return "commandlist";
    }

    //命令详细信息
    public String commdetail() {
        if (cmdId != -1) {
            comminfo = commandService.getCommInfoById(cmdId);
        }
        return "commadd";
    }

    //添加命令信息
    public void add() {
        JSONObject json = new JSONObject();

        if (cmdName.equals("") || cmdGroup.equals("") || cmdHex.equals("")) {
            json.put("success", false);
            json.put("msg", "请填写完整！");
        } else {
            List<Command> commlist = null;
            commlist = commandDao.getDeviceCommByName(cmdName);
            if (commlist != null) {
                json.put("success", false);
                json.put("msg", "命令名称重复！");
            }
            commlist = commandDao.getDeviceCommByHex(cmdHex);
            if (commlist != null) {
                json.put("success", false);
                json.put("msg", "命令值重复！");
            } else {
                commandService.cmdAdd(cmdName, cmdGroup, cmdHex, cmdDefault, cmdFlag, cmdImage);
                json.put("success", true);
                json.put("msg", "添加成功！");
            }
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }

    //修改命令信息
    public void edit() {
        JSONObject json = new JSONObject();

        if (cmdName == null || cmdGroup == null || cmdHex == null || cmdName.equals("") || cmdGroup.equals("") || cmdHex.equals("")) {
            json.put("success", false);
            json.put("msg", "请填写完整！");
        } else {
            if (commandService.cmdUpdate(cmdId, cmdName, cmdGroup, cmdHex, cmdDefault, cmdFlag, cmdImage)) {
                json.put("success", true);
                json.put("msg", "保存成功！");
            } else {
                json.put("success", false);
                json.put("msg", "保存失败！");
            }
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }

    //删除命令
    public void delete() {
        Map<String, String> hashmap = new HashMap<String, String>();
        Integer commandId = Integer.parseInt(this.commandId);
        if (commandService.delcommand(commandId)) {
            hashmap.put("msg", "删除成功");
        } else {
            hashmap.put("msg", "删除失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }
}
