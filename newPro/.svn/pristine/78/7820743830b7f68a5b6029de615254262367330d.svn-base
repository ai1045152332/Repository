package com.honghe.recordweb.action.frontend.tresource;

import com.honghe.recordhibernate.entity.*;
import com.honghe.recordhibernate.entity.form.JsonForm;
import com.honghe.recordhibernate.entity.form.ResourceForm;
import com.honghe.recordhibernate.entity.tools.GlobalParameter;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.tresource.DataGrid;
import com.honghe.recordweb.service.frontend.tresource.MyUploadForm;
import com.honghe.recordweb.service.frontend.tresource.TresourceService;
import com.honghe.recordweb.util.base.entity.FunctionModule;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/26
 */
@Controller
@Scope(value = "Prototype")
public class TresourceAction extends BaseAction {
    @Resource
    private TresourceService tresourceService;
    public String virtualPreView;
    public String fatherPathId;
    public String names;
    String path;
    String floderName;
    String createPathId;
    private String rows;
    private String page;
    String prid;
    public String movePath;
    private String virtual;
    String name;
    String type;
    String flag;
    String data;
    String ids;
    String currentFolderId;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCurrentFolderId() {
        return currentFolderId;
    }

    public void setCurrentFolderId(String currentFolderId) {
        this.currentFolderId = currentFolderId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getMovePath() {
        return movePath;
    }

    public void setMovePath(String movePath) {
        this.movePath = movePath;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getRows() {
        return rows;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void getResourceTree() {
        writeJson(tresourceService.getResourceTree());
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }

    public String getPrid() {
        return prid;
    }

    public void setPrid(String prid) {
        this.prid = prid;
    }

    public String getCreatePathId() {
        return createPathId;
    }

    public void setCreatePathId(String createPathId) {
        this.createPathId = createPathId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFloderName() {
        return floderName;
    }

    public void setFloderName(String floderName) {
        this.floderName = floderName;
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    public String home() throws Exception {
        if (virtualPreView == null) {
            virtualPreView = "/root/";
        } else {
            virtualPreView = java.net.URLDecoder.decode(virtualPreView, "UTF-8");
            //virtualPreView = new String(virtualPreView.getBytes("ISO-8859-1"), "UTF-8");
        }
        List<TResourceCatalog> initData = tresourceService.findByProperty("virtual", virtualPreView);
        HttpServletRequest req = ServletActionContext.getRequest();
//        String prid = req.getParameter("prid");
        ActionContext.getContext().put("virtualPreView1", java.net.URLDecoder.decode(virtualPreView, "UTF-8"));
        req.setAttribute("prid", prid);
        if (fatherPathId == null) {
            req.setAttribute("currentPathId", "1");
        } else {
            req.setAttribute("currentPathId", fatherPathId);
        }
        Map<FunctionModule, Boolean> authMap = new HashMap<FunctionModule, Boolean>();
//        UserSession userSession=(UserSession)request.getSession().getAttribute(UserSession.NAME);
//
//        homeManager.getUserAuthMap(userSession.getUserInfo(), authMap);
//        String str=userManager.getUserPopedomStr(authMap, false);
//        req.setAttribute("moduleForOperate", str);
        //    System.out.println("in resourceAction ************************************");
        return "home";
    }


    /**
     * todo 加注释
     */
    public void load1() {
        try {
            HttpServletRequest req = ServletActionContext.getRequest();
//            String virtual = req.getParameter("virtual");
            virtual = new String(virtual.getBytes("ISO-8859-1"), "UTF-8");
            String virtual1 = java.net.URLDecoder.decode(virtual, "UTF-8");

		    /*req.getParameter("virtual");*/
//            String name = req.getParameter("name");
//            String type = req.getParameter("type");
//            String flag = req.getParameter("divFlag");
            if (type == null) {
                type = "all";
            }
            if (flag == null) {
                flag = "all";
            }
            List<TResourceCatalog> initData = new ArrayList<TResourceCatalog>();
            try {
                if ((name == null || "".equals(name)) && type.trim().equals("all")) {
                    initData = tresourceService.findByPropertyWidthOrder("virtual", virtual1);
                } else {
                    initData = tresourceService.findByQueryWithOrder(type, name, virtual1);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//        if("desc".equals(resourceFrom.getSortOrder()))
//            Collections.reverse(initData);
            String json1 = ConvertToJson(initData, 170);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json1);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * todo 加注释
     * @param list
     * @param countList
     * @return
     */
    public String ConvertToJson(List<?> list, int countList) {
        String jsonString = null;
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = null;
            for (Object object : list) {
                TResourceCatalog resourceCatalog = (TResourceCatalog) object;
                JsonForm jsonForm = new JsonForm();
                jsonForm.setId(resourceCatalog.getId());
                jsonForm.setType(resourceCatalog.getType());
                jsonForm.setName(resourceCatalog.getName());
                jsonForm.setStatus(resourceCatalog.getStatus());
                jsonForm.setVirtual(resourceCatalog.getVirtual());
                if (resourceCatalog.getResourceIndex_id() == null) {
                    jsonForm.setPath(null);
                } else {
                    TResource tResource = tresourceService.findTresourceById(resourceCatalog.getResourceIndex_id());
                    jsonForm.setSize(tResource.getSize());
                    jsonForm.setUploader(tResource.getCreator());
                    List<Object[]> tResourceIndex = tresourceService.findById(resourceCatalog.getResourceIndex_id());
                    //TResourceIndex tResourceIndex = resourceCatalog.getResourceIndex();
                    if (tResourceIndex != null && tResourceIndex.size() > 0) {
                        List<Object[]> tResourceStatus = tresourceService.findTresourceStatusById(Integer.parseInt(tResourceIndex.get(0)[0].toString()));
                        jsonForm.setCreateTime(tResourceStatus.get(0)[0].toString());
                    }
                    //TResourceStatus tResourceStatus = tResourceIndex.getResouceStatus();

                    String path = tresourceService.findTResourceIndexById(resourceCatalog.getResourceIndex_id()).gettPath();
                    int startPos = path.indexOf("\\data\\resources\\");
                    if (startPos < 0) startPos = path.indexOf("/data/resources/");
                    String webPath = ServletActionContext.getServletContext().getContextPath() + "/msgResource" + path.substring(startPos).replace("\\", "/");
                    //webPath += ServletActionContext.getServletContext().getContextPath()+"/msgResource";
                    jsonForm.setPath(webPath);
                }
                Integer type = resourceCatalog.getType();
                if (type.equals(GlobalParameter.floder)) {
                    Date date = new Date();
                    jsonForm.setCreateTime("");
                    jsonForm.setSize(0);
                    jsonForm.setUploader("");
                } else {
//                    String str=resourceCatalog.getResourceIndex().getResouceStatus().getUploadTime();
//                    String timeStr=str.substring(0,str.lastIndexOf(":"));
//                    jsonForm.setCreateTime(timeStr);
//                    jsonForm.setUploader(resourceCatalog.getResourceIndex().getResouceStatus().getUploader());
//                    jsonForm.setSize(resourceCatalog.getResourceIndex()
//                            .getResource().getSize());
                }

                jsonObject = new JSONObject(jsonForm);
                jsonArray.put(jsonObject);
            }
            String json = jsonArray.toString();
            StringBuffer sb = new StringBuffer();
            sb.append("{\"total\":");
            sb.append(countList);
            sb.append(",\"rows\":");
            sb.append(json);
            sb.append("}");
            jsonString = sb.toString();

            return jsonString;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * todo 加注释
     */
    public void load2() {
        try {
            ResourceForm resourceForm = new ResourceForm();
            HttpServletRequest request = ServletActionContext.getRequest();
//            String name = request.getParameter("name");
//            String type = request.getParameter("type");
            if (type == null) {
                type = "all";
            }
            if (name == null) {
                name = "";
            }
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            String userName = user.getUserName().toString();
            String createname = userName;
            List<TResourceCatalog> resourceCatalogs = tresourceService.getResourceCatalogByName(createname, type, name);
            List<MyUploadForm> list = new ArrayList<MyUploadForm>();
            if (resourceCatalogs != null) {
                for (TResourceCatalog resourceCatalog : resourceCatalogs) {
                    resourceCatalog.setVirtual(resourceCatalog.getVirtual().replace("root", "公共资源"));
                    list.add(tresourceService.getMyUpload(resourceCatalog));
                }
                DataGrid dataGrid = new DataGrid();
                dataGrid.setRows(list);
                dataGrid.setTotal((long) list.size());
                if ("desc".equals(resourceForm.getSortOrder()))
                    dataGrid.getReverse();
                this.writeJson(dataGrid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void pendingAuditDisplay() throws Exception {
        //Boolean flag=sysManager.verifyResourceStatus();是否开启资源审核
        this.writeJson(false);
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public String subfloder() throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();
        virtualPreView = java.net.URLDecoder.decode(virtual, "UTF-8");
//        virtualPreView=req.getParameter("virtual");
//        virtualPreView = new String(virtualPreView.getBytes("ISO-8859-1"),"UTF-8");
//        fatherPathId = req.getParameter("currentFolderId");
        fatherPathId = currentFolderId;
        return "subfloder";
    }



    /**
     * todo 加注释
     */
    public void getMd5name() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
//            String na = request.getParameter("data");
            String na = data;
            TResourceCatalog rCatalog = tresourceService.findByCatalogId(Integer.parseInt(na));
            String str = rCatalog.getMd5() + "." + rCatalog.getFormat();
            String path = rCatalog.getResourceIndex().gettPath();
            String[] strs = path.split("\\\\");
            //   System.out.println("3321 is :" + strs[strs.length-2]);
            this.writeJson("{'md5name':'" + str + "','path':'" + strs[strs.length - 2] + "'}");
        } catch (Exception e) {
            e.printStackTrace();
            this.writeJson("");
        }
    }
    /**
     * todo 加注释
     */
    public void getFileNum() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            //String pathStr = "../webapps/" + GlobalParameter.PROJECT_PATH;
            String pathStr = ServletActionContext.getServletContext().getContextPath();
//            String pathStr2 = request.getParameter("data");
            String pathStr2 = data;
            String path = ServletActionContext.getServletContext().getRealPath("/") + pathStr2;
            //String path = pathStr.replaceAll("\\\\", "/") + pathStr2;
            File f = new File(path);
            File[] files = f.listFiles();
            int num = files.length - 2;
            this.writeJson(num);
        } catch (Exception e) {
            e.printStackTrace();
            this.writeJson(0);
        }
    }
    /**
     * todo 加注释
     */
    public void dels() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
//            String[] ids = request.getParameter("data").trim().split("/");
            String[] ids = data.trim().split("/");

            Integer[] id = new Integer[ids.length];
            for (int i = 0; ids != null && i < ids.length; i++) {
                id[i] = Integer.parseInt(ids[i]);
                //   System.out.println("#########" + id[i]);
                TResourceCatalog resorceCatalog2 = tresourceService.findByCatalogId(id[i]);
                resorceCatalog2.setDelFlag(1);
                tresourceService.saveResourceCatalog(resorceCatalog2);
            }
            this.writeJson(true);
        } catch (Exception e) {
            e.printStackTrace();
            this.writeJson(false);
        }
    }

    /**
     * todo 加注释
     */
    public void rename() {
        try {
            int flag = 0;
//            HttpServletRequest req = ServletActionContext.getRequest();
//            String id = req.getParameter("ids");
            String id = ids;
//            String name = req.getParameter("name");
            //修改文件夹并修改原文件夹下的文件所属位置
            Boolean res = tresourceService.editFileNameAndSubFile(Integer.parseInt(id), name);
            if (res) {
                this.writeJson(true);
            } else {
                this.writeJson(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            writeJson(false);
        }
    }

    /**
     * 移动文件
     * @throws Exception
     */
    public void moveFile() throws Exception {
        String[] fileIds = ids.split(",");
        for (int i = 0; i < fileIds.length; i++) {
            // System.out.println("in moveFile");
            TResourceCatalog resourceCatalog = tresourceService.findByCatalogId(Integer.parseInt(fileIds[i]));
            TResourceCatalog resourceTarget = tresourceService.findByCatalogId(Integer.parseInt(movePath));
            String name = resourceTarget.getName();
            if ("公共资源".equals(name)) {
                name = "root";
            }
            String virtual = resourceTarget.getVirtual() + name + "/";
            resourceCatalog.setFid(Integer.parseInt(movePath));
            resourceCatalog.setVirtual(virtual);
            tresourceService.saveResourceCatalog(resourceCatalog);
        }
        this.writeJson(true);
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void checkOneNameOnly() throws Exception {
        String[] fileIds = names.split(",");
        ArrayList<String> idList = new ArrayList();
        for (int i = 0; i < fileIds.length; i++) {
            idList.add(fileIds[i]);
        }
        if (tresourceService.checkOneNameOnly(names, movePath)) {
            this.writeJson(true);
        } else {
            this.writeJson(false);
        }

    }

    /**
     * todo 加注释
     * @throws Exception
     */
    public void addFloder() throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();
//        String pathIdStr = (String) req.getParameter("createPathId");
        String pathIdStr = createPathId;
        TResourceCatalog resourceCatalog = new TResourceCatalog();
        resourceCatalog.setFid(Integer.parseInt(pathIdStr));
//        virtualPreView = req.getParameter("path").trim();
        virtualPreView = path.trim();
        resourceCatalog.setName(floderName);
        resourceCatalog.setVirtual(virtualPreView);
        resourceCatalog.setType(GlobalParameter.floder);
        resourceCatalog.setDelFlag(0);
        resourceCatalog.setStatus(2);//为文件夹添加标示，仅仅作为标示，sql处理中较容易，无其他意义
        tresourceService.saveResourceCatalog(resourceCatalog);
        this.writeJson(true);
    }

    /**
     * 下载文件
     * @param path
     * @param response
     * @return
     */
    public boolean downloadFile(String path, HttpServletResponse response) {
        if (path != null && !path.trim().equals("")) {
            URL urlfile = null;
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                OutputStream out = response.getOutputStream();
                //bis = new BufferedInputStream(new FileInputStream(path));
                urlfile = new URL(path);
                httpUrl = (HttpURLConnection) urlfile.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bos.flush();
                bis.close();
                httpUrl.disconnect();

            } catch (UnsupportedEncodingException uee) {
                System.out.println("no Encoding for download video path");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null)
                        bis.close();
                    if (bos != null)
                        bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 下载资源
     */
    public void downloadResource() {
        HttpServletRequest request = ServletActionContext.getRequest();
//        String url = request.getParameter("url");
//        String name = request.getParameter("name");
        String filePath = "http://localhost:8080" + url;
        String fileName = name;

        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            // 设置输出的格式
            response.reset();
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data;");
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            downloadFile(filePath, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件夹中是否有文件
     * @throws Exception
     */
    public void havaSub() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
//        String ids = request.getParameter("data");
        String ids = data;
        String[] fileIds;
        if (ids.indexOf(",") > -1) {
            fileIds = ids.split(",");
        } else {
            fileIds = ids.split("/");
        }

        int flag = 0;
        for (int i = 0; i < fileIds.length; i++) {
            if (tresourceService.findByProperty("fid", Integer.parseInt(fileIds[i])).size() > 0) {
                flag = 2;
                break;
            }
        }
        this.writeJson("{fla:\'" + flag + "\'}");
    }
}
