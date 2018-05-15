package com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter;

import com.honghe.convert.sdk.ConvertServiceClient;
import com.honghe.recordhibernate.entity.TResourceStatus;
import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordweb.service.frontend.tresource.ResourceStatusFlag;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import com.honghe.recordweb.util.ConfigUtil;
import com.honghe.recordweb.util.base.util.ZipUtil;
import com.honghe.service.client.Result;
import com.honghe.service.client.ServiceClientCallback;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 本类实现excel或word转化为png图片
 */
public class WordOrExcelToPng {
    /**
     * 把word文件转换为pdf文件	 *
     *
     * @param wordFileName
     * word源文件
     * @return 生成pdf文件的路径名称
     */
    public final static Logger logger = Logger.getLogger(WordOrExcelToPng.class);

    public void word2Png(final String wordFileName, final TresourceService resourceManager, final TResourceStatus resourceStatus, final String targetPath) {
        File source = new File(wordFileName);
        final String filename = source.getName().substring(0, source.getName().indexOf("."));
        final String path = source.getPath().replaceAll(source.getName(), "");
        new File(path + filename).mkdir();
        String target = path + filename;
        Integer port = Integer.parseInt(ConfigUtil.get("serviceport"));
        String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        final Map<String, String> params = new HashMap<String, String>();
        params.put("source", wordFileName);
        params.put("target", target);

        Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Document_2_Png, params);
        logger.debug("word2Png :" + wordFileName + "target:" + target + "code:" + result.getCode() + "result:" + result.getValue().toString().equals("true"));
        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                resourceManager.updateByEntiy(resourceStatus);
            } catch (Exception e) {
            }
            return;
        }
        File cssFile = new File(path + filename + "\\1.png ");
        if (cssFile.exists()) {
            params.clear();
            params.put("source", path + filename + File.separator + "1.png");
            params.put("target", path + filename + File.separator + "thumb.png");
            params.put("width", "200");
            result = convertServiceClient.executeT(ConvertServiceClient.ThumbMethod.Image_Thumb, params);
            if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
                try {
                    resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                    resourceManager.updateByEntiy(resourceStatus);
                } catch (Exception e) {
                }
                return;
            }

            try {
                ZipUtil.zipDe(path + filename + ".word.zip", path + filename);
                com.honghe.recordweb.util.base.util.ZipUtil.zipCopy(path + filename + ".word.zip", path + filename + "\\word.zip");
                FileUtil.deleteFile(path + filename + ".word.zip");
            } catch (Exception e) {
                logger.error("word compress or uncompree fail", e);
            }

            try {

                if (resourceManager.isSuccess(wordFileName, "word")) {
                    resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                } else {
                    resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                }
                resourceManager.updateByEntiy(resourceStatus);
                com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = getOfficeInfo(targetPath);
                resourceManager.updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
            } catch (Exception e) {
                logger.error("state change fail", e);
            }
        }


    }

    public void pdf2Png(String pdfFileName, final TresourceService resourceManager, final TResourceStatus resourceStatus, final String targetPath) {

        File source = new File(pdfFileName);
        final String filename = source.getName().substring(0, source.getName().indexOf("."));
        final String path = source.getPath().replaceAll(source.getName(), "");
        new File(path + filename).mkdir();
        String target = path + filename;
        Integer port = Integer.parseInt(ConfigUtil.get("serviceport"));
        String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        final Map<String, String> params = new HashMap<String, String>();
        params.put("source", pdfFileName);
        params.put("target", target);
        Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Document_2_Png, params);
        logger.debug("pdf2Png :" + pdfFileName + "target:" + target + "code:" + result.getCode() + "result:" + result.getValue().toString().equals("true"));
        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            resourceManager.updateByEntiy(resourceStatus);
            return;
        }
        params.clear();
        params.put("source", path + filename + File.separator + "1.png");
        params.put("target", path + filename + File.separator + "thumb.png");
        params.put("width", "200");
        result = convertServiceClient.executeT(ConvertServiceClient.ThumbMethod.Image_Thumb, params);
        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            resourceManager.updateByEntiy(resourceStatus);
            return;
        }
        try {
            ZipUtil.zipDe(path + filename + ".pdf.zip", path + filename);
            com.honghe.recordweb.util.base.util.ZipUtil.zipCopy(path + filename + ".pdf.zip", path + filename + "\\pdf.zip");
            FileUtil.deleteFile(path + filename + ".pdf.zip");
        } catch (Exception e) {
            logger.error("excel compress or uncompree fail", e);
        }
        try {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            resourceManager.updateByEntiy(resourceStatus);
            com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = getOfficeInfo(targetPath);
            resourceManager.updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
        } catch (Exception e) {
            logger.error("state change fail", e);
        }

