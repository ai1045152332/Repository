package com.honghe.recordweb.service.project.dtype;

import com.honghe.recordhibernate.dao.*;
import com.honghe.recordhibernate.entity.Command;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordhibernate.entity.Dtype;
import com.honghe.recordhibernate.entity.Spec2command;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lch on 2014/10/28.
 */
@Service
public class SpecService {
    private final static Logger logger = Logger.getLogger(SpecService.class);
    @Resource
    private SpecDao specDao;

    @Resource
    private DTypeDao dTypeDao;

    @Resource
    private HostDao hostDao;

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private Spec2commandDao spec2commandDao;

    /**
     * 删除设备型号
     * @param specid
     * @return
     */
    @Transactional
    public Boolean delspec(int specid) {
        try {
            List spec2Comand = specDao.getSpecRelation(specid);
            List hostlist = hostDao.getHostBySpec(specid);
            List devicelist = deviceDao.getDeviceBySpec(specid);
            if (hostlist.size() == 0 && devicelist.size() == 0)
            //if(devicelist.size()==0)
            {
                boolean flag = false;
                if (spec2Comand.size() > 0) {
                    int i = spec2commandDao.deleteRelation(specid);
                    if (i > 0) {
                        flag = specDao.delete(specid);
                    } else {
                        flag = false;
                    }
                } else {
                    flag = specDao.delete(specid);
                }
                return flag;

            } else {
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e);
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param specid
     * @return
     */
    @Transactional
    public Dspecification getSpecById(int specid) {
        try {
            Dspecification dspecification = specDao.getSpecById(specid);
//            Dtype dtype = dspecification.getDtype();
            return dspecification;

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param specid
     * @param specname
     * @param spectype
     * @param connectparam
     * @param cmdId
     * @param cmdWord
     * @param cmdParam
     * @param cmdReturn
     * @param cmdFlag
     * @return
     */
    @Transactional
    public Boolean updateBySpecid(int specid, String specname, int spectype, String connectparam, String[] cmdId, String[] cmdWord, String[] cmdParam, String[] cmdReturn, String[] cmdFlag) {
        Boolean flag = false;
        try {
            Dspecification dspecification = new Dspecification();
            dspecification.setDspecId(specid);
            dspecification.setDspecName(specname);
            Dtype dtype = dTypeDao.getTypeById(Integer.valueOf(spectype));
            dspecification.setDtype(dtype);
            dspecification.setConnectParam(connectparam);
            if (specDao.updata(dspecification)) {
                List spec2commandlist = spec2commandDao.getSpec2commandBySpecID(dspecification);
                if (spec2commandlist != null && spec2commandlist.size() > 0) {
                    spec2commandDao.delete(dspecification);
                    for (int i = 0; i < cmdId.length; i++) {
                        if (!cmdId[i].equals("") && cmdId[i] != null) {
                            Spec2command spec2command = new Spec2command();
                            spec2command.setCmdFlag(cmdFlag[i]);
                            spec2command.setCmdParam(cmdParam[i]);
                            spec2command.setCmdReturn(cmdReturn[i]);
                            spec2command.setCmdWord(cmdWord[i]);
                            spec2command.setDspecification(dspecification);
                            Command command = new Command();
                            command.setCmdId(Integer.parseInt(cmdId[i]));
                            spec2command.setCommand(command);
                            spec2commandDao.save(spec2command);
                        }
                    }
                    flag = true;
                } else {
                    for (int i = 0; i < cmdId.length; i++) {
                        if (!cmdId[i].equals("") && cmdId[i] != null) {
                            Spec2command spec2command = new Spec2command();
                            spec2command.setCmdFlag(cmdFlag[i]);
                            spec2command.setCmdParam(cmdParam[i]);
                            spec2command.setCmdReturn(cmdReturn[i]);
                            spec2command.setCmdWord(cmdWord[i]);
                            spec2command.setDspecification(dspecification);
                            Command command = new Command();
                            command.setCmdId(Integer.parseInt(cmdId[i]));
                            spec2command.setCommand(command);
                            spec2commandDao.save(spec2command);
                        }
                    }
                    flag = true;
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return flag;
    }

    /**
     * todo 加注释
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Map<String, Object> getSpecListBypage(int currentPage, int pageSize) {
        try {
            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
            // System.out.println(currentPage);
            //hostgroupDao.getHostgroupList(page);
            List<Object[]> result = specDao.getSpecListByPage(page);
            //System.out.println(result);
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("speclist", result);
            map.put("page", page);
            return map;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param specname
     * @param connectparam
     * @param spectype
     * @param cmdId
     * @param cmdWord
     * @param cmdParam
     * @param cmdReturn
     * @param cmdFlag
     * @return
     */
    @Transactional
    public boolean save(String specname, String connectparam, String spectype, String[] cmdId, String[] cmdWord, String[] cmdParam, String[] cmdReturn, String[] cmdFlag) {
        try {
            Dspecification dspecification = new Dspecification();
            dspecification.setDspecName(specname);
            dspecification.setConnectParam(connectparam);
            Dtype dtype = dTypeDao.getTypeById(Integer.valueOf(spectype));
            dspecification.setDtype(dtype);
            specDao.save(dspecification);
            int j = 0;
            for (int i = 0; i < cmdId.length; i++) {
                if (!cmdId[i].equals("") && cmdId[i] != null) {
                    Spec2command spec2command = new Spec2command();
                    spec2command.setCmdFlag(cmdFlag[i]);
                    spec2command.setCmdParam(cmdParam[i]);
                    spec2command.setCmdReturn(cmdReturn[i]);
                    spec2command.setCmdWord(cmdWord[i]);
                    spec2command.setDspecification(dspecification);
                    Command command = new Command();
                    command.setCmdId(Integer.parseInt(cmdId[i]));
                    spec2command.setCommand(command);
                    if (spec2commandDao.save(spec2command)) {

                    } else {
                        j++;
                    }
                }
            }
            if (j >= cmdId.length) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

}
