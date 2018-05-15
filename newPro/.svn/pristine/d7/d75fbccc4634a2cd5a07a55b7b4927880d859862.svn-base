package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.entity.tools.ConfigUtil;

import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class IndexController {
	static int controlSemaphore=0;
	static Map<String, Timer> indexTimerMap =new Hashtable<String, Timer>();
	static int builerDelayTime=Integer.parseInt(ConfigUtil.get("indexDelayTime"));

	/**
	 * todo 加注释
	 * @param key
	 * @param timer
	 */
	public synchronized void setIndexTimer(String key,Timer timer){
		if(indexTimerMap!=null&&indexTimerMap.size()>0){
			Timer indexTimer=indexTimerMap.get(key);
			if(indexTimer!=null)
				indexTimer.cancel();
			indexTimerMap.put(key, timer);
		}
		else{
			indexTimerMap.put(key, timer);
		}
	}
//	
//	public Timer getIndexTimer(){
////		Timer indexTimer;
////		if(indexTimerMap!=null&&indexTimerMap.size()>0){
////			indexTimer=indexTimerMap.get(0);
////		}
////		else{
////			indexTimer=null;
////		}
////		return indexTimer;
//		return null;
//	}
//	
	public synchronized void createOrUpdateIndex(boolean initFlag){
//	    if(controlSemaphore==0&&!initFlag){
//	    	return;
//	    }
//	    else{
//	    	controlSemaphore=0;
//	    	IndexDeleter.getInstance().forceDelete();
//	    	IndexBuilder.getInstance().buildIndex();
//	    }
	}

	/**
	 * todo 加注释
	 * @param name
	 * @param rootPath
	 * @param jnotifyType
	 */
	public synchronized void increaseSemaphore(String name,String rootPath,int jnotifyType){
		controlSemaphore++;
		Timer timer=new Timer();
		TimerTask timerTask=new IndexBuilderTask(name,rootPath,jnotifyType);
		timer.schedule(timerTask, builerDelayTime);
		setIndexTimer(jnotifyType+"_"+name,timer);
	}
	
	public synchronized void resetSemaphore(){
		controlSemaphore=0;
	}//todo 加注释
}
