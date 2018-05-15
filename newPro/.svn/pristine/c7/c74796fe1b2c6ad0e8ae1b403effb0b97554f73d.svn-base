package com.honghe.recordweb.action.project.device;


import com.honghe.recordhibernate.dao.DTypeDao;
import com.honghe.recordhibernate.dao.Spec2commandDao;
import com.honghe.recordhibernate.dao.SpecDao;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.project.dtype.CommandService;
import com.honghe.recordweb.service.project.dtype.DTypeService;
import com.honghe.recordweb.service.project.dtype.SpecService;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/9/11.
 */
@Component
public class SpecAction extends BaseAction {
    @Resource
    private SpecDao specDao;
    @Resource
    private DTypeDao dTypeDao;
    @Resource
    private Spec2commandDao spec2commandDao;

    @Resource
    private SpecService specService;
    @Resource
    private DTypeService dTypeService;
    @Resource
    CommandService commandService;

    private List<Dspecification> speclist;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private int currentPage;

    public Dspecification getDspecification() {
        return dspecification;
    }

    public void setDspecification(Dspecification dspecification) {
        this.dspecification = dspecification;
    }

    private Dspecification dspecification;

    public List<Dspecification> getSpeclist() {
        return speclist;
    }

    public void setSpeclist(List<Dspecification> speclist) {
        this.speclist = speclist;
    }

    public List getDtypelist() {
        return dtypelist;
    }

    public void setDtypelist(List dtypelist) {
        this.dtypelist = dtypelist;
    }

    private List dtypelist;

    public List getCommandlist() {
        return commandlist;
    }

    public void setCommandlist(List commandlist) {
        this.commandlist = commandlist;
    }

    private List commandlist;

//    public String getCommandIds() {
//        return commandIds;
//    }
//    public void setCommandIds(String commandIds) {
//        this.commandIds = commandIds;
//    }
//    private String commandIds;
//
//    public String getParams() {
//        return params;
//    }
//    public void setParams(String params) {
//        this.params = params;
//    }
//    private String params;
//
//    public String getReturns() {
//        return returns;
//    }
//    public void setReturns(String returns) {
//        this.returns = returns;
//    }
//    private String returns;
//
//    public String getFlags() {
//        return flags;
//    }
//    public void setFlags(String flags) {
//        this.flags = flags;
//    }
//    private String flags;
//
//    public String getWords() {
//        return words;
//    }
//    public void setWords(String words) {
//        this.words = words;
//    }
//    private String words;

    public String specList() {
        String cpage = String.valueOf(this.currentPage);
        currentPage = 1;
        if (cpage != null) {
            currentPage = Integer.parseInt(cpage);
        }
        Integer pagesize = 13;
        speclistpage = specService.getSpecListBypage(currentPage, pagesize);
        //System.out.println(speclistpage);
        return "sepclist";
    }

    /**
     * 获取所有设备型号JSON
     */
    /*public void specJSON()
    {
        System.out.println("specJSON start");
        List<Dspecification> speclist = specDao.getSpecList();

        List datalist = new ArrayList();

        for (int i=0; i<speclist.size(); i++)
        {
            Dspecification spec = speclist.get(i);
            int id = spec.getDspecId();
            String name = spec.getDspecName();
            String param = spec.getConnectParam();
            Dtype dtype = spec.getDtype();

            Map specmap = new HashMap();
            specmap.put("spec_id", id);
            specmap.put("spec_name", name);
            specmap.put("spec_param",param);
            specmap.put("spec_type",dtype.getDtypeName());

            datalist.add(specmap);
        }

        JSONArray jsonArray = JSONArray.fromObject(datalist);
        String jsonstr = jsonArray.toString();

        System.out.println(jsonstr);
        writeJSON(jsonstr);

        //System.out.println("getSpecJSON!!!!!");
    }*/

    private String specname;
    private String connectparam;
    private String spectype;
    private String[] cmdId;
    private String[] cmdWord;

    public String[] getCmdParam() {
        return cmdParam;
    }

    public void setCmdParam(String[] cmdParam) {
        this.cmdParam = cmdParam;
    }

    public String[] getCmdFlag() {
        return cmdFlag;
    }

    public void setCmdFlag(String[] cmdFlag) {
        this.cmdFlag = cmdFlag;
    }

    public String[] getCmdReturn() {
        return cmdReturn;
    }

    public void setCmdReturn(String[] cmdReturn) {
        this.cmdReturn = cmdReturn;
    }

    public String[] getCmdWord() {
        return cmdWord;
    }

    public void setCmdWord(String[] cmdWord) {
        this.cmdWord = cmdWord;
    }

