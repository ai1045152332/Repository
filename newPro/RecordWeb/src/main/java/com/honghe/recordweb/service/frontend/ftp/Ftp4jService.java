package com.honghe.recordweb.service.frontend.ftp;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by chby on 2014/10/17.
 * Ftp4j服务
 * Date 2014-10-17
 */
@Component
public class Ftp4jService {
    private final static Logger logger = Logger.getLogger(Ftp4jService.class);
    @Resource
    private MyTransferListener myTransferListener;

    /**
     * 创建FTP连接
     */
    public FTPClient run() {
        logger.info("run the Ftp4j service");
        try {
            FTPClient client = new FTPClient();
            client.connect("127.0.0.1", 21);
            client.login("admin", "qt");
            return client;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("Ftp client connect failed!", e);
            return null;
        }
    }

    /**
     * 传递参数创建FTP连接
     *
     * @param ftpUrl   主机名IP
     * @param port     端口
     * @param user     用户名
     * @param password 密码
     */
    public FTPClient run(String ftpUrl, int port, String user, String password) {
        if (user == null) {
            user = "";
        }
        if (password == null) {
            password = "";
        }
        logger.info("run the Ftp4j service");
        try {
            FTPClient client = new FTPClient();
            client.connect(ftpUrl, port);
            client.login(user, password);
            return client;
        } catch (Exception e) {
            logger.error("Ftp client connect failed!", e);
            return null;
        }
    }

    //退出FTp链接
    public void disconnect(FTPClient client, boolean safe) {
        if (client != null) {
            if (client.isConnected()) {
                try {
                    client.disconnect(false); //断开连接
                    logger.info("  disconnect ftp!");
                } catch (Exception e) {
                    logger.error(" Ftp client disconnect failed!", e);
                }
            }
        }
    }

    /**
     * 获取路径
     * @return
     */
    public String getFileDirStr() {
        FTPClient client = run();
        try {
            String fileDirStr = client.currentDirectory();
            logger.info(fileDirStr);
            return fileDirStr;
        } catch (Exception e) {
            logger.error("Ftp getfiledirstr failed", e);
            return "";
        }
    }

    /**
     * 获取当前目录文件列表
     * @param client
     * @param path
     * @param format
     * @return
     */
    public FTPFile[] getFiles(FTPClient client, String path, String format) {
        FTPFile[] list;
        try {
            if (!path.isEmpty() && !path.equals("")) {
                client.changeDirectory(path);
            }

            if (!format.isEmpty() && !format.equals(""))//未实现
            {
                list = client.list(format);
            } else {
                list = client.list();
            }
            return list;
        } catch (Exception e) {
            logger.error("Ftp get files failed!!", e);
            return null;
        }
    }

    /**
     * 获取文件名称列表
     *
     * @param client
     * @param dir
     * @return
     */
    public String[] getFileNames(FTPClient client, String dir) {
        String[] fileNames;
        try {
            if (!dir.isEmpty() && !dir.trim().equals("")) {
                client.changeDirectory(dir);
            }
            fileNames = client.listNames();
            Arrays.sort(fileNames, new Comparator<String>() {
                public int compare(String val1, String val2) {
                    return val1.compareToIgnoreCase(val2);
                }
            });
            return fileNames;
        } catch (Exception e) {
            logger.error("listnames failed!!!", e);
            return null;
        }
    }

    /**
     * 下载文件
     *
     * @param client      FTP客户端
     * @param fileName    下载的文件名称
     * @param newFilePath 下载到本地的文件名称
     * @return
     */
    public boolean downloadFile(FTPClient client, String fileName, String newFilePath) {
        try {
            File file = new File(newFilePath);
            client.download(fileName, file);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("download the file failed!!", e);
            return false;
        }
    }

