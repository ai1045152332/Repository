package com.honghe.recordweb.service.frontend.liveplan;

import com.alibaba.fastjson.JSON;
import com.honghe.recordhibernate.dao.*;
import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import com.honghe.service.client.udp.UdpClient;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzz on 2016/9/6.
 */
@Service("liveplanService")
public class LiveplanService {
    private final static Logger logger = Logger.getLogger(LiveplanService.class);
    @Resource
    HostgroupService hostgroupService;
    @Resource
    HostDevice hostDevice;
    @Resource
    HongheDeviceService hongheDeviceService;
    @Resource
    ClasstimeService classtimeService;
    @Resource
    private LiveplanDao liveplanDao;
    @Resource
    private SettingsService settingsService;
    @Resource
    private DeviceService deviceService;

    /**
     * 添加主机直播计划
     *
     * @param hostId      int 主机id
     * @param sectionId int 上课节次
     * @param week_id     byte 周几
     * @return list
     */
    @Transactional
    public Integer addLiveplan(int hostId, int sectionId, Byte week_id,String dateStr) {
        try {
            Liveplan liveplan = new Liveplan();
            liveplan.setLiveplanSection(sectionId);
            liveplan.setLiveplanHost(hostId);
            Setting setting = settingsService.getSetting();
            Integer liveplanId;
            if (setting != null && setting.getCurriculumType() == Curriculum.CUT_DATE_TYPE) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(dateStr);
                liveplan.setLiveplanDate(_date);
                liveplanId = liveplanDao.getLiveplan(hostId, sectionId, dateStr);
            } else {
                liveplan.setLiveplanWeek(week_id);
                liveplanId = liveplanDao.getLiveplan(hostId, sectionId, week_id);
            }
            if(liveplanId != null && liveplanId != 0)
            {
                return liveplanId;
            }
            else {
                if (liveplanDao.save(liveplan)) {
                    return liveplan.getLiveplanId();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            logger.error("添加主机直播计划异常", e);
            return 0;
        }
    }

    /**
     * 删除直播计划
     * @param liveplan_id
     * @return
     */
    @Transactional
    public Boolean delLiveplan(int liveplan_id) {
        try {
            if (liveplanDao.delete(liveplan_id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("删除计划异常", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * todo 加注释
     * @param section_id
     * @param host_id
     * @param week_id
     * @return
     * @throws Exception
     */
    public String getChannel(int section_id, int host_id, Byte week_id) throws Exception{
        return liveplanDao.getChannel(host_id, section_id ,week_id);
    }

    /**
     * todo 加注释
     * @param section_id
     * @param host_id
     * @param date
     * @return
     * @throws Exception
     */
    public String getChannel(int section_id, int host_id, Date date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        return liveplanDao.getChannel(host_id, section_id ,_date);
    }

    /**
     * todo 加注释
     * @param host_id
     * @param week_id
     * @param section_id
     * @param channels
     * @throws Exception
     */
    @Transactional
    public Liveplan updateChannel(int host_id, Byte week_id, int section_id, String channels) throws Exception{
        Integer liveplanId = liveplanDao.getLiveplan(host_id, section_id, week_id);
        Liveplan liveplan = null;
        if (liveplanId == null) {
            liveplan = new Liveplan();
            liveplan.setLiveplanHost(host_id);
            liveplan.setLiveplanWeek(week_id);
            liveplan.setLiveplanSection(section_id);
            liveplan.setLiveplanChannel(channels);
            liveplanDao.save(liveplan);
            liveplan = null;
        } else {
            liveplan = liveplanDao.getLiveplan(liveplanId);
            liveplanDao.update(channels, liveplanId);
        }

        return liveplan;
    }

    /**
     * todo 加注释
     * @param host_id
     * @param date
     * @param section_id
     * @param channels
     * @throws Exception
     */
    @Transactional
    public Liveplan updateChannel(int host_id, Date date, int section_id, String channels) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        Integer liveplanId = liveplanDao.getLiveplan(host_id, section_id, _date);
        Liveplan liveplan = null;
        if (liveplanId == null) {
            liveplan = new Liveplan();
            liveplan.setLiveplanHost(host_id);
            liveplan.setLiveplanDate(date);
            liveplan.setLiveplanSection(section_id);
            liveplan.setLiveplanChannel(channels);
            liveplanDao.save(liveplan);
            liveplan = null;
        } else {
            liveplan = liveplanDao.getLiveplan(liveplanId);
            liveplanDao.update(channels, liveplanId);
        }
        return liveplan;
    }

    /**
     * todo 加注释
     * @param section_id
     * @param host_id
     * @param week_id
     * @return
     * @throws Exception
     */
    public Integer getLiveplanId(int section_id, int host_id, Byte week_id) throws Exception {
        return liveplanDao.getLiveplanId(host_id, section_id, week_id);
    }

    /**
     * todo 加注释
     * @param section_id
     * @param host_id
     * @param date
     * @return
     * @throws Exception
     */
    public Integer getLiveplanId(int section_id, int host_id, Date date) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        return liveplanDao.getLiveplanId(host_id, section_id ,_date);
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void clearplan() throws Exception {
        liveplanDao.clearPlan();
    }

    /**
     * 获取有直播计划的班级列表
     * @return
     */
    public List<Object[]> getLiveplanHostList() {
        List<Object[]> hostlist = new ArrayList<>();
        try {
            List<Integer> list = liveplanDao.getAllPan();
            if (list != null && list.size() > 0) {
                String hostIds = "";
                for (Integer obs : list) {
                    hostIds += obs + ",";
                }
                if (hostIds.endsWith(",")) {
                    hostIds = hostIds.substring(0, hostIds.length() - 1);
                }
                Result res = hostDevice.getHostInfo(CommonName.DEVICE_TYPE_RECOURD, hostIds);
                if (res.getCode() == 0 && res.getValue() != null) {
                    List<Map<String, Object>> hostRes = ((List<Map<String, Object>>) res.getValue());
                    if (hostRes != null && hostRes.size() > 0) {
                        for (Map<String, Object> map : hostRes) {
                            Object[] hostInfoArr = new Object[2];
                            if(map!=null){
                                hostInfoArr[1] = map.get("id");
                                hostInfoArr[0] = map.get("host_ip");
                                hostlist.add(hostInfoArr);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return hostlist;
    }

    /**
     * update by zlj on 2018/04/12
     * 调用资源平台接口，开启直播
     * @param hostIp
     * @param livePlanList
     * @param hostId
     * @return
     */
    @Transactional
    public boolean startLiveplan(String hostIp,List<Object[]> livePlanList,int hostId) {
        try {
            Map<String,Object> hostInfo = hongheDeviceService.getHostInfoByIp(hostIp);
            String hostName = hostInfo.get("host_name").toString();
//            String rtmpServerIp = System.getProperty("RtmpServerIp");
//            int rtmpServerPort = Integer.parseInt(System.getProperty("RtmpServerPort"));
            for(int j=0;j<livePlanList.size();j++) {
                String channel = livePlanList.get(j)[4] == null ? "" : livePlanList.get(j)[4].toString();
                String[] _channel = channel.split(",");
                String[] _stream = new String[_channel.length];
                for (int i = 0; i < _channel.length; i++) {
                    String tokenName = _channel[i];
                    Object[] stream = deviceService.getStream(hostId, tokenName);
                    String mainStream = stream[0].toString();
                    String rtmpStream = "rtmp://"+settingsService.getSetting().getLiveAddr() + "/"+ DigestUtils.md5Hex(hostIp+tokenName);
                    _stream[i] = rtmpStream;
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("action", "start");
//                    jsonObject.put("rtsp", mainStream);
//                    jsonObject.put("rtmp", rtmpStream);
//                    UdpServiceClient.send(rtmpServerIp, rtmpServerPort, JSON.toJSONString(jsonObject).getBytes());
//                    System.out.println("hostName-start:"+hostName+"--"+mainStream+"--"+rtmpStream);
//                    logger.error("hostName-start:"+hostName+"--"+mainStream+"--"+rtmpStream);
                }
                Map<String,Object> livePlanMap = new HashMap<>();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                String date = dt.format(Calendar.getInstance().getTime());
                livePlanMap.put("id",livePlanList.get(j)[0]);
                livePlanMap.put("section",livePlanList.get(j)[2]);
                livePlanMap.put("hostId",hostId);
                livePlanMap.put("room_name",hostName);
                livePlanMap.put("channel",_channel);
                livePlanMap.put("stream",_stream);
                livePlanMap.put("title",hostName + (livePlanList.get(j)[10]==null?"":livePlanList.get(j)[10])+"直播");
                String rtmpurl = "";
                //电影模式放到第一个位置
                for(int i=0;i<_channel.length;i++)
                {
                    if(i != 0 && _channel[i].equals("电影模式"))
                    {
                        String channle0 = _channel[0];
                        _channel[0] = _channel[i];
                        _channel[i] = channle0;
                        String stream0 = _stream[0];
                        _stream[0] = _stream[i];
                        _stream[i] = stream0;
                    }

                }
                //生成资源平台code参数，Json格式
                for(int i=0;i<_channel.length;i++)
                {
                    rtmpurl+="{\"stream\":\""+_stream[i]+"\",\"name\":\""+_channel[i]+"\"},";
                }
                if(_channel.length>0)
                {
                    rtmpurl = rtmpurl.substring(0,rtmpurl.length()-1);
                    rtmpurl="["+rtmpurl+"]";
                }
                livePlanMap.put("code",rtmpurl);
                livePlanMap.put("roomid",livePlanList.get(j)[6]==null?"":livePlanList.get(j)[6]);
                livePlanMap.put("autoclose",livePlanList.get(j)[7]==null?"":livePlanList.get(j)[7]);
                livePlanMap.put("start",date+" "+livePlanList.get(j)[8]+":00");
                livePlanMap.put("end",date+" "+livePlanList.get(j)[9]+":00");
                livePlanMap.put("subject",livePlanList.get(j)[10]==null?"":livePlanList.get(j)[10]);
                livePlanMap.put("teacher_name",livePlanList.get(j)[11]==null?"":livePlanList.get(j)[11]);
                livePlanMap.put("description","录播平台直播计划发起的直播");
                //add by xinye
                int size = _channel.length;
                livePlanMap.put("video_streams_count",size==1?1:2);//单路、多路设置
                if(size!=1){
                    int flag = 1;
                    if(size==4){
                        flag = 2;
                    }else if(size>4){
                        flag = 3;
                    }
                    livePlanMap.put("flag",flag);//分屏模式
                }
                //add by xinye end
                if(livePlanMap.get("roomid").equals(""))
                {
                    int roomId = addLiveRomm(livePlanMap);
                    System.out.println("新增直播间：id"+roomId);
                    logger.error("新增直播间：id"+roomId);
                    if(roomId != -1)
                    {
                        Liveplan liveplan = liveplanDao.getLiveplan(Integer.parseInt(livePlanMap.get("id").toString()));
                        if(liveplan != null) {
                            liveplan.setLiveplanRoomid(roomId + "");
                            liveplan.setLiveplanFlag(1);
                            liveplanDao.update(liveplan);
                        }
                        openLiveRomm(roomId);
                    }
                }
                else
                {
                    int roomId = Integer.parseInt(livePlanMap.get("roomid").toString());
                    Map<String,String> liveRoom = getLiveRomm(roomId);
                    if(!rtmpurl.equals(liveRoom.get("code"))) {
                        boolean result = editLiveRomm(livePlanMap);
                        System.out.println("编辑直播间"+result);
                        logger.error("编辑直播间"+result);
                    }
                    if(!"yes".equals(liveRoom.get("active")))
                    {
                        boolean result = openLiveRomm(roomId);
                        System.out.println("开启直播间"+result);
                        logger.error("开启直播间"+result);
                    }
                    System.out.println("修改直播间");
                    logger.error("修改直播间");
                    int livePlanId = Integer.parseInt(livePlanMap.get("id").toString());
                    Liveplan liveplan = liveplanDao.getLiveplan(livePlanId);
                    if(liveplan != null) {
                        liveplan.setLiveplanFlag(1);
                        liveplanDao.update(liveplan);
                    }
                }
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 调用资源平台接口，关闭直播
     * @param hostIp
     * @param livePlanList
     * @param hostId
     * @return
     */
    @Transactional
    public boolean stopLiveplan(String hostIp,List<Object[]> livePlanList,int hostId) {
        try {
            for(int j=0;j<livePlanList.size();j++) {
                String channel = livePlanList.get(j)[4] == null ? "" : livePlanList.get(j)[4].toString();
                int roomId = Integer.parseInt(livePlanList.get(j)[6].toString());
                boolean delResult = delLiveRoom(roomId);;
                logger.error("delResult:"+delResult);
                System.out.println("delResult:"+delResult);
                int livePlanId = Integer.parseInt(livePlanList.get(j)[0].toString());
                Liveplan liveplan = liveplanDao.getLiveplan(livePlanId);
                if(liveplan != null) {
                    liveplan.setLiveplanFlag(0);
                    liveplan.setLiveplanRoomid(null);
                    liveplanDao.update(liveplan);
                }
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * 调用资源平台接口，创建直播间
     * @param livePlanMap
     * @return
     */
    public int addLiveRomm(Map<String,Object> livePlanMap)
    {
        int roomId = -1;
        try {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?cmd=add_live&key=getlive";
            String content = "";
            Map<String, String> data = new HashMap<>();
            data.put("title",livePlanMap.get("title")==null?"":livePlanMap.get("title").toString());//标题
            data.put("description", "录播平台直播计划发起的直播");//科目
            data.put("teacher_name", livePlanMap.get("teacher_name")==null?"":livePlanMap.get("teacher_name").toString());//教师
            data.put("room_name", livePlanMap.get("room_name")==null?"":livePlanMap.get("room_name").toString());//直播间名称
            data.put("start_time", livePlanMap.get("start")==null?"":livePlanMap.get("start").toString());//开始时间
            data.put("end_time", livePlanMap.get("end")==null?"":livePlanMap.get("end").toString());//结束时间
            data.put("code", livePlanMap.get("code")==null?"":livePlanMap.get("code").toString());//直播流
            //add by xinye
            if(livePlanMap.get("video_streams_count")!=null){
                data.put("video_streams_count",livePlanMap.get("video_streams_count").toString());
                int count = Integer.parseInt(livePlanMap.get("video_streams_count").toString());
                if(count==2){
                    data.put("flag",livePlanMap.get("flag").toString());
                }
            }
            //add by xinye end
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).
                    data(data).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
            JSONObject jsonObject = JSONObject.fromObject(content);
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {
                roomId = Integer.parseInt(jsonObject.getString("result"));
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return roomId;
    }

    /**
     * 调用资源平台接口，修改直播间
     * @param livePlanMap
     * @return
     */
    public boolean editLiveRomm(Map<String,Object> livePlanMap)
    {
        boolean result = false;
        try {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?cmd=edit_live&key=getlive";
            String content = "";
            Map<String, String> data = new HashMap<>();
            data.put("liveid",livePlanMap.get("roomid")==null?"":livePlanMap.get("roomid").toString());
            data.put("title",livePlanMap.get("title")==null?"":livePlanMap.get("title").toString());//标题
            data.put("description", livePlanMap.get("description")==null?"":livePlanMap.get("description").toString());//科目
            data.put("teacher_name", livePlanMap.get("teacher_name")==null?"":livePlanMap.get("teacher_name").toString());//教师
            data.put("room_name", livePlanMap.get("room_name")==null?"":livePlanMap.get("room_name").toString());//直播间名称
            data.put("start_time", livePlanMap.get("start")==null?"":livePlanMap.get("start").toString());//开始时间
            data.put("end_time", livePlanMap.get("end")==null?"":livePlanMap.get("end").toString());//结束时间
            data.put("code", livePlanMap.get("code")==null?"":livePlanMap.get("code").toString());//直播流
            //add by xinye
            if(livePlanMap.get("video_streams_count")!=null){
                data.put("video_streams_count",livePlanMap.get("video_streams_count").toString());
                int count = Integer.parseInt(livePlanMap.get("video_streams_count").toString());
                if(count==2){
                    data.put("flag",livePlanMap.get("flag").toString());
                }
            }
            //add by xinye end
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).
                    data(data).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
//            JSONObject jsonObject = JSONObject.fromObject(content);
//            String code = jsonObject.getString("code");
            if(content.indexOf("\"code\":0") != -1)
            {
                result  = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }
    /**
     * 调用资源平台接口，查询直播间
     * @param roomId
     * @return
     */
    public Map<String, String> getLiveRomm(int roomId)
    {
        Map<String,String> liveRoomMap = new HashMap<>();
        try {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?" +
                    "cmd=get_liveinfo&key=getlive&liveid="+roomId;
            String content = "";
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
            JSONObject jsonObject = JSONObject.fromObject(content);
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {
                JSONObject liveRoomJson = JSONObject.fromObject(jsonObject.getString("result"));
                liveRoomMap = (Map<String,String>)liveRoomJson;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return liveRoomMap;
    }
    /**
     * 调用资源平台接口，开启直播间
     * @param roomId
     * @return
     */
    public boolean openLiveRomm(int roomId)
    {
        boolean result = false;
        try {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?" +
                    "cmd=update_active_yes&key=getlive&liveid="+roomId;
            String content = "";
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
            JSONObject jsonObject = JSONObject.fromObject(content);
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {
                result = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }
    /**
     * 调用资源平台接口，关闭直播间
     * @param roomId
     * @return
     */
    public boolean closeLiveRomm(int roomId)
    {
        boolean result = false;
        try {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?" +
                    "cmd=update_active_no&key=getlive&liveid="+roomId;
            String content = "";
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
            JSONObject jsonObject = JSONObject.fromObject(content);
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {
                result = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;
    }

    /**
     * 调用资源平台接口，删除直播间
     * @param roomId
     * @return
     */
    public boolean delLiveRoom(int roomId){
        boolean result = false;
        try{
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?" +
                    "cmd=del_lives&key=getlive&liveid="+roomId;
            String content = "";
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
            JSONObject jsonObject = JSONObject.fromObject(content);
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {
                result = true;
            }
        }catch(Exception e){
            logger.error("",e);
        }
        return result;
    }
    public static void main(String[] args) {
        try {
            String url = "http://www.resappnew.com/apps/live_api.php?cmd=edit_live&key=getlive";
            String content = "";
            Map<String, String> data = new HashMap<>();
            data.put("liveid","146");
            data.put("title", "直播");//标题
            data.put("description", "直播计划发起的直播");//科目
            data.put("teacher_name", "12");//教师
            data.put("room_name", "23");//直播间名称
            data.put("start_time", "2016-8-1");//开始时间
            data.put("end_time", "2016-8-9");//结束时间
            Object[] channels = {"1","2","3"};
            data.put("video_streams_count", (channels.length+""));//结束时间
            Object[] streams = {"a","b","c"};
            String rtmpurl = "";
            for(int i=0;i<channels.length;i++)
            {
                rtmpurl+="{\"stream\":\""+streams[i]+"\",\"name\":\""+channels[i]+"\"},";
            }
            if(channels.length>0)
            {
                rtmpurl = rtmpurl.substring(0,rtmpurl.length()-1);
                rtmpurl="["+rtmpurl+"]";
            }
            data.put("code", rtmpurl);//直播流
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).
                    data(data).ignoreContentType(true).execute().body().trim();
            content = StringEscapeUtils.unescapeJava(content).trim();
            JSONObject jsonObject = JSONObject.fromObject(removeBOM(content));
            String code = jsonObject.getString("code");
            if(code.equals("0"))
            {

            }
        } catch (Exception e) {
            logger.error("", e);
        }
//            String url = "http://www.resappnew.com/apps/live_api.php?" +
//                    "cmd=update_active_no&key=getlive&liveid="+146;
//            String content = "";
//            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
//            content = removeBOM(StringEscapeUtils.unescapeJava(content).trim());
//            JSONObject jsonObject = JSONObject.fromObject(content);
//            String code = jsonObject.getString("code");
//            if(code.equals("0"))
//            {
//                JSONObject liveRoomJson = JSONObject.fromObject(jsonObject.getString("result"));
//
//            }
//        } catch (Exception e) {
//            logger.error("", e);
//        }
    }

    /**
     * todo 加注释
     * @param data
     * @return
     */
    public static final String removeBOM(String data) {
        if (data != null && data.startsWith("\ufeff")) {
            data = data.replaceAll("\ufeff","");
        }
        return data;
    }

    /**
     * update by zlj on 2018/04/12
     * 获取设备ip
     * @param host_id
     * @return
     */
    public String getHostIp(String host_id) {
        Map hostMap = hongheDeviceService.getHostInfoById(host_id);
        return hostMap.get("host_ipaddr").toString();
    }

    public List<Object[]> getLivePlanListByHostId(int i) {
        try{
            return liveplanDao.getLivePlanListByHostId(i);
        }catch(Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * update by zlj on 2018/04/12
     *根据指令操作直播流
     * @param command "start" 新建流 "stop" 关闭流
     * @param channels 通道名称
     * @param hostId  主机id
     * @param hostIp 主机ip
     * @return
     * Author xinye
     */
    public boolean handleStream(String hostIp,int hostId,String command,String...channels){

        try{
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            String rtmpServerIp = ConfigUtil.get("RtmpServerIp");
            int rtmpServerPort = Integer.parseInt(ConfigUtil.get("RtmpServerPort"));
            Map<String,Object> hostInfo = hongheDeviceService.getHostInfoByIp(hostIp);
            String hostName = hostInfo.get("host_name").toString();
            String[] _stream = new String[channels.length];
            for (int i = 0; i < channels.length; i++) {
                String tokenName = channels[i];
                Object[] stream = deviceService.getStream(hostId, tokenName);
                String mainStream = stream[0].toString();
                if(mainStream==null||"".equals(mainStream)){
                    System.out.println("主机："+hostId+"*****"+tokenName+"rtsp为空");
                    continue;
                }
                String rtmpStream = "rtmp://"+settingsService.getSetting().getLiveAddr() + "/"+ DigestUtils.md5Hex(hostIp+tokenName);
                _stream[i] = rtmpStream;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", command);
                jsonObject.put("rtsp", mainStream);
                jsonObject.put("rtmp", rtmpStream);
                UdpClient.send(rtmpServerIp, rtmpServerPort, JSON.toJSONString(jsonObject).getBytes());
                System.out.println("hostName-"+command+":"+hostName+"--"+mainStream+"--"+rtmpStream);
                logger.error("hostName-"+command+":"+hostName+"--"+mainStream+"--"+rtmpStream);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //************************************************** cancel by lichong *****************************************

//    /**
//     * 查询指定班级、星期直播计划
//     * @param hostid
//     * @param week_id
//     * @return
//     */
//    public List<Object[]> getLiveplanList(int hostid, int week_id) {
//        try {
//            return liveplanDao.getLiveplanList(hostid, week_id);
//        } catch (Exception e) {
//            logger.error(e);
//            return Collections.emptyList();
//        }
//    }

//    /**
//     * 根据班级ID获取当前5分钟内直播计划
//     * @param hostId
//     * @param curDate
//     * @return
//     */
//    public List<Object[]> getLiveplanByHost(int hostId,Date curDate)
//    {
//        List<Object[]> livePlanList = new ArrayList<>();
//        try {
//            Setting setting = settingsService.getSetting();
//            int curriculumType = Curriculum.CUR_WEEK_TYPE;
//            if(setting != null && setting.getCurriculumType() != null)
//            {
//                curriculumType = setting.getCurriculumType();
//            }
//            String type = "week";
//            if(curriculumType == Curriculum.CUT_DATE_TYPE)
//            {
//                type = "term";
//            }
//            livePlanList = liveplanDao.getLiveplanByHost(hostId,type,curDate);
//        }
//        catch (Exception e)
//        {
//            livePlanList = Collections.EMPTY_LIST;
//        }
//        return livePlanList;
//    }

    // HostgroupService.java 中调用到此方法，所以没有屏蔽，
    public List getLiveplanList(Integer liveplan_id,String type) {
        try {
//            return liveplanDao.getLiveplanList(liveplan_id,type); //原来的
            return liveplanDao.getLiveplanList(-1,liveplan_id,type);  //edit by lichong
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

//    /**
//     * 查询指定班级、日期直播计划
//     * @param hostid
//     * @param date
//     * @return
//     */
//    public List<Object[]> getLiveplanList(int hostid, Date date) {
//        try {
//            List<Object[]> curriculums = liveplanDao.getLiveplanList(hostid, date);
//            return curriculums;
//        } catch (Exception e) {
//            logger.error(e);
//            return Collections.emptyList();
//        }
//    }
    //*************************************************** add by lichong *********************************************

    /**
     * add by lichong
     * 查询指定班级、星期直播计划
     * @param hostid
     * @param week_id
     * @return
     */
    public List<Object[]> getLiveplanList(int hostid, int week_id) {
        int groupId = -1;
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostid);
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.valueOf(groupList.get(0)[0].toString());
            }
            return liveplanDao.getLiveplanList(groupId, hostid, week_id);
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }

    /**
     * add by lichong
     * 根据班级ID获取当前5分钟内直播计划
     * @param hostId
     * @param curDate
     * @return
     */
    public List<Object[]> getLiveplanByHost(int hostId,Date curDate)
    {
        List<Object[]> livePlanList = new ArrayList<>();
        int groupId = -1;
        try {
            Setting setting = settingsService.getSetting();
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostId);
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            String type = "week";
            if(curriculumType == Curriculum.CUT_DATE_TYPE)
            {
                type = "term";
            }
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.valueOf(groupList.get(0)[0].toString());
            }
            livePlanList = liveplanDao.getLiveplanByHost(groupId,hostId,type,curDate);
        }
        catch (Exception e)
        {
            livePlanList = Collections.EMPTY_LIST;
        }
        return livePlanList;
    }

    /**
     * add by lichong
     * @param liveplan_id
     * @param type
     * @return
     */
    public List getLiveplanList(int hostId, int liveplan_id,String type) {
        int groupId = -1;
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostId);
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.valueOf(groupList.get(0)[0].toString());
            }
            return liveplanDao.getLiveplanList(groupId,liveplan_id,type);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }


    /**
     * add by lichong
     * 查询指定班级、日期直播计划
     * @param hostid
     * @param date
     * @return
     */
    public List<Object[]> getLiveplanList(int hostid, Date date) {
        int groupId = -1;
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostid);
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.parseInt(groupList.get(0)[0].toString());
            }
            List<Object[]> curriculums = liveplanDao.getLiveplanList(groupId,hostid, date);
            return curriculums;
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }
   // ******************************************************** cancel by lichong ********************************************
//    /**
//     * 根据班级ID获取当前时间结束的直播计划
//     * @param hostId
//     * @param curDate
//     * @param flag 0:查询所有正在直播的直播间,1:查询当前时间5分钟后即将结束的直播计划
//     * @return
//     */
//    public List<Object[]> getLiveplanEndByHost(int hostId,Date curDate,int flag)
//    {
//        List<Object[]> livePlanList = new ArrayList<>();
//        try {
//            Setting setting = settingsService.getSetting();
//            int curriculumType = Curriculum.CUR_WEEK_TYPE;
//            if(setting != null && setting.getCurriculumType() != null)
//            {
//                curriculumType = setting.getCurriculumType();
//            }
//            String type = "week";
//            if(curriculumType == Curriculum.CUT_DATE_TYPE)
//            {
//                type = "term";
//            }
//            livePlanList = liveplanDao.getLiveplanEndByHost(hostId,type,curDate,flag);
//        }
//        catch (Exception e)
//        {
//            livePlanList = Collections.EMPTY_LIST;
//        }
//        return livePlanList;
//    }

    //***************************************************** add by lichong **********************************************
    /**
     * 根据班级ID获取当前时间结束的直播计划
     * @param hostId
     * @param curDate
     * @param flag 0:查询所有正在直播的直播间,1:查询当前时间5分钟后即将结束的直播计划
     * @return
     */
    public List<Object[]> getLiveplanEndByHost(int hostId,Date curDate,int flag)
    {
        List<Object[]> livePlanList = new ArrayList<>();
        int groupId = -1;
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostId);
            Setting setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            String type = "week";
            if(curriculumType == Curriculum.CUT_DATE_TYPE)
            {
                type = "term";
            }
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.parseInt(groupList.get(0)[0].toString());
            }
            livePlanList = liveplanDao.getLiveplanEndByHost(groupId,hostId,type,curDate,flag);
        }
        catch (Exception e)
        {
            livePlanList = Collections.EMPTY_LIST;
        }
        return livePlanList;
    }

}

