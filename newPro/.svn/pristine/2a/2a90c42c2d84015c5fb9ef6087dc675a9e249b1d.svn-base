package com.honghe.recordweb.service.project.dtype;

import com.honghe.recordhibernate.dao.CommandDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Command;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lch on 2014/11/3.
 */
@Service
public class CommandService {

    private final static Logger logger = Logger.getLogger(CommandService.class);

    @Resource
    CommandDao commandDao;

    /**
     * 分页获取命令列表
     *
     * @param currentpage
     * @param pagesize
     * @return
     */
    public Map<String, Object> getDcommList(int currentpage, int pagesize) {
        try {
            Page<List<Object[]>> page = new Page<List<Object[]>>(currentpage, pagesize);
            // System.out.println(currentPage);
            //hostgroupDao.getHostgroupList(page);
            List<Command> result = commandDao.getDeviceCommList(page);
            //System.out.println(result);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("commandlist", result);
            map.put("page", page);
            return map;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("分页获取命令列表异常", e);
            return null;
        }
    }

    /**
     * 删除
     *
     * @param commandid
     * @return boolean
     */
    @Transactional
    public boolean delcommand(int commandid) {
        try {
            return commandDao.delete(commandid);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 根据commid获取command信息
     *
     * @param commid
     * @return Command
     */
    public Command getCommInfoById(int commid) {
        try {
            return commandDao.getCommandById(commid);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 添加命令
     *
     * @param cmdName
     * @param cmdGroup
     * @param cmdHex
     * @param cmdDefault
     * @param cmdFlag
     * @param cmdImage
     * @return
     */
    @Transactional
    public Boolean cmdAdd(String cmdName, String cmdGroup, String cmdHex, Integer cmdDefault, String cmdFlag, String cmdImage) {
        try {
            Command command = new Command();
            command.setCmdName(cmdName);
            command.setCmdGroup(cmdGroup);
            command.setCmdHex(cmdHex);
            command.setCmdDefault(cmdDefault);
            command.setCmdImage(cmdImage);
            command.setCmdFlag(cmdFlag);
            return commandDao.save(command);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改命令
     *
     * @param cmdId
     * @param cmdName
     * @param cmdGroup
     * @param cmdHex
     * @param cmdDefault
     * @param cmdFlag
     * @param cmdImage
     * @return
     */
    @Transactional
    public Boolean cmdUpdate(Integer cmdId, String cmdName, String cmdGroup, String cmdHex, Integer cmdDefault, String cmdFlag, String cmdImage) {
        try {
            Command command = new Command();
            command.setCmdId(cmdId);
            command.setCmdName(cmdName);
            command.setCmdGroup(cmdGroup);
            command.setCmdHex(cmdHex);
            command.setCmdDefault(cmdDefault);
            command.setCmdImage(cmdImage);
            command.setCmdFlag(cmdFlag);
            return commandDao.updata(command);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取命令列表
     *
     * @return List 类型数据
     * @throws Exception
     */
    @Transactional
    public List<Command> getCommandListService() {
        List<Command> list = null;

        try {
            list = commandDao.getCommandList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
