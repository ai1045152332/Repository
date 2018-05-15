package com.honghe.recordweb.action.frontend.tresource;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/27
 */
@Controller
@Scope(value = "Prototype")
public class TresourceUploadByUploadifyAction extends BaseAction{

    @Resource
    private TresourceService tresourceService;
    private File file;
    private String fileFileName;

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static int getVerifyStatusFlag() {
        return verifyStatusFlag;
    }

    public static void setVerifyStatusFlag(int verifyStatusFlag) {
        TresourceUploadByUploadifyAction.verifyStatusFlag = verifyStatusFlag;
    }

    public static int verifyStatusFlag=0;

    private String pathId;

    public static Set<String> getCatalogSet() {
        return catalogSet;
    }

    public static void setCatalogSet(Set<String> catalogSet) {
        TresourceUploadByUploadifyAction.catalogSet = catalogSet;
    }

    public  static Set<String> catalogSet = new HashSet<String>();

    /**
     * todo 加注释
     */
    public void md5Validate(){
        try {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            String userId = user.getUserId().toString();
            String loginName = user.getUserName().toString();
            String path = ServletActionContext.getServletContext().getRealPath("/msgResource") + "/data/resources/";
            tresourceService.savemd5Upload(pathId, fileFileName, file, userId, true, null, path,loginName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
