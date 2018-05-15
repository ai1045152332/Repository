package com.honghe.recordweb.service.frontend.upload;

import com.honghe.recordweb.action.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by wj on 2015-01-21.
 */
@Controller

@Scope(value = "prototype")
public class UploadAction extends BaseAction {


    /**
     * todo 加注释
     */
    public void upload1(){
        HttpServletRequest request = ServletActionContext.getRequest();

            File myFile = ((MultiPartRequestWrapper) request).getFiles("img")[0];

        File myFile1 = ((MultiPartRequestWrapper) request).getFiles("audio")[0];
       // }
    }
}
