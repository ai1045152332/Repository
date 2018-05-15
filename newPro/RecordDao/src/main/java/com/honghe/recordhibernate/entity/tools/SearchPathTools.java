package com.honghe.recordhibernate.entity.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SearchPathTools {
	private static Logger logger= Logger.getLogger(SearchPathTools.class);
	
	public static final String serverIp = getServerIp();
	
	public static final String serverPort = ConfigUtil.get("serverPort");
	
	public static final String serverName = ConfigUtil.get("serverName");
	
	public static final String host = "http://"+serverIp + (serverPort.equals("80")?"" : ":"+serverPort);
	
	public static final String projectPathRelative= "../webapps/" + serverName + "/data/projects/";
	
	public static final String publishPathRelative = ServletActionContext.getServletContext().getRealPath("/msgResource") + "/publish/";
	
	public static final String publishPathAbsolute = host + "/" + serverName + "/data/projects/publish/";

	public static final String editPathRelative = ServletActionContext.getServletContext().getRealPath("/msgResource")+"/";
	
	public static String initResources = "../webapps/" + serverName + "/data/initresources/";
	
	public static String initProjects = "../webapps/" + serverName + "/data/initprojects/";
 	
	public static final String initFlag ="../webapps/" + serverName + "/data/initflag/";
	
	public static final String editPathAbsolute = host + "/" + serverName + "/data/projects/edit/";
	
	public static final String templatePathRelative = ServletActionContext.getServletContext().getRealPath("/msgResource") + "/template/";
	
	public static final String templatePathAbsolute = ServletActionContext.getServletContext().getRealPath("/msgResource") +  "/template/";
	
	public static final String modelPath = ServletActionContext.getServletContext().getRealPath("/data") + "/product/";
	
	public static final String ftpPath = "/"+ serverName + "/data/resources/ftp/";
	
	public static final String defaultPath = "/" + serverName + "/data/product/default/thumb.png";
	
	//public static final String resources="../webapps/"+serverName+"/data";
	public static final String resources=ServletActionContext.getServletContext().getRealPath("/");

	
	public static final String tempPath="../webapps/" + serverName + "/data/temp5/tempfolder/";
	public static final String tempPath2="../webapps/" + serverName + "/data/temp5/tempfolder2/";
	public static final String proImportTempPath="../webapps/" + serverName + "/data/temp6/";
	public static final String downloadPath ="../webapps/" + serverName + "/data/download/";
//	public static String decodePath(){
//		
//	}
	public static String getResources(String foldername){
		return resources+foldername+"/";
	}
	
	public static String getServerIp(){
		String serverIp = ConfigUtil.get("serverIp");
		if(StringUtils.isEmpty(serverIp.trim())){
			try {
				serverIp=InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e) {
				logger.error("获取服务器ip失败，请手动配置后重启服务",e);
			}
		}
		return serverIp;
	}
	
	public static String getEditRoot(String userName){
		String path =  editPathRelative+userName+"/";
		return path;
	}
	
	public static String getPublishRoot(boolean isAbsolute){
		if(isAbsolute){
			return publishPathAbsolute;
		}
		return publishPathRelative;
	}
	
	public static String getTemplateProgram(String userName,String prid,boolean isFile){
		String path = templatePathRelative+prid+"/"; 
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	public static String getEditFirstPageThumb(String userName,String prid,String pid,boolean isAbolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/"+pid+"/"+"thumb.png";
		if(isAbolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/"+pid+"/"+"thumb.png";
		}
		return thumbPath;
	}
	public static String getEditFirstPageThumb2(String userName,String prid,String pid,boolean isAbolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/"+""+"/"+"thumb.png";
		if(isAbolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/"+""+"/"+"thumb.png";
		}
		return thumbPath;
	}
	public static String getThumb(String userName,String prid,boolean isAbolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/"+"thumb"+".png";
		if(isAbolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/"+"thumb"+".png";
		}
		return thumbPath;
	}
	
	// 获取公共节目下的最外面的封面thumb.png
	public static String getPubLishThumb(String prid,boolean isAbolute){
		String thumbPath = publishPathRelative+prid+"/"+"thumb"+".png";
		if(isAbolute){
			thumbPath = publishPathAbsolute+prid+"/"+"thumb"+".png";
		}
		return thumbPath;
	}
	public static String getMD5(String userName,String prid,String folder,boolean isAbolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/media/"+folder+"/";
		if(isAbolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/media/"+folder+"/";
		}
		return thumbPath;
	}
	public static String getMD5Q(String userName,String prid,String folder,boolean isAbolute){
		String thumbPath = publishPathRelative+prid+"/media/"+folder+"/";
		if(isAbolute){
			thumbPath = publishPathAbsolute+prid+"/media/"+folder+"/";
		}
		return thumbPath;
	}
	public static String getSource(String userName,String prid,boolean isAbsolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/source/";
		if(isAbsolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/source/";
		}
		return thumbPath;
	}
	public static String getSourceQ(String userName,String prid,boolean isAbsolute){
		String thumbPath = publishPathRelative+prid+"/source/";
		if(isAbsolute){
			thumbPath = publishPathAbsolute+prid+"/source/";
		}
		return thumbPath;
	}
	public static String getMappedPath(String userName,String prid,boolean isAbolute){
		String thumbPath = editPathRelative+userName+"/"+prid+"/mapped/"+"/";
		if(isAbolute){
			thumbPath = editPathAbsolute+userName+"/"+prid+"/mapped/"+"/";
		}
		return thumbPath;
	}
	public static String getMappedPathQ(String userName,String prid,boolean isAbolute){
		String thumbPath = publishPathRelative+prid+"/mapped/"+"/";
		if(isAbolute){
			thumbPath = publishPathAbsolute+prid+"/mapped/"+"/";
		}
		return thumbPath;
	}
	public static String getPublishFirstPageThumb(String prid,String pid,boolean isAbolute){
		String thumbPath = publishPathRelative+prid+"/"+pid+"/"+"thumb.png";
		if(isAbolute){
			thumbPath = publishPathAbsolute+prid+"/"+pid+"/"+"thumb.png";
		}
		return thumbPath;
	}
	
	public static String getTemplateFirstPageThumb(String prid,String pid,boolean isAbolute){
		String thumbPath = templatePathRelative+prid+"/"+"thumb.png";
		if(isAbolute){
			thumbPath = templatePathAbsolute+prid+"/"+"thumb.png";
		}
		return thumbPath;
	}
	
	public static String getNPublishFirstPageThumb(String prid,String pid,boolean isAbolute){
		String thumbPath = publishPathRelative+prid+"/"+""+"/"+"thumb.png";
		if(isAbolute){
			thumbPath = publishPathAbsolute+prid+"/"+""+"/"+"thumb.png";
		}
		return thumbPath;
	}
	
	public static String getTemplateProgram(String prid,boolean isFile){
		String path =  templatePathRelative + prid+"/";
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	
	public static String getTemplatePage(String userName,String prid,String pid,boolean isFile){
		String path = templatePathRelative+prid+"/"+pid+"/";
		if(isFile){
			path = path+"page.xml";
		}
		return path;
	}
	
	public static String getTemplateItem(String prid,String pid,String bid){
		String path =  templatePathRelative+prid+"/"+pid+"/"+bid+".xml";
		return path;
	}
	
	public static String getTemplatePreview(String prid){
		String path = templatePathRelative + prid + "/preview/";
		return path;
	}
	
	public static String getTemplateZip(boolean isAbsolute,String prid){
		String path = templatePathRelative + prid+".zip";
		if(isAbsolute){
			path =  templatePathAbsolute + prid+".zip";
		}
		return path;
	}
	
	public static String getEditProgram(String userName,String prid,boolean isFile){
		String path =  editPathRelative+userName+"/"+prid+"/"; 
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	
	public static String getTempProgram(String userName,String prid,boolean isFile){
		String path =  templatePathRelative+prid+"/"; 
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	

	public static String getPreTemplateProgram(String userName,String prid,boolean isFile){
		String path =  templatePathRelative+prid+"/"; 
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	
	public static String getPublishProgram(String prid,boolean isFile){
		String path =  publishPathRelative + prid+"/";
		if(isFile){
			path = path +"project"+".xml";
		}
		return path;
	}
	
	public static String getPublishItem(String prid,String pid,String bid){
		String path =  publishPathRelative+prid+"/"+pid+"/"+bid+".xml";
		return path;
	}

	public static String getEditPage(String userName,String prid,String pid,boolean isFile){
		String path = editPathRelative+userName+"/"+prid+"/"+pid+"/";
		if(isFile){
			path = path+"page.xml";
		}
		return path;
	}
	
	public static String getPublishPage(String userName,String prid,String pid,boolean isFile){
		String path = publishPathRelative+prid+"/"+pid+"/";
		if(isFile){
			path = path+"page.xml";
		}
		return path;
	}
	
	public static String getEditItem(String userName,String prid,String pid,String bid){
		String path = editPathRelative+userName+"/"+prid+"/"+pid+"/"+bid+".xml";
		return path;
	}
	
	public static String getInitResources(){
		return initResources;
	}
	
	public static String getCommericalInitResources(){
			return  "../webapps/" + serverName + "/data/initresources_commerical/";
	}
	
	public static String getInitProjects(){
		return initProjects;
	}
	
	public static String getCommericalInitProjects(){
		return "../webapps/" + serverName + "/data/initprojects_commerical/";
	}
	
	public static String getInitFlag(){
		return initFlag;
	}
	public static String getEditPreview(String userName,String prid){
		String path = editPathRelative+userName+"/"+prid+"/preview";
		return path;
	}
	
	public static String getPublishPreview(String prid){
		String path = publishPathRelative + prid + "/preview/";
		return path;
	}
	
	public static String getUserInfo(String userName){
		String path = editPathRelative+userName+"/"+userName+".xml";
		return path;
	}
	
	public static String getPublishZip(boolean isAbsolute,String prid){
		String path = publishPathRelative + prid+".zip";
		if(isAbsolute){
			path =  publishPathAbsolute + prid+".zip";
		}
		return path;
	}
	
	public static String getPublishUnzip(boolean isAbsolute,String prid){
		String path = publishPathRelative + prid;
		if(isAbsolute){
			path =  publishPathAbsolute + prid;
		}
		return path;
	}
	
	/**
	 * get content from edit preview
	 * @param type 1 root 2 project html 3 page html 4 css 5 js 6 images
	 * @param userName
	 * @param prid
	 * @return
	 */
	public static String getEditPreviewPath(boolean isAbsolute ,int type,String userName,String prid,String pid,long timestamp,String sig ){
		String previewPath = "";
		//true, 2, userName, prid, "", 0, ""
		if(sig.equals("single")){
			previewPath = editPathRelative +userName+"/"+prid+"/previewSingle";
			if(isAbsolute){
				previewPath = ServletActionContext.getServletContext().getContextPath()+"/msgResource/edit/"+userName+"/"+prid+"/previewSingle";
//				previewPath = "/data/projects/edit/"+userName+"/"+prid+"/preview";
			}
		}else{
			previewPath = editPathRelative +userName+"/"+prid+"/preview";
			if(isAbsolute){
				previewPath = ServletActionContext.getServletContext().getContextPath()+"/msgResource/edit/"+userName+"/"+prid+"/preview";
//				previewPath = "/data/projects/edit/"+userName+"/"+prid+"/preview";
			}
		}
		if(type==1){
			return previewPath;
		}
		else if(type==2){
			previewPath = previewPath +"/project.html";
		}
		else if(type == 3){
			previewPath = previewPath +"/page-"+pid+"("+timestamp+").html";
		}
		else if(type == 4){
			previewPath = previewPath + "/css/";
		}
		else if(type == 5){
			previewPath = previewPath + "/js/";
		}
		else if(type == 6){
			previewPath = previewPath + "/images/";
		}
		else if(type==7){
			previewPath = previewPath +"/projectP.html";
		}
		return previewPath;
	}
	
	public static String getPublishPreviewPath(boolean isAbsolute ,int type,String userName,String prid,String pid,long timestamp,String sig ){
		String previewPath = "";
		if(sig.equals("singleQ")){
			previewPath = publishPathRelative+prid+"/previewSingle";
			if(isAbsolute){
				previewPath = host+"/"+serverName+"/data/projects/publish/"+prid+"/previewSingle";
			}
		}else{
			previewPath = publishPathRelative +prid+"/preview";
			if(isAbsolute){
				previewPath = host+"/"+serverName+"/data/projects/publish/"+prid+"/preview";
			}
		}
		
		if(type==1){
			return previewPath;
		}
		else if(type==2){
			previewPath = previewPath +"/project.html";
		}
		else if(type == 3){
			previewPath = previewPath +"/page-"+pid+"("+timestamp+").html";
		}
		else if(type == 4){
			previewPath = previewPath + "/css/";
		}
		else if(type == 5){
			previewPath = previewPath + "/js/";
		}
		else if(type == 6){
			previewPath = previewPath + "/images/";
		}
		else if(type==7){
			previewPath = previewPath +"/projectP.html";
		}
		return previewPath;
	}
	
	public static String getTemplatePreviewPath(boolean isAbsolute ,int type,String userName,String prid,String pid,long timestamp){
		String previewPath = templatePathRelative +prid+"/preview";
		if(isAbsolute){
			previewPath = host+"/"+serverName+"/data/projects/template/"+prid+"/preview";
//			previewPath = "/data/projects/edit/"+userName+"/"+prid+"/preview";
		}
		if(type==1){
			return previewPath;
		}
		else if(type==2){
			previewPath = previewPath +"/project.html";
		}
		else if(type == 3){
			previewPath = previewPath +"/page-"+pid+"("+timestamp+").html";
		}
		else if(type == 4){
			previewPath = previewPath + "/css/";
		}
		else if(type == 5){
			previewPath = previewPath + "/js/";
		}
		else if(type == 6){
			previewPath = previewPath + "/images/";
		}
		return previewPath;
	}
	public static String getDownloadPath(String str){
		return "/"+str;
	}
	public static String getModelPath(int type){
		String previewPath = modelPath +"project";
		
//		if(type==1){
//			
//		}
		if(type==2){
			previewPath = previewPath +"/project.html";
		}
		else if(type == 3){
			previewPath = previewPath +"/page.html";
		}
		if(type == 4){
			previewPath = previewPath + "/css/";
		}
		else if(type == 5){
			previewPath = previewPath + "/js/";
		}
		else if(type == 6){
			previewPath = previewPath + "/images/";
		}
		return previewPath;
	}
	
	public static String getModelComponentPath(String type) {
		String componentPath = modelPath+"components/";
		if(type.equals("1")){
			componentPath=componentPath+"images/comp.html";
		}
		else if(type.equals("2")){
			componentPath=componentPath+"media/comp.html";
		}
		else if(type.equals("3")){
			componentPath=componentPath+"rect/comp.html";
		}
		else if(type.equals("4")){
			componentPath=componentPath+"scrollImages_accordion/comp.html";
		}
		else if(type.equals("5")){
			componentPath=componentPath+"text/comp.html";
		}
		else if(type.equals("25")){
			componentPath=componentPath+"text2/comp.html";
		}
		else if(type.equals("6")){
			componentPath=componentPath+"horizonLine/comp.html";
		}
		else if(type.equals("7")){
			componentPath=componentPath+"verticalLine/comp.html";
		}
		else if(type.equals("8")||type.equals("18")){ // “18”-->PPT
			componentPath=componentPath+"scrollImages_swipe/comp.html";
		}
		else if(type.equals("9")){
			componentPath=componentPath+"scrollImages_wander/comp.html";
		}
		else if(type.equals("10")){
			componentPath=componentPath+"scrollImages_3D/comp.html";
		}
		else if(type.equals("11")){
			componentPath=componentPath+"flash/comp.html";
		}
		else if(type.equals("12")){
			componentPath=componentPath+"powerpoint/comp.html";
		}
		else if(type.equals("13")){
			componentPath=componentPath+"webpage/comp.html";
		}
		else if(type.equals("14")){
			componentPath=componentPath+"streaming/comp.html";
		}
		else if(type.equals("14honghestream")){  // 鸿合流媒体
			componentPath=componentPath+"streaming/honghecomp.html";
		}
		else if(type.equals("15")){
			componentPath=componentPath+"excellentWorks/comp.html";
//			FileUtil.copyFolder(modelPath+"/excellentWorks/images/", "../webapps/ManagementCenter/data/");
		}
		else if(type.equals("16")){
			componentPath=componentPath+"time/comp.html";
		}
		else if(type.equals("19")){
			componentPath=componentPath+"weather/comp.html";
		}
		// 倒计时功能
		else if(type.equals("26")){  // 按天计算
			componentPath=componentPath+"countdown/compymd.html";
		}
		else if(type.equals("26tymd")){  // 按天计算
			componentPath=componentPath+"countdown/compymd.html";
		}
		else if(type.equals("26tymdhms")){ // 按天时分秒计算
			componentPath=componentPath+"countdown/compymdhms.html";
		}
		else if(type.equals("22")){
			componentPath=componentPath+"courseList/comp.html";
		}
		else if(type.equals("27")){
			componentPath=componentPath+"classdynamics/comp.html";
		}
		else if(type.equals("28")){
			componentPath=componentPath+"notice/comp.html";
		}
        return componentPath; 
	}
	
	public static String getFtpPath(Boolean isAbsolute,String prid,String pid,String bid){
		String path = "http://"+ serverIp+ftpPath+prid+"-"+pid+"-"+bid;
		if(!isAbsolute){
			path = "../webapps"+ftpPath+prid+"-"+pid+"-"+bid+"/";
		}
		return path;
	}
	
	
}
