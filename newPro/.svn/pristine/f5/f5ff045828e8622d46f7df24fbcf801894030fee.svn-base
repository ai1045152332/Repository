package com.honghe.recordweb.service.frontend.devicemanager;

import com.alibaba.fastjson.JSON;
import com.honghe.recordweb.service.frontend.event.DeviceEvent;
import com.honghe.recordweb.util.JSONUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Tpaduser on 2015/7/27.
 */
@WebServlet(urlPatterns = "/serviceEvent", loadOnStartup = 1)
public class EventService extends HttpServlet{
    private DeviceEvent deviceEvent;

    @Override
    public void init() throws ServletException {
        deviceEvent = ContextLoaderListener.getCurrentWebApplicationContext().getBean(DeviceEvent.class);
        super.init();
    }

    /**
     * todo 加注释
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(req.getParameter("eventsData")!=null){
            Object eventsData = URLDecoder.decode(req.getParameter("eventsData"), "UTF-8");
            JSONArray jsonArray = JSONArray.fromObject(eventsData);
            List eventsList = (List) JSONUtil.jsonToList(jsonArray);
            //处理发送来的多个设备事件信息
            eventsProsses(eventsList);
            JSONObject object=new JSONObject();
            object.put("state","success");
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
            res.getWriter().write(json);
        } else if (req.getParameter("event")!=null){
            String event = req.getParameter("event");
            String strDeviceToken = req.getParameter("strDeviceToke");
            String strEventContext = req.getParameter("strEventContext");
            String deviceType = req.getParameter("deviceType");
            String deviceMac = req.getParameter("deviceMac");//设备物理地址 添加时间2015.12.16
            deviceEvent.excute(event,deviceType,strDeviceToken,strEventContext,deviceMac);

            JSONObject object=new JSONObject();
            object.put("state","success");
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
            res.getWriter().write(json);
        }else{
            res.getWriter().write("Params are error!");
        }
        res.setContentType("text/html;charset=utf-8");
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


    /**
     * 解析设备
     * @param eventsList
     */
    private void eventsProsses(List eventsList){
        if (eventsList!=null&&eventsList.size()>0){
            for (Object devicesEvent:eventsList){
                Map eventMap = (Map) devicesEvent;
                Object eventInfo = eventMap.get("eventInfo");
                String deviceType = eventMap.get("deviceType").toString();
                if (eventInfo!=null){
                    List deviceList = (List) eventMap.get("eventInfo");
                    if (deviceList!=null&&deviceList.size()>0){
                        for (Object eventObject:deviceList){
                            Map event = (Map) eventObject;
                            String deviceIp = event.get("deviceIp").toString();
                            String eventType = event.get("eventType").toString();
                            String deviceMac = event.get("deviceMac").toString();
                            String eventContext = event.get("eventContext").toString();
                            deviceEvent.excute(eventType,deviceType,deviceIp,eventContext,deviceMac);
                        }
                    }
                }

            }

        }
    }
}
