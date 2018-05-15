package com.honghe.recordweb.service.frontend.resource;

import com.honghe.recordhibernate.dao.NasDao;
import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2016/8/2
 */
public class NasAutoDelThread implements Runnable {
    private static final SimpleDateFormat DATE_FORMAT= new SimpleDateFormat("yyyy-MM-dd");
    private final static Logger logger = Logger.getLogger(ResourceService.class);
    private static final NasDao nasdao = (NasDao) ContextLoaderListener.getCurrentWebApplicationContext().getBean(NasDao.class);
    private static final ResourceService resourceService = (ResourceService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(ResourceService.class);
    private static final SettingsService settingsService = (SettingsService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(SettingsService.class);
    public NasAutoDelThread() {
    }

    @Override
    /**
     * todo 加注释
     */
    public void run() {
        try {
            List<Object[]> nasList = nasdao.getNasList();
            NasAutoDel.setNasList(nasList);
            RWNasProperties.init();
            Map<String,String> nasMap = RWNasProperties.getNasMap();
            for(Object[] objects : nasList)
            {
                String nasId = objects[0].toString();
                if(objects[1] != null && objects[1].toString().indexOf(":") > 0)
                {
                    String nasPath = objects[1].toString();
                    String nasIp = nasPath.substring(0,objects[1].toString().indexOf(":"));
                    if(nasPath.toLowerCase().indexOf("cifs") > -1)
                    {
                        nasIp = nasPath.substring(7,nasPath.length() -1);
                        nasIp = nasIp.substring(0,nasIp.indexOf("/"));
                        //nasIp = nasIp.substring(0,nasIp.indexOf("/"));
                    }
                    String nasDaysDel = nasMap.get("nas" + nasId);
                    if(nasDaysDel == null || nasDaysDel.equals(""))
                    {
                        continue;
                    }

                    List<Map<String, String>> nasConfigs = settingsService.getNasConfig();
                    String disk = "";
                    for (int i = 0; i < nasConfigs.size(); i++) {
                        Map<String, String> maps = nasConfigs.get(i);
                        if (maps.containsKey(nasIp)) {
                            disk = maps.get(nasIp);
                            nasIp = nasIp.replace(nasIp, disk+":");
                        }
                    }
                    String dateTime = caculateTime(nasDaysDel);
                    logger.error("NasAutoDelThread开始清理过期资源nasIp=" + nasIp + ",datetime=" + dateTime);
                    System.out.println("NasAutoDelThread开始清理过期资源nasIp=" + nasIp + ",datetime=" + dateTime);
                    resourceService.delResourceNasDel(nasIp,dateTime);
                }
            }
        } catch (Exception e) {
            logger.error("NasAutoDelThreadnas自动清理过期资源异常",e);
            e.printStackTrace();
        }


    }

    /**
     * todo 加注释
     * @param days
     * @return
     */
    private static String caculateTime(String days)
    {
        if(days != null && !days.equals(""))
        {
            try
            {
                long daysLong = Long.parseLong(days);
                Date nowDate = new Date();
                return DATE_FORMAT.format(nowDate.getTime() - 1000*3600*24*daysLong);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
