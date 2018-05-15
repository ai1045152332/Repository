package com.honghe.recordweb.action.project.device;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Dtype;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.service.project.dtype.DTypeService;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


/**
 * Created by Moon on 2014/9/17.
 */
@Controller
@Scope(value = "prototype")
public class DTypeAction extends BaseAction {
    @Resource
    private DTypeService dTypeService;

    private String typeName;

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(String currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    private String typeDesc;
    private String typeId;
    private String currentPageSize;


    public String findDTypeList() {
        Page<Dtype> page = dTypeService.getDTypeList(Integer.parseInt(currentPageSize));
        ActionContext.getContext().put("dTypeList", page);
        return "dtype";
    }

    public void saveDType() {
        Dtype dtype = new Dtype(this.typeName, this.typeDesc);
        boolean flag = dTypeService.save(dtype);
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("success", flag);
            json.put("msg", "设备类型增加成功");
        } else {
            json.put("success", flag);
            json.put("msg", "设备类型增加失败");
        }
        this.writeJSON(json.toString());
    }


    public void deleteDType() {
        boolean flag = dTypeService.delete(Integer.parseInt(this.typeId));
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("success", flag);
            json.put("msg", "设备类型删除成功");
        } else {
            json.put("success", flag);
            json.put("msg", "设备类型删除失败");
        }
        this.writeJSON(json.toString());
    }

    public void findDtype() {
        boolean flag = dTypeService.isDType(typeName);
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("success", flag);
            json.put("msg", "设备类型已存在");
        } else {
            json.put("success", flag);
            json.put("msg", "");
        }
        this.writeJSON(json.toString());
    }


    public void hasDtype(){
        boolean flag = dTypeService.hasDtype(Integer.parseInt(this.typeId));
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("success", flag);
            json.put("msg", "设备类型已经在使用");
        } else {
            json.put("success", flag);
            json.put("msg", "");
        }
        this.writeJSON(json.toString());
    }


    public void updateDtype() {
        Dtype dtype = new Dtype();
        dtype.setDtypeId(Integer.parseInt(this.typeId));
        dtype.setDtypeName(this.typeName);
        dtype.setDtypeDesc(this.typeDesc);
        boolean flag = dTypeService.update(dtype);
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("success", flag);
            json.put("msg", "设备类型修改成功");
        } else {
            json.put("success", flag);
            json.put("msg", "设备类型删除成功");
        }
        this.writeJSON(json.toString());
    }


}