    /**
     * 下载文件夹
     *
     * @param client        FTP客户端
     * @param folderName    下载的文件夹名称
     * @param newFolderName
     * @return
     */
    public boolean downloadFolder(FTPClient client, String folderName, String newFolderName) {
        try {
            client.setType(FTPClient.TYPE_BINARY);
            FTPFile[] ftpFiles = client.list(folderName);
            File f = new File(newFolderName);
            if (!f.exists() && !f.isDirectory()) {
                logger.info("can not find the dir " + newFolderName + "create it");
                f.mkdir();
            }
            for (FTPFile ftpFile : ftpFiles) {
                String name = ftpFile.getName();
                if (!name.trim().equals(".") && !name.trim().equals("..")) {
                    if (ftpFile.getType() == FTPFile.TYPE_FILE) {
                        client.changeDirectory(folderName);
                        logger.info("准备下载" + newFolderName + "\\" + name);
                        client.download(name, new File(newFolderName + "\\" + name));
                        logger.info("下载完毕" + newFolderName + "\\" + name);
                    } else if (ftpFile.getType() == FTPFile.TYPE_DIRECTORY) {
                        downloadFolder(client, folderName + "\\" + name, newFolderName + "\\" + name);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            logger.error("download the folder failed!!", e);
            return false;
        } finally {
            this.disconnect(client, true);
        }
    }

    /**
     * 上传文件
     *
     * @param client     ftp客户端
     * @param fileName   要上传文件的全路径名称 d://a/b.txt
     * @param folderPath 上传目标文件夹
     * @return
     */
    public boolean uploadFile(FTPClient client, String fileName, String folderPath) {
        boolean flag = false;
        try {
            String[] dirs = folderPath.split("/");
            for (int i = 0; i < dirs.length; i++) {
                String _folderPath = dirs[i];
                if (!this.exists(client, dirs[i])) {
                    client.createDirectory(dirs[i]);
                }
                client.changeDirectory(_folderPath);
            }

            File f = new File(fileName);
            if (f.exists()) {
                client.upload(f, myTransferListener);
                flag = true;
            }

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            this.disconnect(client, true);
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param client     ftp客户端
     * @param fileName   要上传文件的全路径名称 d://a/b.txt
     * @param folderPath 上传目标文件夹
     * @return
     */
    public boolean uploadFile(FTPClient client, String fileName, String folderPath, MyTransferListener mtl) {
        boolean flag = false;
        if (mtl == null) {
            mtl = myTransferListener;
        }
        try {
            String[] dirs = folderPath.split("/");
            for (int i = 0; i < dirs.length; i++) {
                String _folderPath = dirs[i];
                if (!this.exists(client, dirs[i])) {
                    client.createDirectory(dirs[i]);
                }
                client.changeDirectory(_folderPath);
            }
            File f = new File(fileName);
            if (f.exists()) {
                client.upload(f, mtl);
                flag = true;
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            this.disconnect(client, true);
        }

        return flag;
    }

    /**
     * 判断文件或目录是否存在
     *
     * @param client FTP客户端对象
     * @param dir    文件或目录
     * @return
     * @throws Exception
     */
    private boolean exists(FTPClient client, String dir) throws Exception {
        return getFileType(client, dir) != -1;
    }

    /**
     * 判断当前为文件还是目录
     *
     * @param client FTP客户端对象
     * @param dir    文件或目录
     * @return -1、文件或目录不存在 0、文件 1、目录
     * @throws Exception
     */
    private int getFileType(FTPClient client, String dir) throws Exception {
        FTPFile[] files = null;
        try {
            files = client.list(dir);
        } catch (Exception e) {
            return -1;
        }
        if (files.length > 1) {
            return FTPFile.TYPE_DIRECTORY;
        } else if (files.length == 1) {
            FTPFile f = files[0];
            if (f.getType() == FTPFile.TYPE_DIRECTORY) {
                return FTPFile.TYPE_DIRECTORY;
            }
            String path = dir + "/" + f.getName();
            try {
                int len = client.list(path).length;
                if (len == 1) {
                    return FTPFile.TYPE_DIRECTORY;
                } else {
                    return FTPFile.TYPE_FILE;
                }
            } catch (Exception e) {
                return FTPFile.TYPE_FILE;
            }
        } else {
            try {
                client.changeDirectory(dir);
                client.changeDirectoryUp();
                return FTPFile.TYPE_DIRECTORY;
            } catch (Exception e) {
                logger.error("", e);
                return -1;
            }
        }
    }

    /**
     * 上传文件夹
     *
     * @param client
     * @param fileFolder
     * @param folderPath
     * @return
     */
    public boolean uploadFolder(FTPClient client, String fileFolder, String folderPath) {
        try {
            File file = new File(fileFolder);
            String ss = file.getName(); //对应文件夹的名称
            if (!file.isDirectory()) {
                client.changeDirectory(folderPath);
                client.upload(file, new MyTransferListener());
            } else if (file.isDirectory()) {
                client.changeDirectory(folderPath);
                client.createDirectory(ss);
                client.changeDirectory(ss);
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(fileFolder + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        client.upload(readfile, new MyTransferListener());
                    } else if (readfile.isDirectory()) {
                        uploadFolder(client, fileFolder + "\\" + ss + "\\" + filelist[i], folderPath + "/" + ss + "/" + filelist[i]);
                    }
                }

            }
            return true;

        } catch (Exception e) {
            logger.error("", e);
            return false;
        } finally {
            this.disconnect(client, true);
        }
    }

    /**
     * 返回上一级
     *
     * @param client
     * @return
     */
    public boolean backUp(FTPClient client) {
        try {
            client.changeDirectoryUp();
            return true;
        } catch (Exception e) {
            logger.error("back up failed！！", e);
            return false;
        }
    }

    /**
     * 重命名
     *
     * @param client  FTP客户端
     * @param oldName 旧名字
     * @param newName 新名字
     * @return
     */
    public boolean rename(FTPClient client, String oldName, String newName) {
        try {
            client.rename(oldName, newName);
            return true;
        } catch (Exception e) {
            logger.error("rename failed!!", e);
            return false;
        }
    }

    /**
     * 删除文件
     *
     * @param client
     * @param fileName
     * @param flag
     * @return
     */
    public boolean deleteFile(FTPClient client, String fileName, String flag) {
        try {
            if (flag == null || flag.equals("file") || flag.equals("")) {
                client.deleteFile(fileName); //删除文件
            } else {
                client.deleteDirectory(fileName);//删除文件夹,只允许删除空白文件夹
            }
            return true;
        } catch (Exception e) {
            logger.error("delete file failed!!!", e);
            return false;
        } finally {
            this.disconnect(client, true);
        }
    }
}
