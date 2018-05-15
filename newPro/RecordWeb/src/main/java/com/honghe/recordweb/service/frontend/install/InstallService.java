package com.honghe.recordweb.service.frontend.install;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by wzz on 2015-08-11.
 */
@Service
public class InstallService {
    private final static Logger logger = Logger.getLogger(InstallService.class);
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private SyslogService syslogService;

    /**
     * 读取指定路径下的文件
     *
     * @param path 文件路径
     * @return 文件列表组成的map
     */
    public Map<String, Object> readFile(String path) {
        final String uploadPath = ServletActionContext.getServletContext().getRealPath("/uploadsoft") + path;
        File file = new File(uploadPath);
        if (!file.exists()) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Map<String, String>> fileList = new ArrayList<>();
        // 这个路径就是一个文件
        if (file.isFile()) {
            Map<String, String> fileMap = new HashMap<>();
            fileMap.put("fileName", file.getName());
            fileMap.put("filePath", file.getPath());
            fileMap.put("fileSize", file.length() + "");
            fileMap.put("fileType", "1");
            fileList.add(fileMap);
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("fileName", files[i].getName());
                fileMap.put("filePath", files[i].getPath().substring(files[i].getPath().indexOf("uploadsoft") + 10));
                fileMap.put("fileSize", FormetFileSize(files[i].length()));
                if (files[i].isDirectory()) {
                    fileMap.put("fileType", "0");
                } else {
                    fileMap.put("fileType", "1");
                }
                fileList.add(fileMap);
            }
        }
        map.put("installList", fileList);
        return map;
    }

    /**
     * 读取指定路径下的文件
     *
     * @param path        文件路径
     * @param currentPage 当前页面，从1开始
     * @param pageSize    每页显示数量
     * @return 文件列表和总页数组成的map
     */
    public Map<String, Object> readFile(String path, int currentPage, int pageSize) {
        final String uploadPath = ServletActionContext.getServletContext().getRealPath("/uploadsoft") + path;
        File file = new File(uploadPath);
        if (!file.exists()) {
            return Collections.EMPTY_MAP;
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
        List<Map<String, String>> fileList = new ArrayList<>();
        // 这个路径就是一个文件
        if (file.isFile()) {
            Map<String, String> fileMap = new HashMap<>();
            fileMap.put("fileName", file.getName());
            fileMap.put("filePath", file.getPath());
            fileMap.put("fileSize", file.length() + "");
            fileMap.put("fileType", "1");
            fileList.add(fileMap);
            page.setTotalPageSize(1);
        } else {
            File[] files = file.listFiles();
            //游标的位置 (当前页 - 1) * 页面大小 + 1
            int posion = (currentPage - 1) * pageSize;
            int endData = currentPage * pageSize;
            if (files.length < endData) {
                endData = files.length;
            }
            for (int i = posion; i < endData; i++) {
                Map<String, String> fileMap = new HashMap<>();
                fileMap.put("fileName", files[i].getName());
                fileMap.put("filePath", files[i].getPath().substring(files[i].getPath().indexOf("uploadsoft") + 10));
                fileMap.put("fileSize", FormetFileSize(files[i].length()));
                if (files[i].isDirectory()) {
                    fileMap.put("fileType", "0");
                } else {
                    fileMap.put("fileType", "1");
                }
                fileList.add(fileMap);
            }
            page.setTotalPageSize(files.length);
        }
        map.put("installList", fileList);
        map.put("pageCount", String.valueOf(page.getTotalPageSize()));
        return map;
    }

    /**
     * 转换文件大小
     *
     * @param fileS 文件大小，单位byte
     * @return 文件大小，换算成B、KB、MB等
     */
    private String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("0.0");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 一键安装
     *
     * @param hostIps  要安装的大屏ip
     * @param fileName 软件名称
     * @param filePath 软件路径
     * @return
     */
    public int oneKeyInstallService(String hostIps, String fileName, String filePath, String serverIp, String port) {
        try {
            int j = 0;
            if (hostIps != null && !hostIps.equals("")) {
                String[] ip_array = hostIps.split(":");

                for (String ip : ip_array) {
                    if (filePath != null && !filePath.equals("")) {
                        String[] path_array = filePath.split(":");
                        String[] name_array = fileName.split(":");
                        for (int i = 0; i < path_array.length; i++) {
                            Boolean res = computerCommand.oneKeyInstall(ip, name_array[i], path_array[i], serverIp, port);
                            if (!res) {
                                j++;
                            }
                        }
                    }

                }
            }
            syslogService.addDeviceLog(hostIps, hostIps + "通过一键安装安装了软件:" + fileName + "", "SYSTEM");
            if (j == 0) {
                return 0;
            } else {
                return j;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return -1;
        }
    }

    /**
     * 一键卸载
     *
     * @param hostIps  要卸载的大屏ip
     * @param fileName 软件名称
     * @return
     */
    public int oneKeyuninstallService(String hostIps, String fileName) {
        try {
            int j = 0;
            if (hostIps != null && !hostIps.equals("")) {
                String[] ip_array = hostIps.split(":");

                for (String ip : ip_array) {
                    if (fileName != null && !fileName.equals("")) {
                        String[] name_array = fileName.split(":");
                        for (int i = 0; i < name_array.length; i++) {
                            Boolean res = computerCommand.oneKeyuninstall(ip, name_array[i]);
                            if (!res) {
                                j++;
                            }
                        }
                    }

                }
            }
            syslogService.addDeviceLog(hostIps, hostIps + "通过一键卸载卸载了软件:" + fileName + "", "SYSTEM");
            if (j == 0) {
                return 0;
            } else {
                return j;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return -1;
        }
    }
    /**
     * 上传文件
     * @param file 上传的文件
     * @param fileName 上传的文件名 页面获取
     * @param filePath 上传文件到的路径 固定写死 可在action中修改
     */
    public void uploadFile(File file,String fileName,String filePath) throws Exception{
        File mulu = new File(filePath);
        if (!mulu.exists()){
            mulu.mkdir();
        }
        File local = new File(mulu,fileName);
        FileUtils.copyFile(file,local);
    }
    /**
     * 删除文件
     * @param deleteName 需要删除的所有软件名 需要通过“，”来切割字符串
     * @param filePath  需要删除的文件的文件夹目录 固定写死 可在action中修改
     */
    public void deleteFile(String deleteName,String filePath) throws Exception{
        File mulu = new File(filePath);
        String [] file = deleteName.split(":");
        for (int i = 0;i<file.length;i++){
            File local = new File(mulu,file[i]);
            if (local.exists()){
                local.delete();
            }
        }

    }
}
