package com.honghe.recordweb.util.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;


public class GlobalFlag {
	private static Logger logger=Logger.getLogger(GlobalFlag.class);
	private static Map<String,String> flagMap=new HashedMap();
	private static List<String> exportlist = new ArrayList<String>();
	private static Map<String,String> publishMap = new HashMap<String, String>();
	private static Map<String,String> publishMapQ = new HashMap<String, String>();
	private static Map<String,String> copyMap = new HashMap<String, String>();
	public static void change(String flag,String ps){
		flagMap.put(flag,"finish_"+ps);
		logger.info(flag+"已经加入局容器中...");
	}
	public static String getVal(String flag){
		String str = flagMap.get(flag);
		logger.info(flag+"为"+str+"...");
		return str;
	}
	public static void pop(String flag){
		flagMap.remove(flag);
		logger.info(flag+"已经从全局容器中清除.");
	}
	public static void push(String flag,String value){
		if(value==""){
			value="process";
		}
		flagMap.put(flag, value);
		logger.info(flag+"&&"+value+"已经加入全局容器");
	}
	public static boolean haveFlag(String flag){
		logger.info("正在查询全局容器...");
		if(flagMap.get(flag)!=null){
			logger.info("查询全局容器完毕，容器中存在此flag...");
			return true;
		}else{
			logger.info("查询全局容器完毕，容器中不存在此flag...");
			return false;
		}
		
	}
	//************导出任务列表
	public static void exportPush(String flag){
		if(flag!=null&&!"".equals(flag)){
			exportlist.add(flag);
			logger.info("已加入");
		}
		
	}
	public static void exprotPop(String flag){
		if(flag!=null&&!"".equals(flag)){
			if(exportlist.contains(flag)){
				exportlist.remove(flag);
				logger.info("已清除");
			}
		}
		
		
	}
	public static int  getexportnum(){
		return exportlist.size();
	}
	public static boolean inprogress(String flag){
		
			if(exportlist.contains(flag)){
				logger.info("导出中...");
				return true;
			}
			logger.info("已经结束...");
			return false;
		
		
			
	}
	
	//************发布任务列表
		public static void publishPushQ(String flag){
			if(flag!=null&&!"".equals(flag)){
				publishMapQ.put(flag, "start");
			}
			
		}
		public static void publishPopQ(String flag){
			if(flag!=null&&!"".equals(flag)){
				if(publishMapQ.containsKey(flag)){
					publishMapQ.remove(flag);
				}
			}
		}
		
		public static String getPublishQ(String flag){
			if(flag!=null&&!"".equals(flag)){
				if(publishMapQ.get(flag) == null){
					return "";
				}
				return publishMapQ.get(flag).equals("")?"":publishMapQ.get(flag);
			}
			return "";
			
		}
		public static Map<String,String> getPublishMapQ(){
			return publishMapQ;
			
		}
	
	
	//************发布任务列表
	public static void publishPush(String flag){
		if(flag!=null&&!"".equals(flag)){
			publishMap.put(flag, "start");
		}
		
	}
	public static void publishPop(String flag){
		if(flag!=null&&!"".equals(flag)){
			if(publishMap.containsKey(flag)){
				publishMap.remove(flag);
			}
		}
	}
	
	public static String getPublish(String flag){
		if(flag!=null&&!"".equals(flag)){
			if(publishMap.get(flag) == null){
				return "";
			}
			return publishMap.get(flag).equals("")?"":publishMap.get(flag);
		}
		return "";
		
	}
	public static Map<String,String> getPublishMap(){
		return publishMap;
		
	}
	
	//************复制节目列表
		/*public static void copyPush(String flag){
			if(flag!=null&&!"".equals(flag)){
				copyMap.put(flag, "start");
			}
			
		}
		public static void copyPop(String flag){
			if(flag!=null&&!"".equals(flag)){
				if(publishMap.containsKey(flag)){
					copyMap.remove(flag);
				}
			}
		}
		
		public static String getCopy(String flag){
			if(flag!=null&&!"".equals(flag)){
				if(copyMap.get(flag) == null){
					return "";
				}
				return copyMap.get(flag).equals("")?"":copyMap.get(flag);
			}
			return "";
			
		}
		public static Map<String,String> getCopyMap(){
			return copyMap;
			
		}*/
}
