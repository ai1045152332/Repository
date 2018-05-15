package com.honghe.recordweb.action.frontend.devicecode;

import com.honghe.recordhibernate.entity.CommandCode;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.frontend.devicecode.CmdCodeService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by sky on 2015/8/22.
 */
public class DevicecodeAction extends BaseAction {

    private int codeId;
    private String codeName;
    private String codeType;
    private String codeResult;
    private String codeRemark;
    private String codeInstruction;
    private String codeCode;
    private String codeFlag;
    private String codeGroup;
    private Dspecification dspecification;
    private String dspecName;
    private Integer dspecId;
    private String cmdName;
    private Map<String, Object> codeList;
    private Map<String, Object> cmdNameMap;
    private Map<String,Object> dpecNameMap;
    private List cmdNameList;

    public List getCmdNameList() {
        return cmdNameList;
    }

    public void setCmdNameList(List cmdNameList) {
        this.cmdNameList = cmdNameList;
    }

    public Map<String, Object> getDpecNameMap() {
        return dpecNameMap;
    }

    public void setDpecNameMap(Map<String, Object> dpecNameMap) {
        this.dpecNameMap = dpecNameMap;
    }

    private int currentPage;
    private CommandCode codeinfo;

    public CommandCode getCodeinfo() {
        return codeinfo;
    }

    public void setCodeinfo(CommandCode codeinfo) {
        this.codeinfo = codeinfo;
    }

    @Resource
    private CmdCodeService cmdcodeService;

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeResult() {
        return codeResult;
    }

    public void setCodeResult(String codeResult) {
        this.codeResult = codeResult;
    }

    public String getCodeRemark() {
        return codeRemark;
    }

    public void setCodeRemark(String codeRemark) {
        this.codeRemark = codeRemark;
    }

    public String getCodeInstruction() {
        return codeInstruction;
    }

    public void setCodeInstruction(String codeInstruction) {
        this.codeInstruction = codeInstruction;
    }
    public String getCodeCode() {
        return codeCode;
    }

    public void setCodeCode(String codeCode) {
        this.codeCode = codeCode;
    }
    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCodeFlag() {
        return codeFlag;
    }

    public void setCodeFlag(String codeFlag) {
        this.codeFlag = codeFlag;
    }

    public Dspecification getDspecification() {
        return dspecification;
    }

    public void setDspecification(Dspecification dspecification) {
        this.dspecification = dspecification;
    }

    public CmdCodeService getCmdcodeService() {
        return cmdcodeService;
    }

    public void setCmdcodeService(CmdCodeService cmdcodeService) {
        this.cmdcodeService = cmdcodeService;
    }

    public String getDspecName() {
        return dspecName;
    }

    public void setDspecName(String dspecName) {
        this.dspecName = dspecName;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public Integer getDspecId() {
        return dspecId;
    }

    public void setDspecId(Integer dspecId) {
        this.dspecId = dspecId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, Object> getCodeList() {
        return codeList;
    }

    public void setCodeList(Map<String, Object> codeList) {
        this.codeList = codeList;
    }

    public Map<String, Object> getCmdNameMap() {
        return cmdNameMap;
    }

    public void setCmdNameMap(Map<String, Object> cmdNameMap) {
        this.cmdNameMap = cmdNameMap;
    }

    /**
     * 获取命令
     * @return
     */
    public String codeList() {

        //String cpage = ServletActionContext.getRequest().getParameter("currentPage");
        //currentPage = 1;
        if (currentPage == 0) {
            currentPage = 1;
        }
        Integer pagesize = 10;
        codeList = cmdcodeService.getDcodeList(currentPage, pagesize);
        cmdNameMap=cmdcodeService.getCmdNameList();
        dpecNameMap=cmdcodeService.getDspeName();
        return "codelist";
    }

    /**
     * 获取命令
     * @return
     */
    public String ccodelist(){
    if (currentPage == 0) {
        currentPage = 1;
    }
    Integer pagesize = 10;
    codeList = cmdcodeService.getDcodeList(currentPage, pagesize);
    cmdNameMap=cmdcodeService.getCmdNameList();
    dpecNameMap=cmdcodeService.getDspeName();
    return "ccodelist";
}

    /**
     * 命令详细信息
     * @return
     */
    public String codeDetail() {
        cmdNameMap=cmdcodeService.getCmdNameList();
        dpecNameMap=cmdcodeService.getDspeName();
        if (codeId != -1) {
            codeinfo = cmdcodeService.getCodeInfoById(codeId);
        }
        return "codeadd";
    }

    /**
     * 根据设备名称来获取命令名称列表
     */
    public void getcmdName(){
        JSONObject json=new JSONObject();
        cmdNameList=cmdcodeService.getCmdnameBydspec(dspecName);
        json.put("cmdnamelist",cmdNameList);
        writeJson(json);
    }

    /**
     * 根据命令编码查找
     * @return
     */
    public String codeSearch()
    {
        dpecNameMap=cmdcodeService.getDspeName();
        cmdNameMap=cmdcodeService.getCmdNameList();

        if(currentPage==0)
        {
            currentPage=1;
        }
        Integer pagesize = 10;
        try {
            cmdName = URLDecoder.decode(cmdName, "UTF-8");
           dspecName=URLDecoder.decode(dspecName,"UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        codeList =  cmdcodeService.getCodeByName(dspecName,cmdName,currentPage, pagesize);
        return "codesearch";
    }

    /**
     * 更新
     */
    public void edit() {
        JSONObject json = new JSONObject();
            boolean result = cmdcodeService.codeUpdate(codeId, codeName, codeType,  codeRemark, codeInstruction,codeCode, codeFlag, codeGroup,dspecName,dspecId);
            if (result) {
                json.put("success", true);
                json.put("msg", "保存成功！");
            } else {
                json.put("success", false);
                json.put("msg", "保存失败！");
            }

        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }

    /**
     * 删除数据
     * @return
     */
    public String delete() {
       JSONObject json=new JSONObject();
        Integer codeId = Integer.parseInt(ServletActionContext.getRequest().getParameter("codeId"));
        if (cmdcodeService.delcode(codeId)) {
            json.put("success", true);
            json.put("msg", "删除成功！");
        } else {
            json.put("success", false);
            json.put("msg", "删除失败！");
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
        return "delsuccess";
    }

    /**
     * 添加
     */
    public void add() {
        JSONObject json = new JSONObject();
        if (codeName.equals("")  ||  codeRemark.equals("")) {
            json.put("success", false);
            json.put("msg", "请填写完整！");
        } else {
           boolean result_add=cmdcodeService.codeAdd(codeName, codeType, codeRemark, codeInstruction,codeCode, codeFlag, codeGroup,dspecName,dspecId);
            if(result_add)
            {
            json.put("success", true);
            json.put("msg", "添加成功！");
            }
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }
}