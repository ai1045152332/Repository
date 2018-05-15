package com.honghe.recordhibernate.entity.tools;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang3.StringUtils;

public class GlobalParameter {

	public static final String PROJECT_PATH = "hhtc";
	
	public static final String PACKAGE_PATH="../package.txt";
	
	public static boolean IsCampusVersion=true;
	
	static{
		File file=new File(PACKAGE_PATH);
		String filecontent;
		if(file.exists()){
			filecontent= FileUtil.getFileContent(PACKAGE_PATH, "utf-8");
			if(!StringUtils.isEmpty(filecontent)){
				//1为商业，其他任何情况为教育
				if(filecontent.contains("1")){
					IsCampusVersion = false;
				}
			}
		}
		
	}

	// 以下7个值是设置上传文件的类型的
	public static final int floder=0;
	public static final int video = 1;
	public static final int word = 2;
	public static final int excel = 3;
	public static final int img = 4;
	public static final int pdf = 5;
	public static final int ppt = 6;
	public static final int flash=7;
	public static final int audio=8;
	
	public static final short STATE_PROJECT_CREATE = 1;
	public static final short STATE_PROJECT_SUBMIT = 2;
	public static final short STATE_PROJECT_PASS = 3;
	public static final short STATE_PROJECT_REFUSE = 4;
	public static final short STATE_PROJECT_PUBLISH = 5;
	public static final short STATE_PROJECT_TEMPLATE = 100; //state=0 模板标识 add 20141014 lihuimin
	public static final short STATE_PROJECT_SYSTEMPLATE=200;//state=200 系统模板
	public static final Short STATE_PROJECT_PUBLISH_DELETE = 10;
	
	public static final int DEFAULT_PROJECT_PAGE_DURATION=300;
	
	public static final String PROJECT_RANGE_UNASSIGNED="1";
	public static final String PROJECT_RANGE_ASSIGNED="2";
	public static final String PROJECT_RANGE_ALL="3";
	
	public static final String RESOURCE_VITUALPATH="/root/";

	
	
	public static String getExtName(int type,String suffix){
		String fileName = "";
		if(type==GlobalParameter.img ){
			if(suffix.equals(".gif")||suffix.equals("gif")){
				fileName="image.gif";
			}
			else{
				fileName="image.png";
			}
		}
		else if(type==GlobalParameter.video){
			fileName="video.mp4";
		}
		else if(type==GlobalParameter.flash){
			fileName="flash.swf";
		}
		else if(type==GlobalParameter.ppt){
			fileName="ppt.html";
		}
		else if(type==GlobalParameter.word){
			fileName = "thumb.png";
		}
		return fileName;
	}
	
	public static String getLocalAddr(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "255.255.255.255";
		}
		String ip=addr.getHostAddress().toString();//获得本机IP
		return ip;
	}
	
	//true:campus version
	//false:commerical version
	public static boolean isCampusVersion(){
		return IsCampusVersion;
	}
	
	
}