//		});

    }


    public void excel2Png(final String excelPathString, final TresourceService resourceManager, final TResourceStatus resourceStatus, final String targetPath) {
        File source = new File(excelPathString);
        final String filename = source.getName().substring(0, source.getName().indexOf("."));
        final String path = source.getPath().replaceAll(source.getName(), "");
        new File(path + filename).mkdir();
        String target = path + filename;
        Integer port = Integer.parseInt(ConfigUtil.get("serviceport"));
        String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        final Map<String, String> params = new HashMap<String, String>();
        params.put("source", excelPathString);
        params.put("target", target);
        Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Document_2_Png, params);
        logger.debug("excel2Png :" + excelPathString + "target:" + target + "code:" + result.getCode() + "result:" + result.getValue().toString().equals("true"));
        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                resourceManager.updateByEntiy(resourceStatus);
            } catch (Exception e) {

            }

            return;
        }
        params.clear();
        params.put("source", path + filename + File.separator + "1.png");
        params.put("target", path + filename + File.separator + "thumb.png");
        params.put("width", "200");
        result = convertServiceClient.executeT(ConvertServiceClient.ThumbMethod.Image_Thumb, params);
        if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                resourceManager.updateByEntiy(resourceStatus);
            } catch (Exception e) {

            }
            return;
        }

        try {
            ZipUtil.zipDe(path + filename + ".excel.zip", path + filename);
            com.honghe.recordweb.util.base.util.ZipUtil.zipCopy(path + filename + ".excel.zip", path + filename + "\\excel.zip");

            FileUtil.deleteFile(path + filename + ".excel.zip");
        } catch (Exception e) {
            logger.error("excel compress or uncompree fail", e);
        }


        try {
            if (resourceManager.isSuccess(excelPathString, "excel")) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            } else {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            }
            resourceManager.updateByEntiy(resourceStatus);
            com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = WordOrExcelToPng.getOfficeInfo(targetPath);
            resourceManager.updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
        } catch (Exception e) {
            logger.error("state change fail", e);
        }

    }


    /**
     * 主方法 测试
     */

    public static PicInfo getOfficeInfo(String officePath) {

        StringBuffer srcStringBuffer = new StringBuffer(officePath);

        int dotIndex = srcStringBuffer.lastIndexOf(".");

        StringBuffer path = srcStringBuffer.replace(dotIndex, srcStringBuffer.length(), "/1.png");


        String picPath = path.toString();

        PicInfo picInfo = new PicInfo();

        File _file = new File(picPath);
        if (!(_file.exists())) {

            picInfo.setWidth(1920);
            picInfo.setHeight(1358);
            return picInfo;
        }
        Image src = null;
        try {
            logger.debug(picPath);
            src = javax.imageio.ImageIO.read(_file);
        } catch (IOException e) {

            e.printStackTrace();

            logger.error("读取图片信息失败");

        }
        picInfo.setWidth(src.getWidth(null));
        picInfo.setHeight(src.getHeight(null));

        return picInfo;
    }

    public static void main(String[] args) {
        Integer port = Integer.parseInt(ConfigUtil.get("serviceport"));
        String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        final Map<String, String> params = new HashMap<String, String>();
        params.put("source", "C:\\Users\\shisibihua\\Desktop\\软件开发任务.docx");
        params.put("target", "C:\\Users\\shisibihua\\Desktop");

        convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Document_2_Png, params, new ServiceClientCallback() {
            @Override
            protected void response(Result result) {
                System.out.println(result.getCode() + "---------------------" + result.getValue());
            }
        });

    }
}
