package com.honghe.recordweb.action.project.device;

import com.honghe.recordhibernate.dao.CommandDao;
import com.honghe.recordhibernate.entity.Command;
import com.honghe.recordweb.action.BaseAction;
import net.sf.json.JSONArray;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/9/22.
 */
public class HostAction extends BaseAction
{
    @Resource
    private CommandDao commandDao;
    public void setCommandDao(CommandDao commandDao)
    {
        this.commandDao = commandDao;
    }

    public List getHcommList()
    {
        List list = null;

        list = commandDao.getHostCommList();

        return list;
    }

    /**
     *
     */
    public void hcommJSON()
    {
        List<Command> list = getHcommList();

        if (list==null)
        {
            return;
        }

        List datalist = new ArrayList();
        for (int i=0; i<list.size(); i++)
        {
            Command command = list.get(i);
            int id = command.getCmdId();
            String name = command.getCmdName();
            String group = command.getCmdGroup();
            String hex = command.getCmdHex();

            Map dcommmap = new HashMap();
            dcommmap.put("hcomm_id",id);
            dcommmap.put("hcomm_name",name);
            dcommmap.put("hcomm_group",group);
            dcommmap.put("hcomm_hex",hex);

            datalist.add(dcommmap);
        }

        JSONArray jsonArray = JSONArray.fromObject(datalist);
        String jsonstr = jsonArray.toString();

        writeJSON(jsonstr);
    }

    private String hcommname;
    private String hcommgroup;
    private String hcommvaluehex;

    public void setHcommname(String hcommname)
    {
        this.hcommname = hcommname;
    }

    public void setHcommgroup(String hcommgroup)
    {
        this.hcommgroup = hcommgroup;
    }

    public void setHcommvaluehex(String hcommvaluehex)
    {
        this.hcommvaluehex = hcommvaluehex;
    }

    /**
     * 增加保存
     */
    public void add()
    {
        /*JSONObject json = new JSONObject();

        if (hcommname.equals("") || hcommgroup.equals("") || hcommvaluehex.equals(""))
        {
            json.put("success", false);
            json.put("msg", "请填写完整！");
        }
        else
        {
            List<Command> commlist = null;
            commlist = commandDao.getDeviceCommByName(hcommname);
            if (commlist != null)
            {
                json.put("success", false);
                json.put("msg", "命令名称重复！");
            }
            commlist = commandDao.getDeviceCommByHex(hcommvaluehex);
            if (commlist !=null)
            {
                json.put("success", false);
                json.put("msg", "命令值重复！");
            }
            else
            {
                Command command = new Command();
                command.setCmdName(hcommname);
                command.setCmdGroup(hcommgroup);
                command.setCmdHex(hcommvaluehex);
                command.setCmdFlag("10");
                commandDao.save(command);
                json.put("success", true);
                json.put("msg", "添加成功！");
            }
        }

        String jsonstr = json.toString();
        System.out.println(jsonstr);
        writeJSON(jsonstr);*/
    }

    /**
     * 编辑更新
     */
    /*public void edit()
    {
        HttpServletRequest request = ServletActionContext.getRequest(); //req的获得必须在具体的方法中实现

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
            Command command = commandDao.getCommandById(Integer.valueOf(rArray[0]).intValue());
            if (command==null)
            {
                json.put("success",false);
                json.put("msg","没有找到要更新的数据！");
            }
            else
            {
                Command commdata = new Command();
                commdata.setCmdId(Integer.valueOf(rArray[0]).intValue());
                commdata.setCmdName(rArray[1]);
                commdata.setCmdGroup(rArray[2]);
                commdata.setCmdHex(rArray[3]);
                commdata.setCmdFlag("10");

                if(commandDao.updata(commdata))
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
        }

        String jsonstr = json.toString();
        System.out.println(jsonstr);
        writeJSON(jsonstr);
    }*/

    /*public void delete()
    {
        HttpServletRequest request = ServletActionContext.getRequest(); //req的获得必须在具体的方法中实现
        String r = request.getParameter("idata");
        System.out.println(r);

        String rArray[] = r.split(",");
        JSONObject json = new JSONObject();

        for (int i=0; i<rArray.length; i++)
        {
            int n = Integer.valueOf(rArray[i]).intValue();
            if (!commandDao.delete(n))
            {
                json.put("success",false);
                json.put("msg","删除失败！");

                writeJSON(json.toString());
                return;
            }
        }

        json.put("success",true);
        json.put("msg","删除成功！");

        writeJSON(json.toString());
    }*/
}
