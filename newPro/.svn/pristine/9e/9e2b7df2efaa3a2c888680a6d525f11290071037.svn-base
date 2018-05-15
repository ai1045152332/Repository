package com.honghe.recordweb.service.frontend.ftp;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by chby on 2014/10/22.
 * // 上传和下载文件时， 监听文件传输的状态
 */
@Service
public class MyTransferListener implements FTPDataTransferListener {
    private final static Logger logger = Logger.getLogger(MyTransferListener.class);

    /**
     * 文件开始上传或下载时触发
     */
    public void started() {
        logger.info("开始上传或下载");
    }

    /**
     * 显示已经传输的字节数
     * @param length
     */
    public void transferred(int length) {
        //  System.out.println("已传输"+length);
    }

    /**
     * 文件传输完成时，触发
     */
    public void completed() {
        logger.info("文件传输完成时触发");
    }


    /**
     * 传输放弃时触发
     */
    public void aborted() {
        // Transfer aborted
        logger.info("传输放弃时触发");
    }

    /**
     * 传输失败时触发
     */
    public void failed() {
        logger.info("传输失败时触发");
    }

}