    public String[] getCmdId() {
        return cmdId;
    }

    public void setCmdId(String[] cmdId) {
        this.cmdId = cmdId;
    }

    private String[] cmdParam;
    private String[] cmdReturn;
    private String[] cmdFlag;

    public Map<String, Object> getSpeclistpage() {
        return speclistpage;
    }

    public void setSpeclistpage(Map<String, Object> speclistpage) {
        this.speclistpage = speclistpage;
    }

    private Map<String, Object> speclistpage;

    public void setSpecname(String name) {
        this.specname = name;
    }

    public String getSpecname() {
        return specname;
    }

    public void setConnectparam(String param) {
        connectparam = param;
    }

    public String getConnectparam() {
        return this.connectparam;
    }

    public String getSpectype() {
        return spectype;
    }

    public void setSpectype(String spectype) {
        this.spectype = spectype;
    }

    private String specId;

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    /**
     * 添加保存
     */
    public void add() {
        JSONObject json = new JSONObject();

        if (specname.trim().equals("")) {
            json.put("success", false);
            json.put("msg", "请填写型号名称");
        } else {
            List<Dspecification> rolelist = null;

            rolelist = specDao.getSpecByName(specname.trim());
            if (rolelist != null) {
                json.put("success", false);
                json.put("msg", "该型号名称已经存在");
            } else {
                //String[]cmdId,String[] cmdWord,String[] cmdParam,String[] cmdReturn,String[] cmdFlag
                if (specService.save(specname.trim(), connectparam, spectype, cmdId, cmdWord, cmdParam, cmdReturn, cmdFlag)) {
                    json.put("success", true);
                    json.put("msg", "添加成功！");
                } else {
                    json.put("success", false);
                    json.put("msg", "添加失败！");
                }
            }
        }

        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }

    public String addList() {
        commandlist = commandService.getCommandListService();
        return "specadd";
    }

    /**
     * 删除
     */
    public void delete() {
        Map<String, String> hashmap = new HashMap<String, String>();
        Integer specId = Integer.parseInt(this.specId);
        //System.out.println(specId);
        if (specService.delspec(specId)) {
            hashmap.put("msg", "删除成功");
        } else {
            hashmap.put("msg", "删除失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    public String update() {
        Integer specId = Integer.parseInt(this.specId);
        if (specId != -1) {
            dspecification = specService.getSpecById(specId);
        }
        dtypelist = dTypeService.getDTypeListService();
        commandlist = commandService.getCommandListService();
        return "specupdate";
    }

    /**
     * 编辑更新
     */
    public void edit() {
        int specId = Integer.parseInt(this.specId);
        Map<String, String> hashmap = new HashMap<String, String>();
        //List<Dspecification> rolelist = null;
        //rolelist = specDao.getSpecByName(specname);
        Dspecification dspecification = specService.getSpecById(specId);
        if (specname == null || specname.trim().equals("")) {
            hashmap.put("msg", "请填写型号名称");
        } else if (dspecification.getDspecName().equals(specname.trim())) {
            /*hashmap.put("success", false);*/
            hashmap.put("msg", "该型号名称已经存在");
        } else {
            if (specService.updateBySpecid(specId, specname.trim(), Integer.parseInt(spectype), connectparam, cmdId, cmdWord, cmdParam, cmdReturn, cmdFlag)) {
                hashmap.put("msg", "修改成功");
            } else {
                hashmap.put("msg", "修改失败");
            }
        }

        writeJSON(JSONSerializer.toJSON(hashmap).toString());
       /* HttpServletRequest request = ServletActionContext.getRequest(); //req的获得必须在具体的方法中实现

        String r = request.getParameter("idata");
        System.out.println(r);

        String rArray[] = r.split(",");

        JSONObject json = new JSONObject();

        if (rArray[1].equals(""))
        {
            json.put("success",false);
            json.put("msg","请填写完整！");
        }
        else
        {
                Dspecification specdata = new Dspecification();
                specdata.setDspecId(Integer.valueOf(rArray[0]).intValue());
                specdata.setDspecName(rArray[1]);
                specdata.setConnectParam(rArray[2]);
                Dtype dtype = dTypeDao.getTypeById(Integer.valueOf(rArray[3]));
                specdata.setDtype(dtype);

                if(specDao.updata(specdata))
                {
                    json.put("success",true);
                    json.put("msg","保存成功！");
                }
                else
                {
                    json.put("success",true);
                    json.put("msg","保存失败！");
                }
        }

        String jsonstr = json.toString();
        System.out.println(jsonstr);
        writeJSON(jsonstr);*/
    }
}
