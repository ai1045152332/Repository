package com.honghe.recordweb.action.frontend.news;

import com.alibaba.fastjson.JSON;
import com.honghe.recordhibernate.dao.TResourceDao;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordhibernate.entity.form.JsonForm;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/18
 */
@Controller
@Scope(value = "prototype")
public class ResourceFileAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(ResourceFileAction.class);
    @Resource
    private TResourceDao tResourceDao;
    private String multi;
    private String op;
    private String prid;
    private String virtual;
    private String type;
    private String srhText;

    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrhText() {
        return srhText;
    }

    public void setSrhText(String srhText) {
        this.srhText = srhText;
    }

    public String getMulti() {
        return multi;
    }

    public void setMulti(String multi) {
        this.multi = multi;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    /**
     * todo 加注释
     * @return
     */
    public String homeIndex() {
        HttpServletRequest request = ServletActionContext.getRequest();
//        String type = request.getParameter("type");
//        String multi = request.getParameter("multi");
//        String op = request.getParameter("op"); // 在线视频地址回显
//        String prid = request.getParameter("prid");
        request.setAttribute("type", type);
        request.setAttribute("multi", multi);
        request.setAttribute("op", op);
        request.setAttribute("prid", prid);
        return "home";
    }

    /**
     * todo 加注释
     */
    public void brown() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String virtual = this.virtual;
            String type = this.type;
            if (type.equals("6")) {
                type = "6,2,3,5";
            }
            String srhText = this.srhText;
            List<JsonForm> resourceList = tResourceDao.selectResources(virtual, type, srhText);//loadResource();
            this.writeJson(resourceList);
        } catch (Exception e) {
            logger.error("", e);
            //    e.printStackTrace();
            this.writeJson("");
        }
    }

    /**
     * todo 加注释
     * @param object 需要回写的对象
     */
    public void writeJson(Object object) {
        try {
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.error("", e);
            //   e.printStackTrace();
        }
    }
}
