package com.honghe.recordweb.service.frontend.tresource;

import com.honghe.convert.sdk.ConvertServiceClient;
import com.honghe.recordhibernate.dao.TResouceStatusDao;
import com.honghe.recordhibernate.dao.TResourceCatalogDao;
import com.honghe.recordhibernate.dao.TResourceDao;
import com.honghe.recordhibernate.dao.TResourceIndexDao;
import com.honghe.recordhibernate.entity.TResource;
import com.honghe.recordhibernate.entity.TResourceCatalog;
import com.honghe.recordhibernate.entity.TResourceIndex;
import com.honghe.recordhibernate.entity.TResourceStatus;
import com.honghe.recordhibernate.entity.form.ResourceForm;
import com.honghe.recordhibernate.entity.tools.ConfigUtil;
import com.honghe.recordhibernate.entity.tools.FileUtil;
import com.honghe.recordhibernate.entity.tools.GlobalParameter;
import com.honghe.recordweb.action.frontend.tresource.TresourceUploadByUploadifyAction;
import com.honghe.recordweb.util.base.util.ListQueue;
import com.honghe.recordweb.util.base.util.MD5;
import com.honghe.recordweb.util.base.util.StringUtil;
import com.honghe.recordweb.util.base.util.VideoEncoder;
import com.honghe.recordweb.util.base.util.picconverter.PicConverter;
import com.honghe.recordweb.util.base.util.picconverter.PicInfo;
import com.honghe.recordweb.util.base.util.ppt2htmlconverter.PPT2HTML;
import com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.WordOrExcelToPng;
import com.honghe.service.client.Result;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/26
 */
