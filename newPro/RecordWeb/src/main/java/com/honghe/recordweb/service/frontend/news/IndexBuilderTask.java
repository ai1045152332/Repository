package com.honghe.recordweb.service.frontend.news;

import net.contentobjects.jnotify.JNotify;
import org.apache.lucene.document.Document;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.TimerTask;

public class IndexBuilderTask extends TimerTask{
	private String name;
	private String rootPath;
	private int jnotifyType;
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexBuilderTask.class);
	
	public IndexBuilderTask(String name,String rootPath,int jnotifyType){
		this.name=name;
		this.rootPath=rootPath;
		this.jnotifyType=jnotifyType;
	}

	/**
	 * todo 加注释
	 */
	public void job(){
		String docId="";
		Document document=null;
		switch (this.jnotifyType) {
		case JNotify.FILE_CREATED:
			document=File2DocumentUtils.getDocument(rootPath+name,name);
			logger.info("开始增加索引："+document.get("name"));
			IndexBuilder.getInstance().add(document);
			break;
		case JNotify.FILE_DELETED:
			docId=File2DocumentUtils.getDocumentId(rootPath+name);
			logger.info("开始删除索引："+docId);
			IndexBuilder.getInstance().delete(docId,name);		
			break;
		case JNotify.FILE_MODIFIED:
			File file=new File(rootPath+name);
			if(file.exists()){
				document=File2DocumentUtils.getDocument(rootPath+name,name);
				logger.info("开始更新索引："+document.get("id")+"********");
				IndexBuilder.getInstance().update(document,name);
				logger.info("完成更新索引："+document.get("id")+"********");
			}
			break;
		case JNotify.FILE_RENAMED:
			break;
		default:
			break;
		}
	}

	@Override
	public void run() {
		job();
//		new IndexController().increaseSemaphore();
//		IndexDeleter.getInstance().forceDelete();
//    	IndexBuilder.getInstance().buildIndex();
    	Query.updateIndexSearcher();
	}
	
}
