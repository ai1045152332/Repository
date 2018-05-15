package com.honghe.recordweb.action.frontend.news;

import com.alibaba.fastjson.JSON;
import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
public class DeviceGroupAction extends BaseDao {
    private final static Logger logger = Logger.getLogger(DeviceGroupAction.class);

    /**
     * //todo 加注释
     */
    public void getGroupsTreeHasDevice(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        int userId = user.getUserId();
        List list = new ArrayList();
        writeJson(list);
    }

    /**
     * //todo 加注释
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
            logger.error("",e);
         //   e.printStackTrace();
        }
    }
}
