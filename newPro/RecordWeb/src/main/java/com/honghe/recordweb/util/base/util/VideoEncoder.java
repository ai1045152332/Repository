package com.honghe.recordweb.util.base.util;

import com.honghe.convert.sdk.ConvertServiceClient;
import com.honghe.recordhibernate.entity.TResourceStatus;
import com.honghe.recordweb.service.frontend.tresource.ResourceStatusFlag;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import com.honghe.service.client.Result;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class VideoEncoder {

    public VideoEncoder() {

    }

    public void encode(String sourceFile, boolean isH264,  TresourceService resourceManager,  TResourceStatus resourceStatus) {
        final File source = new File(sourceFile);
        final String filename = source.getName().substring(0, source.getName().indexOf("."));
        final String path = source.getPath().replaceAll(source.getName(), "");
        new File(path + filename).mkdir();
        Integer port = Integer.parseInt(com.honghe.recordweb.util.ConfigUtil.get("serviceport"));
        String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
        final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
        final Map<String, String> params = new HashMap<String, String>();
        params.put("source", sourceFile);
        params.put("target", path + filename);
        if (sourceFile.contains(".mp4") && isH264) {
//            new Thread(new Runnable() {
//                public void run() {
                    Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.MediaScreenShot, params);
                    if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                        return;
                    }
                    FileUtil.copyFile(source, new File(path + filename + File.separator + "video.mp4"));
                    resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    try {
                        resourceManager.updateByEntiy(resourceStatus);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
//            }).start();

        } else {
            params.put("fileName", "video");
//            new Thread(new Runnable() {
//                public void run() {
                    Result result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Meida_2_MP4, params);
                    if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                        resourceManager.updateByEntiy(resourceStatus);
                        return;
                    }
                    resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    try {
                        resourceManager.updateByEntiy(resourceStatus);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
//            }).start();


//            convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Meida_2_MP4, params, new ServiceClientCallback() {
//                @Override
//                protected void response(Result result) {
//                    if (result.getCode() != 0 || result.getValue().toString().equals("false")) {
//                        resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
//                    } else {
//                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
//                    }
//                    try {
//                        resourceManager.updateByEntiy(resourceStatus);
//                    } catch (Exception e) {
//
//                    }
//                }
//            });
        }
    }

}
