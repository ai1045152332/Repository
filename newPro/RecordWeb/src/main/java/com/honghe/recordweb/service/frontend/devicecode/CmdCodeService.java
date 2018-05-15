package com.honghe.recordweb.service.frontend.devicecode;

import com.honghe.recordhibernate.dao.CmdCodeDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.CommandCode;
import com.honghe.recordhibernate.entity.Dspecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sky on 2015/8/22.
 */
@Service
public class CmdCodeService {
    @Resource
    CmdCodeDao cmdcodeDao;
    /**
     * 分页获取命令列表
     * @param currentpage
     * @param pagesize
     * @return
     */


    /**
     * 根据cmdCodeId获取commandCode信息
     * @param cmdCodeId
     * @return CommandCode
     */
    public CommandCode getCodeInfoById(int cmdCodeId)
    {
        try{
            return cmdcodeDao.getCommandCodeInfo(cmdCodeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取命令
     * @param currentpage 当前页
     * @param pagesize 总页数
     * @return
     */
    public Map<String,Object> getDcodeList(int currentpage,int pagesize)
    {
        try {
            Page<List<Object[]>> page=new Page<List<Object[]>>(currentpage,pagesize);
            List result=cmdcodeDao.getDeviceCodeList(page);
            Map<String,Object> map=new LinkedHashMap<String, Object>();
            map.put("codelist",result);
            map.put("page",page);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过设备名称获取编码
     * @param dspecname 设备名称
     * @param cmdname 命令名称
     * @param currentPage 当前页
     * @param pageSize 总页数
     * @return
     */
    public Map<String,Object> getCodeByName(String dspecname,String cmdname,int currentPage,int pageSize)
    {
        Map<String,Object> map=new LinkedHashMap<String, Object>();
        try
        {
            Page<List<Object[]>> page=new Page<List<Object[]>>(currentPage,pageSize);
            List<Object[]> result=null;
            if (!dspecname.equals("")){
                if (cmdname.equals("")) {
                    result = cmdcodeDao.getCodeBydespName(dspecname, page);
                }else{
                    result=cmdcodeDao.getCodeByname(dspecname,cmdname,page);
                }
            } else {
                 if (!cmdname.equals("")){
                     result=cmdcodeDao.getCodeBycmdName(cmdname,page);
                 }else {
                     result=cmdcodeDao.getDeviceCodeList(page);
                 }
            }
                map.put("codelist", result);
                map.put("page", page);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 通过命令名称获取设备名称
     * @param dspecname
     * @return
     */
    @Transactional
    public List getCmdnameBydspec(String dspecname){
        List result=null;
        try{
            result=cmdcodeDao.getCmdNameBydespc(dspecname);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取命令列表
     * @return
     */
    @Transactional
    public Map<String, Object> getCmdNameList()
    {
        Map<String,Object> map=new LinkedHashMap<String, Object>();
        try {
            List<Object[]> result=cmdcodeDao.getCmdName();
            map.put("codeadd",result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
//    @Transactional
//    public List getCmdGroupList()

    /**
     * 获取设备名称
     * @return
     */
    @Transactional
    public Map<String,Object>getDspeName()
    {
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        try{
            List<Object[]> result=cmdcodeDao.getDspeName();
            map.put("codeadd",result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 添加设备
     * 以下为编码属性
     * @param codeName
     * @param codeType
     * @param codeRemark
     * @param codeInstruction
     * @param codeCode
     * @param codeFlag
     * @param codeGroup
     * @param dspecName
     * @param dspecId
     * @return
     */
    @Transactional
    public Boolean  codeAdd(String codeName, String codeType, String codeRemark, String codeInstruction, String codeCode, String codeFlag, String codeGroup,String dspecName,Integer dspecId)
    {
        try
        {
            CommandCode commandCode=new CommandCode();
            commandCode.setCodeName(codeName);
            commandCode.setCodeType(codeType);
            commandCode.setCodeRemark(codeRemark);
            commandCode.setCodeInstruction(codeInstruction);
            commandCode.setCodeCode(codeCode);
            commandCode.setCodeFlag(codeFlag);
            commandCode.setCodeGroup(codeGroup);
            Dspecification dspecification = new Dspecification();
            dspecification.setDspecName(dspecName);
            dspecification.setDspecId(dspecId);
            commandCode.setDspecification(dspecification);
            return cmdcodeDao.save(commandCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除
     * @param codeid 编码id
     * @return
     */
    @Transactional
    public boolean delcode(int codeid)
    {
        try{
            return cmdcodeDao.delete(codeid);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存
     * @param codeId
     * @param codeName
     * @param codeType
     * @param codeRemark
     * @param codeInstruction
     * @param codeCode
     * @param codeFlag
     * @param codeGroup
     * @param dspecName
     * @param dspecId
     * @return
     */
    @Transactional
    public boolean codeUpdate(int codeId,String codeName,String codeType,String codeRemark,String codeInstruction,String codeCode,String codeFlag,String codeGroup,String dspecName,Integer dspecId )
    {
        try {
            CommandCode commandCode=cmdcodeDao.getCommandCodeInfo(codeId);
            Dspecification dspecification = new Dspecification();
            commandCode.setCodeId(codeId);
            commandCode.setCodeName(codeName);
            commandCode.setCodeType(codeType);
            commandCode.setCodeRemark(codeRemark);
            commandCode.setCodeInstruction(codeInstruction);
            commandCode.setCodeCode(codeCode);
            commandCode.setCodeFlag(codeFlag);
            commandCode.setCodeGroup(codeGroup);
            dspecification.setDspecName(dspecName);
            dspecification.setDspecId(dspecId);
            commandCode.setDspecification(dspecification);
            boolean result = cmdcodeDao.updata(commandCode);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
