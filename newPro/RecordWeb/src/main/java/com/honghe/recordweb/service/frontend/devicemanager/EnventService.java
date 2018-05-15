package com.honghe.recordweb.service.frontend.devicemanager;

import com.alibaba.fastjson.JSON;
import com.honghe.recordweb.service.frontend.event.DeviceEvent;
import net.sf.json.JSONObject;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tpaduser on 2015/7/27.
 */
@WebServlet(urlPatterns = "/serviceEnvent", loadOnStartup = 1)
public class EnventService extends HttpServlet{
    //private HostEventService hostEventService;
    private DeviceEvent deviceEvent;

    /**
     * todo 加注释
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String event = req.getParameter("event");
        String strDeviceToken = req.getParameter("strDeviceToke");
        String strEventContext = req.getParameter("strEventContext");
        String deviceType = req.getParameter("deviceType");
        String deviceMac = req.getParameter("deviceMac");//设备物理地址 添加时间2015.12.16
        //hostEventService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostEventService.class);
        deviceEvent = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DeviceEvent.class);
        //hostEventService.excute(event,strDeviceToken,strEventContext);
        deviceEvent.excute(event,deviceType,strDeviceToken,strEventContext,deviceMac);

        JSONObject object=new JSONObject();
        object.put("state","success");
        String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().write(json);
        res.getWriter().flush();
        res.getWriter().close();
    }

    /**
     * todo 加注释
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
