
package com.honghe.recordweb.util.base.util.ppt2htmlconverter;

import com.honghe.convert.sdk.ConvertServiceClient;
import com.honghe.recordhibernate.entity.TResourceStatus;
import com.honghe.recordweb.service.frontend.tresource.ResourceStatusFlag;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import com.honghe.recordweb.util.ConfigUtil;
import com.honghe.recordweb.util.base.util.FileUtil;
import com.honghe.recordweb.util.base.util.ZipUtil;
import com.honghe.service.client.Result;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A ppt2html5 process wrapper.
 *
 * @author JunChen
 */
public class PPT2HTML {

    public static Logger logger = Logger.getLogger(PPT2HTML.class);

    public PPT2HTML() {

    }

    /**
     * Executes the ppt2html5 process with the previous given arguments.
     *
     * @throws java.io.IOException If the process call fails.
     */
    public void convert(String sourceFile, final TresourceService resourceManager, final TResourceStatus resourceStatus, final String targetPath) {

        File source = new File(sourceFile);
        final String filename = source.getName().substring(0, source.getName().indexOf("."));
        final String path = source.getPath().replaceAll(source.getName(), "");
        new File(path + filename).mkdir();
        final String target = path + filename;
        //转换begin
        Integer port = Integer.parseInt(ConfigUtil.get("serviceport"));
        String serviceIp = ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        Map<String, String> params = new HashMap<String, String>();
        params.put("source", sourceFile);
        params.put("target", target);
        Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Document_2_Png, params);

        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            resourceManager.updateByEntiy(resourceStatus);
            return;
        }
        //转换end
        File cssFile = new File(path + filename + "\\1.png ");
        if (cssFile.exists()) {
            try {
                IOUtils.copy(new FileInputStream(cssFile), new FileOutputStream(target + File.separator + "thumb.png"));
            } catch (Exception e) {
                logger.error("ppt 缩略图失败", e);
            }
        }


        try {
            ZipUtil.zipDe(path + filename + ".ppt.zip", path + filename);
            com.honghe.recordweb.util.base.util.ZipUtil.zipCopy(path + filename + ".ppt.zip", path + filename + "\\ppt.zip");
            FileUtil.deleteFile(path + filename + ".ppt.zip");
        } catch (Exception e) {
           logger.error("ppt压缩包复制并删除原压缩包失败！",e);
        }

        try {
            if (resourceManager.hasPPtTransferingFlag == 1) {
                resourceManager.hasPPtTransferingFlag = 0;
            }
            if (resourceManager.isSuccess(targetPath, "ppt")) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            } else {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            }
            resourceManager.updateByEntiy(resourceStatus);
        } catch (Exception e) {

            logger.error("state change fial", e);
        }

    }
}