@Service
public class TresourceService {
    private final static Logger logger = Logger.getLogger(TresourceService.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    @Resource
    private TResourceCatalogDao tResourceCatalogDao;
    @Resource
    private TResourceIndexDao tResourceIndexDao;
    @Resource
    private TResourceDao tResourceDao;
    @Resource
    private TResouceStatusDao tResouceStatusDao;
    private String virtualForUpload;
    public static int hasPPtTransferingFlag = 0;
    public Set<String> catalogSet = new HashSet<String>();
    File target = null;

    public static int getWhichTransferWay() {
        return whichTransferWay;
    }

    public static LinkedListQueue transferQueue = new LinkedListQueue();

    public static void setWhichTransferWay(int whichTransferWay) {
        TresourceService.whichTransferWay = whichTransferWay;
    }

    public static ListQueue secondtransferQueue = new ListQueue();
    private static int whichTransferWay = Integer.parseInt(ConfigUtil.get("TransferWay"));
    //public static LinkedListQueue threadQueue = new LinkedListQueue();
    //private static int MAX_TRANSFER_CNT = Integer.parseInt(ConfigUtil.get("resourceThreadCnt"));

    //private String ResourcePathRoot= ServletActionContext.getServletContext().getRealPath("/msgResource")+ "/data/resources/";
    //获得资源路径
    public List<ResourceForm> getResourceTree() {
        try {
            //查询所有目录
            String hql = "from TResourceCatalog r where r.type='0' and r.delFlag= 0 ";
            List<ResourceForm> voList = new ArrayList<ResourceForm>();
            List<TResourceCatalog> poList = tResourceCatalogDao.getResourceCatalogsBySql(hql);
            if (poList.size() > 0) {
                for (TResourceCatalog resourceCatalog : poList) {
                    ResourceForm resourceForm = new ResourceForm();
                    resourceForm.setId(resourceCatalog.getId() + "");
                    resourceForm.setPid(resourceCatalog.getFid() + "");
                    resourceForm.setName(resourceCatalog.getName());
                    resourceForm.setPath(resourceCatalog.getVirtual());
                    voList.add(resourceForm);
                }
                return voList;
            } else {
                return voList;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获得资源路径异常", e);
            return new ArrayList<>();
        }
    }

    /**
     * todo 加注释
     * @param virtual
     * @param virtualPreView
     * @return
     */
    public List<TResourceCatalog> findByProperty(String virtual, Object virtualPreView) {
        try {
            return tResourceCatalogDao.findByProperty(virtual, virtualPreView);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return new ArrayList<>();
        }
    }

    /**
     * todo 加注释
     * @param propertyname
     * @param value
     * @return
     */
    public List<TResourceCatalog> findByPropertyWidthOrder(String propertyname, Object value) {
        try {
            return tResourceCatalogDao.findByPropertyWidthOrder(propertyname, value);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return new ArrayList<>();
        }
    }

    /**
     * todo 加注释
     * @param type
     * @param name
     * @param virtual
     * @return
     */
    public List<TResourceCatalog> findByQueryWithOrder(String type, String name, String virtual) {
        try {
            return tResourceCatalogDao.findByQueryWithOrder(type, name, virtual);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return new ArrayList<>();
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public List<Object[]> findById(int id) {
        try {
            return tResourceIndexDao.findById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public TResource findTresourceById(int id) {
        try {
            return tResourceDao.findById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public List<Object[]> findTresourceStatusById(int id) {
        try {
            return tResouceStatusDao.findById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public TResourceIndex findTResourceIndexById(int id) {
        try {
            return tResourceIndexDao.findIndexById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public TResourceCatalog findByCatalogId(int id) {
        try {
            return tResourceCatalogDao.findById(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @param newName
     * @return
     */
    @Transactional
    public boolean editFileNameAndSubFile(int id, String newName) {
        try {
            TResourceCatalog resourceCatalog = findByCatalogId(id);
            String oldName = resourceCatalog.getName();
            Boolean isExist = queryByNameAndFid(newName, resourceCatalog.getFid());
            if (isExist) {
                resourceCatalog.setName(newName);
                //修改文件名字
                saveResourceCatalog(resourceCatalog);
                //如果是文件夹，刚修改原文件夹下的文件所属位置
                updateVirtual(id, newName, oldName);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @param newName
     * @param oldName
     */
    @Transactional
    public void updateVirtual(int id, String newName, String oldName) {
        try {
            Boolean items = tResourceCatalogDao.getItemByFid(id);
            if (items) {
                tResourceCatalogDao.updateFileVirtual(id, newName, oldName);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param tResourceCatalog
     */
    @Transactional
    public void saveResourceCatalog(TResourceCatalog tResourceCatalog) {
        try {
            tResourceCatalogDao.saveOrUpdate(tResourceCatalog);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param name
     * @param fid
     * @return
     * @throws Exception
     */
    public boolean queryByNameAndFid(String name, Integer fid) throws Exception {
        String hql = "from TResourceCatalog r where r.delFlag= 0 and r.name = '" + name + "' and r.fid = " + fid;
        List list = tResourceCatalogDao.getResourceCatalogsBySql(hql);
        if (list.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * todo 加注释
     * @param fileFormat
     * @param id
     * @return
     * @throws Exception
     */
    public List<TResourceCatalog> getResourceCatalogByFormat(String fileFormat, int id) throws Exception {
        String sql = "FROM TResourceCatalog WHERE delFlag=0 and fid='" + id + "'and format='" + fileFormat + "'";
        return tResourceCatalogDao.getResourceCatalogsBySql(sql);// .findByProperty("format", fileFormat);
    }

    /**
     * todo 加注释
     * @param str
     * @return
     */
    public boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * todo 加注释
     * @param pathId
     * @param fileFileName
     * @param file
     * @param userId
     * @param needturn
     * @param md5folder
     * @param ResourcePathRoot
     * @param loginName
     * @throws Exception
     */
    @Transactional
    public void savemd5Upload(String pathId, String fileFileName, File file, String userId, boolean needturn, String md5folder, String ResourcePathRoot, String loginName) throws Exception {
        if (TresourceUploadByUploadifyAction.verifyStatusFlag == 0) {
//            if(sys.verifyResourceStatus()){
//            TresourceUploadByUploadifyAction.verifyStatusFlag=1;
//            }else{
            TresourceUploadByUploadifyAction.verifyStatusFlag = 2;
            //   }
        }
        TResourceCatalog resourceCata = tResourceCatalogDao.findById(Integer.parseInt(pathId));
        String childVirtual = null;
        if ("公共资源".equals(resourceCata.getName())) {
            childVirtual = "root";
        } else {
            childVirtual = resourceCata.getName();
        }
        virtualForUpload = resourceCata.getVirtual() + childVirtual + "/";
        MD5 md5 = new MD5();
        int lastDotIndex = fileFileName.lastIndexOf(".");
        String fileName = fileFileName.substring(0, lastDotIndex);
        fileName = fileName.replace(" ", "_").replace(".", "_");
        fileName = fileName.length() > 50 ? fileName.substring(0, 50) : fileName;
        String fileFormat = fileFileName.substring(lastDotIndex + 1);
        String nameForMd5 = md5.getFileMD5(file);
        catalogSet = new HashSet<String>();
        synchronized (this) {
            List<TResourceCatalog> cataList = getResourceCatalogByFormat(fileFormat, Integer.parseInt(pathId));
            for (TResourceCatalog aCata : cataList) {
                String afileName = aCata.getName() + "." + aCata.getFormat();
                catalogSet.add(afileName);
            }
            for (int i = 0; i < 1000; i++) {
                String newName = i == 0 ? fileName : (fileName.length() > 46 ? fileName.substring(0, 46) + "-" + i : fileName + "-" + i);
                String newFileName = newName + "." + fileFormat;
                if (!catalogSet.contains(newFileName)) {
                    catalogSet.add(newFileName);
                    fileName = newName;
                    break;
                }
            }
        }
        String[] splitname = {fileName, fileFormat};
        List<TResourceCatalog> resourceCatalog = findByProperty("md5", nameForMd5);
        fileFileName = nameForMd5 + "." + splitname[1];
        String maxNumStr = ConfigUtil.get("resourceFileNum");
        int maxNum = Integer.parseInt(maxNumStr);
        try {
            String targetDirectory = "";
            synchronized (this) {
                //分目录---每个资源目录下最多存放1000个文件（不包含文件夹）
                File resouresPath = new File(ResourcePathRoot);
                if (!resouresPath.exists()) {
                    resouresPath.mkdirs();
                }
                File txtFile = new File(ResourcePathRoot + "num.txt");
                if (!txtFile.exists()) {
                    PrintWriter pws = new PrintWriter(txtFile);
                    pws.print(0);
                    pws.flush();
                    pws.close();
                }
                try {
                    BufferedReader br = new BufferedReader(new FileReader(txtFile));
                    String tempNumStr = br.readLine();
                    br.close();
                    PrintWriter pw = new PrintWriter(new FileWriter(txtFile));
                    int temNum = 0;
                    if (tempNumStr != "" && tempNumStr != null) {
                        temNum = Integer.parseInt(tempNumStr);
                    }
                    int max = 0;
                    File fileRoot = new File(ResourcePathRoot);
                    File[] files = fileRoot.listFiles();
                    for (File f : files) {
                        if (f.isDirectory()) {
                            String name = f.getName();
                            if (isNum(name)) {
                                int temp = Integer.parseInt(name);
                                max = (temp > max ? temp : max);
                            }

                        }
                    }
                    String targetDirectory2 = "";
                    if (temNum >= maxNum) {
                        File f = new File(ResourcePathRoot + (max + 1) + "/");
                        f.mkdirs();
                        targetDirectory2 = ResourcePathRoot + (max + 1) + "/";
                        pw.println(1);
                    } else {
                        targetDirectory2 = ResourcePathRoot + (max) + "/";
                        pw.println(temNum + 1);
                    }
                    pw.flush();
                    pw.close();
                    targetDirectory = targetDirectory2;
                } catch (Exception e) {
//                    e.printStackTrace();
                    logger.error("", e);
                }
            }
            //分目录end
            String targetFileName = fileFileName;
            target = new File(targetDirectory, targetFileName);
            FileUtils.copyFile(file, target);
            if (!needturn) {
                //copy md5文件夹
                File fileSrc = new File(md5folder + nameForMd5);
                File folderFile = new File(targetDirectory + targetFileName.substring(0, targetFileName.indexOf(".")) + "/");
                folderFile.mkdirs();
                if (fileSrc.listFiles() == null) {
                    needturn = true;
                } else {
                    FileUtil.copyFolder(fileSrc.getAbsolutePath(), targetDirectory + targetFileName.substring(0, targetFileName.indexOf(".")) + "/");
                }
            }
            //保存数据库
            String userName = loginName;
            String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
            String imgType = "jpg,jpeg,png,bmp,gif";
            String wordType = "docx,doc";
            String excelType = "xlsx,xls";
            String pdfType = "pdf";
            String pptType = "pptx,ppt,pps";
            String flashType = "swf";
            int type = 0;
            String suffix = splitname[1].toLowerCase();
            int vValue = videoType.indexOf(suffix);
            if (vValue != -1) {
                type = 1;
            }
            int wValue = wordType.indexOf(suffix);
            if (wValue != -1) {
                type = 2;
            }
            int eValue = excelType.indexOf(suffix);
            if (eValue != -1) {
                type = 3;
            }
            int imgValue = imgType.indexOf(suffix);
            if (imgValue != -1) {
                type = 4;
            }
            int pValue = pdfType.indexOf(suffix);
            if (pValue != -1) {
                type = 5;
            }
            int pptValue = pptType.indexOf(suffix);
            if (pptValue != -1) {
                type = 6;
            }
            int swfValue = flashType.indexOf(suffix);
            if (swfValue != -1) {
                type = 7;
            }
            TResourceStatus resourceStatus = null;
            boolean normalType = true;
            boolean isH264 = false;
//            String name = splitname[0];
//            String format = splitname[1];
//            int size = (int) (file.length() / 1024);
//            int fid = Integer.parseInt(pathId);
//            String[] path = target.getPath().split("msgResource");
//            String webpath = ServletActionContext.getServletContext().getContextPath() + "msgResource/" + path[1];
            switch (type) {
                case 1: { //video
                    //status = uploadVideo(normalType, name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    Integer port = Integer.parseInt(com.honghe.recordweb.util.ConfigUtil.get("serviceport"));
                    String serviceIp = com.honghe.recordweb.util.ConfigUtil.get("serviceip");
                    final ConvertServiceClient convertServiceClient = new ConvertServiceClient(serviceIp, port);
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("source", target.getPath());
                    Result result;
                    int duration = 0;
                    int length = 0;
                    int width = 0;
                    result = convertServiceClient.executeC(ConvertServiceClient.ConvertMethod.Media_GetInfo, params);  //mediaGetInfo
                    Map<String, Object> info = (Map<String, Object>) result.getValue();
                    if (result.getCode() != 0 || info.isEmpty()) {
                        duration = 0;
                        length = 0;
                        width = 0;
                        normalType = false;
                        isH264 = false;
                    } else {
                        duration = ((Integer) info.get("duration")).intValue();
                        length = (Integer) info.get("height");
                        width = (Integer) info.get("width");
                        isH264 = info.get("decoder").toString().startsWith("h264");
                    }
                    TResource resource = new TResource();
                    resourceStatus = new TResourceStatus();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(1);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }

                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(1);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setDuration(duration);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(length);
                    resource.setWidth(width);
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");

                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }
                    if (!normalType) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Illegal.getFlag());
                    }
                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 2: {//doc
                    //status = uploadDoc(name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    TResource resource = new TResource();
                    resourceStatus = new TResourceStatus();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(2);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(2);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(1);
                    resource.setWidth(1);
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }

                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 3: {//excel
                    // status = uploadExcel(name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    TResource resource = new TResource();
                    resourceStatus = new TResourceStatus();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(3);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(3);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(1);
                    resource.setWidth(1);
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }

                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 4: {//img
                    //status = uploadImg(normalType, name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    PicInfo ImageInfo = null;
                    Integer length = null;
                    Integer width = null;
                    try {
                        ImageInfo = PicConverter.imageInfo(target.getPath());
                        length = ImageInfo.getHeight();
                        width = ImageInfo.getWidth();


                    } catch (Exception e) {
                        normalType = false;
                        length = 0;
                        width = 0;
                    }

                    TResource resource = new TResource();
                    resourceStatus = new TResourceStatus();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(4);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(4);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setDuration(0);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(length);
                    resource.setWidth(width);
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);

                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }
                    if (!normalType) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Illegal.getFlag());
                    }

                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 5: {//pdf
                    //status = uploadPdf(name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    TResource resource = new TResource();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceStatus = new TResourceStatus();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(5);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(5);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(1);
                    resource.setWidth(1);
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }

                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 6: {//ppt
                    //status = uploadPpt(name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    TResource resource = new TResource();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceStatus = new TResourceStatus();

                    //中间是将ppt相关的信息给读取出来并赋值给上面的四个对象。可以参考其他的处理
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(6);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(6);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setDuration(0);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setPath(target.getPath());
                    resource.setLength(1);
                    resource.setWidth(1);
                    resourceStatus.setName(splitname[0]);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }
                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                case 7: {//swf
                    //status = uploadswf(name, nameForMd5, format, fid, md5folder, size, userId, needturn, virtualForUpload, webpath, uName);
                    TResource resource = new TResource();
                    TResourceIndex resourceIndex = new TResourceIndex();
                    TResourceCatalog resourceCata0 = new TResourceCatalog();
                    resourceStatus = new TResourceStatus();
                    resourceCata0.setName(splitname[0]);
                    resourceCata0.setMd5(nameForMd5);
                    resourceCata0.setFormat(splitname[1]);
                    resourceCata0.setVirtual(virtualForUpload);
                    resourceCata0.setFid(Integer.parseInt(pathId));
                    resourceCata0.setType(7);
                    //若为初始化导入
                    if ("init".equals(md5folder)) {
                        resourceCata0.setStatus(2);
                    } else {
                        resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
                    }
                    resourceCata0.setDeep("");
                    resourceCata0.setFolder(true);
                    resourceCata0.setSubitems("子目录集");
                    resourceCata0.setIslock(false);
                    resourceCata0.setLayer("层级");
                    resourceCata0.setDeep("层的深度");
                    resourceCata0.setEdit(true);
                    resourceCata0.setDelFlag(0);
                    resourceIndex.settName(splitname[0]);
                    resourceIndex.settMd5(nameForMd5);
                    resourceIndex.settPath(target.getPath());
                    resourceIndex.settStatus(1);
                    resourceIndex.settType(7);
                    resourceIndex.settConvert("转换的结果");
                    resourceIndex.settDesc("本资源的描述信息");
                    resourceIndex.settResult("资源的转换结果");
                    resourceIndex.settVersion("资源的版本");
                    resourceIndex.settSituation("这里是资源排队等待的状况");
                    resource.setName(splitname[0]);
                    resource.setSize((int) (file.length() / 1024));
                    resource.setFormat(splitname[1]);
                    resource.setCreator(userName);
                    resource.setUpdator("资源更新人，默认的时候是null");
                    resource.setLength(1);
                    resource.setWidth(1);
                    resource.setPath(target.getPath());
                    resourceStatus.setName(splitname[0]);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(System.currentTimeMillis());
                    resourceStatus.setUploadtime(dateString);
                    resourceStatus.setUploader(userName);
                    resourceStatus.setUpdatetime(null);
                    resourceStatus.setUpdater(null);
                    resourceStatus.setChecker("资源的审核人");
                    resourceStatus.setChecktime(null);
                    resourceStatus.setFormatstatus("格式转换状态");
                    if (!needturn) {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
                    } else {
                        resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
                    }

                    //上传完成
                    saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
                    TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
                    break;
                }
                default:
                    logger.debug("我其实什么都不是");
                    break;
            }
            if (needturn && normalType) {

                if (type != 0) {
                    Object[] resource = new Object[]{fileName, fileFormat, nameForMd5, target, target, userName, resourceStatus};
                    executorService.execute(new ResourceTransfer(resource, isH264));
                }
            }
            //转换资源
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
    }

    /*private TResourceStatus uploadPdf(String name, String md5Name, String format, int fid, String md5folder,
                                      int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        TResource resource = new TResource();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        TResourceStatus resourceStatus = new TResourceStatus();
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(5);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(5);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(1);
        resource.setWidth(1);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadPpt(String name, String md5Name, String format, int fid, String md5folder,
                                      int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        TResource resource = new TResource();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        TResourceStatus resourceStatus = new TResourceStatus();
        //中间是将ppt相关的信息给读取出来并赋值给上面的四个对象。可以参考其他的处理
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(6);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(6);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setDuration(0);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(1);
        resource.setWidth(1);
        resourceStatus.setName(name);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadswf(String name, String md5Name, String format, int fid, String md5folder,
                                      int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        TResource resource = new TResource();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        TResourceStatus resourceStatus = new TResourceStatus();
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(7);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(7);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setLength(1);
        resource.setWidth(1);
        resource.setPath(path);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadImg(Boolean normalType, String name, String md5Name, String format, int fid, String md5folder,
                                      int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        PicInfo ImageInfo = null;
        Integer length = null;
        Integer width = null;
        try {
            ImageInfo = PicConverter.imageInfo(path);
            length = ImageInfo.getHeight();
            width = ImageInfo.getWidth();
        } catch (Exception e) {
            normalType = false;
            length = 0;
            width = 0;
        }
        TResource resource = new TResource();
        TResourceStatus resourceStatus = new TResourceStatus();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();

        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(4);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(1);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(name);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setDuration(0);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(length);
        resource.setWidth(width);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);

        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        if (!normalType) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Illegal.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadExcel(String name, String md5Name, String format, int fid, String md5folder,
                                        int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        TResource resource = new TResource();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        TResourceStatus resourceStatus = new TResourceStatus();
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(3);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(3);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(1);
        resource.setWidth(1);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadDoc(String name, String md5Name, String format, int fid, String md5folder,
                                      int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        TResource resource = new TResource();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        TResourceStatus resourceStatus = new TResourceStatus();
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(2);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(2);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(1);
        resource.setWidth(1);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");
        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        //上传完成
        TResourceStatus status = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return status;
    }

    private TResourceStatus uploadVideo(boolean normalType, String name, String md5Name, String format, int fid, String md5folder,
                                        int size, String uid, boolean needturn, String virtualForUpload, String path, String uName) {
        VideoEncoder videoEncoder = new VideoEncoder();
        MultimediaInfo mediaInfo = null;
        int duration = 0;
        int length = 0;
        int width = 0;
        try {
            mediaInfo = videoEncoder.getEncoder().getInfo(new File(target.getPath()));
            duration = (int) mediaInfo.getDuration();
            length = mediaInfo.getVideo().getSize().getHeight();
            width = mediaInfo.getVideo().getSize().getWidth();
        } catch (Exception e) {
            duration = 0;
            length = 0;
            width = 0;
            normalType = false;
        }
        TResource resource = new TResource();
        TResourceStatus resourceStatus = new TResourceStatus();
        TResourceIndex resourceIndex = new TResourceIndex();
        TResourceCatalog resourceCata0 = new TResourceCatalog();
        resourceCata0.setName(name);
        resourceCata0.setMd5(md5Name);
        resourceCata0.setFormat(format);
        resourceCata0.setVirtual(virtualForUpload);
        resourceCata0.setFid(fid);
        resourceCata0.setType(1);
        //若为初始化导入
        if ("init".equals(md5folder)) {
            resourceCata0.setStatus(2);
        } else {
            resourceCata0.setStatus(TresourceUploadByUploadifyAction.verifyStatusFlag);
        }
        resourceCata0.setDeep("");
        resourceCata0.setFolder(true);
        resourceCata0.setSubitems("子目录集");
        resourceCata0.setIslock(false);
        resourceCata0.setLayer("层级");
        resourceCata0.setDeep("层的深度");
        resourceCata0.setEdit(true);
        resourceCata0.setDelFlag(0);
        resourceIndex.settName(name);
        resourceIndex.settMd5(md5Name);
        resourceIndex.settPath(path);
        resourceIndex.settStatus(1);
        resourceIndex.settType(1);
        resourceIndex.settConvert("转换的结果");
        resourceIndex.settDesc("本资源的描述信息");
        resourceIndex.settResult("资源的转换结果");
        resourceIndex.settVersion("资源的版本");
        resourceIndex.settSituation("这里是资源排队等待的状况");
        resource.setName(name);
        resource.setSize(size);
        resource.setFormat(format);
        resource.setCreator(uid);
        resource.setCreatorName(uName);
        resource.setDuration(duration);
        resource.setUpdator("资源更新人，默认的时候是null");
        resource.setPath(path);
        resource.setLength(length);
        resource.setWidth(width);
        resourceStatus.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(System.currentTimeMillis());
        resourceStatus.setUploadtime(dateString);
        resourceStatus.setUploader(uid);
        resourceStatus.setUpdatetime(null);
        resourceStatus.setUpdater(null);
        resourceStatus.setChecker("资源的审核人");
        resourceStatus.setChecktime(null);
        resourceStatus.setFormatstatus("格式转换状态");

        if (!needturn) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
        } else {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Uploaded.getFlag());
        }
        if (!normalType) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Illegal.getFlag());
        }
        //上传完成
        TResourceStatus tatus = saveResourceInfo(resource, resourceStatus, resourceIndex, resourceCata0);
        TresourceUploadByUploadifyAction.catalogSet.remove(resource.getName() + "." + resource.getFormat());
        return tatus;
    }

    class TransferResource extends Thread {
        String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
        String imgType = "jpg,jpeg,png,bmp,gif";
        String wordType = "docx,doc";
        String excelType = "xlsx,xls";
        String pdfType = "pdf";
        String pptType = "pptx,ppt,pps";
        String flashType = "swf";

        public boolean isBigFile(String type) {
            if (type.contains("avi") || type.contains("wmv") || type.contains("mov") || type.contains("rm") || type.contains("rmvb") || type.contains("mpg") || type.contains("mpeg") || type.contains("mp4") || type.contains("mkv") || type.contains("3gp") || type.contains("m4v") || type.contains("ts")) {
                return true;
            } else if (type.contains("xlsx") || type.contains("xls")) {
                return true;
            } else if (type.contains("doc") || type.contains("docx")) {
                return true;
            } else if (type.contains("ppt") || type.contains("pdf") || type.contains("pptx") || type.contains("pps")) {
                return true;
            } else {
                return false;
            }
        }

        public void push(Object[] resource) {
            if (resource == null || resource.length < 6) return;
            if (whichTransferWay == 0) {
                transferQueue.enqueue(resource);
            } else {
                secondtransferQueue.enqueue(resource);
            }
            this.start();
        }

        public void run() {
            if (whichTransferWay == 0) {
                try {
                    if (threadQueue.size() < MAX_TRANSFER_CNT && !transferQueue.isEmpty()) {
                        Object[] aResource = (Object[]) transferQueue.dequeue();
                        new TransferDo(aResource).start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (threadQueue.size() < MAX_TRANSFER_CNT && !secondtransferQueue.isEmpty()) {
                        Object[] wResource = (Object[]) secondtransferQueue.front();
                        if (isBigFile(wResource[1].toString().toLowerCase())) {
                            System.out.println(wResource[1].toString());
                            while ((wResource != null) && isBigFile(wResource[1].toString().toLowerCase())) {
                                if (hasPPtTransferingFlag == 0) {
                                    hasPPtTransferingFlag = 1;
                                    if (secondtransferQueue.remove(wResource)) {
                                        new TransferDo(wResource).start();
                                    }
                                } else {
                                    wResource = (Object[]) secondtransferQueue.next(wResource);
                                }
                            }
                        }
                        if ((wResource != null) && !isBigFile(wResource[1].toString().toLowerCase())) {
                            System.out.println(wResource[1].toString());
                            if (secondtransferQueue.remove(wResource)) {

                                new TransferDo(wResource).start();
                            }
                        }
                    }
                } catch (Exception e) {
//                    e.printStackTrace();
                    logger.error("", e);
                }
            }
        }
    }
class TransferDo extends Thread {

        public Object[] resource = null;

        public TransferDo(Object[] aResource) {
            this.resource = aResource;
        }

        public void run() {
            try {
                threadQueue.enqueue(resource);
                if (resource == null || resource.length < 6) return;
                String fileName = resource[0].toString();
                String fileFormat = resource[1].toString();
                String nameForMd5 = resource[2].toString();
                File myTarget = (File) resource[3];
                File myFile = (File) resource[4];
                String name = resource[5].toString();
                TResourceStatus resourceStatus = (TResourceStatus) resource[6];
                String[] splitname = new String[]{fileName, fileFormat};
                String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
                String imgType = "jpg,jpeg,png,bmp,gif";
                String wordType = "docx,doc";
                String excelType = "xlsx,xls";
                String pdfType = "pdf";
                String pptType = "pptx,ppt,pps";
                String flashType = "swf";
                int type = 0;
                String suffix = splitname[1].toLowerCase();
                int vValue = videoType.indexOf(suffix);
                if (vValue != -1) {

                    type = 1;
                }
                int wValue = wordType.indexOf(suffix);
                if (wValue != -1) {
                    type = 2;
                }
                int eValue = excelType.indexOf(suffix);
                if (eValue != -1) {
                    type = 3;
                }
                int imgValue = imgType.indexOf(suffix);
                if (imgValue != -1) {
                    type = 4;
                }
                int pValue = pdfType.indexOf(suffix);
                if (pValue != -1) {
                    type = 5;
                }
                int pptValue = pptType.indexOf(suffix);
                if (pptValue != -1) {
                    type = 6;
                }
                int swfValue = flashType.indexOf(suffix);
                if (swfValue != -1) {
                    type = 7;
                }
                switch (type) {
                    case 1:
                        video(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        hasPPtTransferingFlag = 0;
                        break;
                    case 2:
                        word(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        hasPPtTransferingFlag = 0;
                        break;
                    case 3:
                        excel(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        hasPPtTransferingFlag = 0;
                        break;
                    case 4:
                        pic(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);

                        break;
                    case 5:
                        pdf(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        hasPPtTransferingFlag = 0;
                        break;
                    case 6:
                        //String convertPath = ServletActionContext.getServletContext().getRealPath("/tools")+"/PptCmdExport.exe";
                        ppt(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        hasPPtTransferingFlag = 0;
                        break;
                    case 7:
                        flash(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    default:
                        System.out.println("我其实什么都不是");
                        break;
                }
            } catch (Exception e) {
//                e.printStackTrace();
                logger.error("", e);
            } finally {
                threadQueue.dequeue();
                new TransferResource().start();
            }
        }
    }*/

    /**
     * todo 加注释
     * @param resource
     * @param resourceStatus
     * @param resourceIndex
     * @param resourceCatalog
     * @return
     */
    @Transactional
    public TResourceStatus saveResourceInfo(TResource resource, TResourceStatus resourceStatus, TResourceIndex resourceIndex, TResourceCatalog resourceCatalog) {
        try {
            String resourceId = this.tResourceDao.save(resource).toString();
            String resourceStatutsId = this.tResouceStatusDao.save(resourceStatus).toString();
            resourceIndex.setResourceId(Integer.parseInt(resourceId));
            resourceIndex.setResouceStatusId(Integer.parseInt(resourceStatutsId));
            this.tResourceIndexDao.save(resourceIndex);
            resourceCatalog.setResourceIndex_id(resourceIndex.getId());
            this.tResourceCatalogDao.saveOrUpdate(resourceCatalog);
            TResourceStatus status = tResouceStatusDao.getResourceStatus(Integer.parseInt(resourceStatutsId));
            return status;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("输出异常", e);
            return new TResourceStatus();
        }
    }

    /*public void video(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
        try {
            VideoEncoder videoEncoder = new VideoEncoder();
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                updateByEntiy(resourceStatus);
                videoEncoder.encode(myTarget.getPath());
            } catch (Exception e) {
                e.printStackTrace();
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                updateByEntiy(resourceStatus);
            }
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            updateByEntiy(resourceStatus);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     * @param isH264
     */
    public void video(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus, boolean isH264) {
        if(!ResourceStatusFlag.Converted.getFlag().equals(resourceStatus.getUploadstatus())) {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
        }

        try {
            updateByEntiy(resourceStatus);
            VideoEncoder videoEncoder = new VideoEncoder();
            videoEncoder.encode(myTarget.getPath(), isH264, this, resourceStatus);
        } catch (Exception e) {

        }
    }

    /*public void word(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
        try {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                updateByEntiy(resourceStatus);
                new WordOrExcelToPng().word2Png(myTarget.getAbsolutePath());
            } catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                updateByEntiy(resourceStatus);
            }
            if (isSuccess(myTarget.getAbsolutePath(), "word")) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            } else {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
            }
            updateByEntiy(resourceStatus);
            com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = WordOrExcelToPng.getOfficeInfo(myTarget.getPath());
            updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
        }
    }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     */
    public void word(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {

        try {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
            updateByEntiy(resourceStatus);
            new WordOrExcelToPng().word2Png(myTarget.getAbsolutePath(), this, resourceStatus, myTarget.getPath());
        } catch (Exception e) {

        }

    }

    /* public void excel(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
         try {
             try {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                 updateByEntiy(resourceStatus);
                 new WordOrExcelToPng().excel2Png(myTarget.getAbsolutePath());

             } catch (Exception e) {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                 updateByEntiy(resourceStatus);
             }
             if (isSuccess(myTarget.getAbsolutePath(), "excel")) {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
             } else {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
             }
             updateByEntiy(resourceStatus);
             com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = WordOrExcelToPng.getOfficeInfo(myTarget.getPath());
             updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
         } catch (Exception e) {
 //            e.printStackTrace();
             logger.error("", e);
         }
     }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     */
    public void excel(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
        try {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
            updateByEntiy(resourceStatus);
        } catch (Exception e) {
        }
        new WordOrExcelToPng().excel2Png(myTarget.getAbsolutePath(), this, resourceStatus, myTarget.getPath());
    }

    /*public void pdf(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
        try {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                updateByEntiy(resourceStatus);
                new WordOrExcelToPng().pdf2Png(myTarget.getAbsolutePath());
            } catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                updateByEntiy(resourceStatus);
            }
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            updateByEntiy(resourceStatus);
            com.honghe.recordweb.util.base.util.wordorexcelorpdf2pngconverter.PicInfo picInfo = WordOrExcelToPng.getOfficeInfo(myTarget.getPath());
            updateSize(resourceStatus, picInfo.getWidth(), picInfo.getHeight());
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     */
    public void pdf(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {

        try {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
            updateByEntiy(resourceStatus);
        } catch (Exception e) {
            return;
        }
        new WordOrExcelToPng().pdf2Png(myTarget.getAbsolutePath(), this, resourceStatus, myTarget.getPath());

    }

    /* public void flash(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
         try {
             try {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                 updateByEntiy(resourceStatus);
                 File source = new File(myTarget.getAbsolutePath());
                 String filename = source.getName().substring(0, source.getName().indexOf("."));
                 String path = source.getPath().replaceAll(source.getName(), "");
                 new File(path + filename).mkdir();
                 FileUtil.copyFile(source, new File(path + filename + "\\flash.swf"));
                 FileUtil.copyFile(new File(path + "./../../../../image/frontend/resources/thumb.png"), new File(path + filename + "\\thumb.png"));
             } catch (Exception e) {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                 updateByEntiy(resourceStatus);
             }
             resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
             updateByEntiy(resourceStatus);
         } catch (Exception e) {
 //            e.printStackTrace();
             logger.error("", e);
         }
     }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     */
    public void flash(String[] splitname, String nameForMd5, final File myTarget, File file, String name, final TResourceStatus resourceStatus) {
//       executorService.execute(new Runnable() {
//           public void run() {
        try {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                updateByEntiy(resourceStatus);
                File source = new File(myTarget.getAbsolutePath());
                String filename = source.getName().substring(0, source.getName().indexOf("."));
                String path = source.getPath().replaceAll(source.getName(), "");
                new File(path + filename).mkdir();
                FileUtil.copyFile(source, new File(path + filename + "\\flash.swf"));
                FileUtil.copyFile(new File(path + "./../../../../image/frontend/resources/thumb.png"), new File(path + filename + "\\thumb.png"));
            } catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                updateByEntiy(resourceStatus);
            }
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            updateByEntiy(resourceStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
//       });
    }

    /* public void pic(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) {
         try {
             try {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                 updateByEntiy(resourceStatus);
                 String path = myTarget.getAbsolutePath();
                 String[] strs = path.split("\\\\");
                 strs[strs.length - 1] = "temp_" + strs[strs.length - 1];
                 String newPath = StringUtil.join(strs, "/");
                 FileUtil.copyFile(myTarget, new File(newPath));
                 PicConverter.imageConvert(myTarget.getPath());
                 if (new File(newPath).exists()) {
                     FileUtil.delFile(myTarget);
                     FileUtil.rename(new File(newPath), myTarget.getAbsolutePath());
                 }
             } catch (Exception e) {
                 resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                 updateByEntiy(resourceStatus);
             }
             resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
             updateByEntiy(resourceStatus);
         } catch (Exception e) {
 //            e.printStackTrace();
             logger.error("", e);
         }
     }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     */
    public void pic(String[] splitname, String nameForMd5, final File myTarget, File file, String name, final TResourceStatus resourceStatus) {
//       executorService.execute(new Runnable() {
//           public void run() {
        try {
            try {
                resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
                updateByEntiy(resourceStatus);
                String path = myTarget.getAbsolutePath();
                String[] strs = path.split("\\\\");
                strs[strs.length - 1] = "temp_" + strs[strs.length - 1];
                String newPath = StringUtil.join(strs, "/");
                FileUtil.copyFile(myTarget, new File(newPath));
                PicConverter.imageConvert(myTarget.getPath());
                if (new File(newPath).exists()) {
                    FileUtil.delFile(myTarget);
                    FileUtil.rename(new File(newPath), myTarget.getAbsolutePath());
                }
            } catch (Exception e) {
                resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
                updateByEntiy(resourceStatus);
            }
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
            updateByEntiy(resourceStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }

//           }
//       });
    }

    /* public void ppt(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) throws Exception {
         try {
             resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
             updateByEntiy(resourceStatus);
             String oo = myTarget.getAbsolutePath();
             System.out.println(myTarget.getAbsolutePath());
             new PPT2HTML().convert(myTarget.getAbsolutePath());
         } catch (Exception e) {
             resourceStatus.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
             updateByEntiy(resourceStatus);
 //            e.printStackTrace();
             logger.error("", e);
         } finally {
             if (hasPPtTransferingFlag == 1) {
                 hasPPtTransferingFlag = 0;
             }
         }
         TResourceStatus status = tResouceStatusDao.getResourceStatus(resourceStatus.getId());
         if (isSuccess(myTarget.getAbsolutePath(), "ppt")) {
             status.setUploadstatus(ResourceStatusFlag.Converted.getFlag());
         } else {
             status.setUploadstatus(ResourceStatusFlag.ConvertError.getFlag());
         }
         ;
         updateByEntiy(status);
     }*/

    /**
     * todo 加注释
     * @param splitname
     * @param nameForMd5
     * @param myTarget
     * @param file
     * @param name
     * @param resourceStatus
     * @throws Exception
     */
    public void ppt(String[] splitname, String nameForMd5, File myTarget, File file, String name, TResourceStatus resourceStatus) throws Exception {
        try {
            resourceStatus.setUploadstatus(ResourceStatusFlag.Converting.getFlag());
            updateByEntiy(resourceStatus);
        } catch (Exception e) {

        }
        new PPT2HTML().convert(myTarget.getAbsolutePath(), this, resourceStatus, myTarget.getPath());

    }

    /**
     *
     * @param path
     * @param flag
     * @return
     */
    public boolean isSuccess(String path, String flag) {
        String pa = path.substring(0, path.lastIndexOf("."));
        File file = new File(pa);
        String taretfile = "";
        if (!file.exists()) {
            return false;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return false;
        }
        if ("word".equals(flag)) {
            taretfile = "word.zip";
        } else if ("excel".equals(flag)) {
            taretfile = "excel.zip";
        } else if ("ppt".equals(flag)) {
            taretfile = "ppt.zip";
        }
        for (File f : files) {
            if (taretfile.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * todo 加注释
     * @param resourceCatalog
     */
    @Transactional
    public void updateByEntiy(TResourceCatalog resourceCatalog) {
        try {
            this.tResourceCatalogDao.update(resourceCatalog);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param resourceStatus
     */
    @Transactional
    public void updateByEntiy(TResourceStatus resourceStatus) {
        try {
            this.tResouceStatusDao.update(resourceStatus);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

//    @Transactional
//    public void updateByEntiy1(TResourceStatus resourceStatus) {
//        try {
//            this.tResouceStatusDao.update1(resourceStatus);
//        } catch (Exception e) {
////            e.printStackTrace();
//            logger.error("", e);
//        }
//    }

    /**
     * todo 加注释
     * @param resourceStatus
     * @param width
     * @param height
     * @throws Exception
     */
    @Transactional
    public void updateSize(TResourceStatus resourceStatus, int width, int height) throws Exception {
        this.tResourceDao.executeHql("UPDATE TResource SET width=" + width + ",length=" + height + " WHERE id IN (SELECT resourceId FROM TResourceIndex WHERE resouceStatusId=" + resourceStatus.getId() + ")");
    }

    /**
     * todo 加注释
     * @param tResourceStatus
     */
    @Transactional
    public void saveResourceStatus(TResourceStatus tResourceStatus) {
        try {
            tResouceStatusDao.save(tResourceStatus);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * todo 加注释
     * @param name
     * @param type
     * @param fileName
     * @return
     * @throws Exception
     */
    public List<TResourceCatalog> getResourceCatalogByName(String name, String type, String fileName) throws Exception {
        List<TResourceCatalog> result = null;
        int types = 0;
        if (type.trim().equalsIgnoreCase("video")) {
            types = 1;
        } else if (type.trim().equalsIgnoreCase("word")) {
            types = 2;
        } else if (type.trim().equalsIgnoreCase("excel")) {
            types = 3;
        } else if (type.trim().equalsIgnoreCase("img")) {
            types = 4;
        } else if (type.trim().equalsIgnoreCase("pdf")) {
            types = 5;
        } else if (type.trim().equalsIgnoreCase("ppt")) {
            types = 6;
        } else if (type.trim().equalsIgnoreCase("flash")) {
            types = 7;
        } else if (type.trim().equalsIgnoreCase("audio")) {
            types = 8;
        } else {
            types = 0;
        }
        result = tResourceCatalogDao.getResourceCatalogByName(name, types, fileName);
        return result;
    }

    public MyUploadForm getMyUpload(TResourceCatalog resourceCatalog) {
        MyUploadForm myUploadForm = new MyUploadForm();
        myUploadForm.setName(resourceCatalog.getName());
        myUploadForm.setVirtual(resourceCatalog.getVirtual());
        myUploadForm.setStatus(resourceCatalog.getStatus());
        myUploadForm.setSize(resourceCatalog.getResourceIndex().getResource().getSize());
        myUploadForm.setType(resourceCatalog.getType());
        myUploadForm.setId(resourceCatalog.getId());
        String str = resourceCatalog.getResourceIndex().getResouceStatus().getUploadtime();
        String timeStr = str.substring(0, str.lastIndexOf(":"));
        myUploadForm.setCreateTime(timeStr);
        myUploadForm.setUploader(resourceCatalog.getResourceIndex().getResouceStatus().getUploader());
        if (resourceCatalog.getType() != 0) {
            myUploadForm.setTransStatus(resourceCatalog.getResourceIndex().getResouceStatus().getUploadstatus());
        } else {
            //     logger.debug("in set null");
            myUploadForm.setTransStatus("");
        }
        return myUploadForm;
    }

    /**
     * todo 加注释
     * @param name
     * @param pathId
     * @return
     * @throws Exception
     */
    public Boolean checkOneNameOnly(String name, String pathId) throws Exception {
        List result = tResourceCatalogDao.findFolderByPath(name, pathId);
        if (result.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * get actual path according to virtual path . if isSnap is true , return snap path , else return actual path.
     */
    public String getPathByVirtual(boolean isSnap, String virtualPath) {
        try {
            if (virtualPath.equals("media/cat.mp4") || virtualPath.equals("images/test1.jpg") || virtualPath.equals("{}") || virtualPath.equals("")) {
                return "";
            }
            if (virtualPath.indexOf("/") > -1) {
                String[] temp = virtualPath.split("/");
                if (temp.length > 1) {
                    String fileName = temp[temp.length - 1];
                    String virtual = virtualPath.replace(fileName, "");
                    if (fileName.split("\\.").length > 1) {
                        String name = fileName.split("\\.")[0];
                        String format = fileName.split("\\.")[1];

                        String hql = "select rc.id,rc.fid,rc.name,ri.tPath,rc.type from TResourceCatalog rc,TResourceIndex ri where rc.md5=ri.tMd5 and rc.virtual='" + virtual + "' and rc.name='" + name + "' and rc.delFlag = 0 and rc.format='" + format + "'";
                        List<?> list = tResourceCatalogDao.find(hql);
                        if (list == null || list.size() == 0) {
                            return "";
                        }

                        Object[] obj = (Object[]) list.get(0);
                        ResourceForm resourceForm = new ResourceForm();

                        int type = (Integer) obj[4];

                        String suffix = ((String) obj[3]).substring(((String) obj[3]).lastIndexOf("."));
                        String extName = GlobalParameter.getExtName(type, suffix);

                        String srcPath = ((String) obj[3]);
                        srcPath = srcPath.replaceAll("\\\\", "/");
                        srcPath = srcPath.replace("../webapps/ManagementCenter/data/", "/");
                        String path = srcPath.substring(0, srcPath.lastIndexOf("."));
                        if (isSnap) {
                            return path + "/thumb.png";
                        }

                        return path + "/" + extName;
                    } else {
                        return "";
                    }
                } else {
                    return "";
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 内部类，用以资源转换
     */
    class ResourceTransfer implements Runnable {
        @Override
        public void run() {
            this.call();
            System.gc();
        }



        private Object[] resource;
        private boolean isH264;

        public ResourceTransfer(Object[] resource, boolean isH264) {
            this.resource = resource;
            this.isH264 = isH264;
        }

        public String call() {
            //开始转换后，将静态的当前转换资源名称置为当前资源的名称，用以帮助监控线程控制转换时间
            try {
                if (resource == null || resource.length < 6) return null;
                String fileName = resource[0].toString();
                String fileFormat = resource[1].toString();
                String nameForMd5 = resource[2].toString();
                File myTarget = (File) resource[3];
                File myFile = (File) resource[4];
                String name = resource[5].toString();
                TResourceStatus resourceStatus = (TResourceStatus) resource[6];
                String[] splitname = new String[]{fileName, fileFormat};
                String videoType = "avi,wmv,mov,rm,rmvb,mpg,mpeg,mp4,vob,flv,f4v,mkv,3gp,m4v,ts";
                String imgType = "jpg,jpeg,png,bmp,gif";
                String wordType = "docx,doc";
                String excelType = "xlsx,xls";
                String pdfType = "pdf";
                String pptType = "pptx,ppt,pps";
                String flashType = "swf";
                int type = 0;

                String suffix = splitname[1].toLowerCase();
                int vValue = videoType.indexOf(suffix);
                if (vValue != -1) {

                    type = 1;
                }
                int wValue = wordType.indexOf(suffix);
                if (wValue != -1) {
                    type = 2;
                }
                int eValue = excelType.indexOf(suffix);
                if (eValue != -1) {
                    type = 3;
                }

                int imgValue = imgType.indexOf(suffix);
                if (imgValue != -1) {
                    type = 4;
                }
                int pValue = pdfType.indexOf(suffix);
                if (pValue != -1) {
                    type = 5;
                }
                int pptValue = pptType.indexOf(suffix);
                if (pptValue != -1) {
                    type = 6;
                }
                int swfValue = flashType.indexOf(suffix);
                if (swfValue != -1) {
                    type = 7;
                }
                //开始区分，并且进行转换
                switch (type) {
                    case 1:
                        video(splitname, nameForMd5, myTarget, myFile, name, resourceStatus, isH264);
                        break;
                    case 2:
                        word(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    case 3:
                        excel(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    case 4:
                        pic(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);

                        break;
                    case 5:
                        pdf(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    case 6:
                        ppt(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    case 7:
                        flash(splitname, nameForMd5, myTarget, myFile, name, resourceStatus);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public List<TResourceCatalog> findByPage1(List<TResourceCatalog> list, int start, int number) {
        List<TResourceCatalog> l = new ArrayList<TResourceCatalog>();
        for (int i = 0; i < number; i++) {
            if ((start + i + 1) > list.size()) {
                break;
            }
            l.add(list.get(start + i));
        }
        return l;
    }
}
