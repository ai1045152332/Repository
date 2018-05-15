package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordhibernate.entity.tools.SearchPathTools;
import com.honghe.recordweb.action.frontend.news.entity.Program;
import net.contentobjects.jnotify.JNotify;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author hucl
 */
public class IndexBuilder {
	private static final Logger logger = Logger.getLogger(IndexBuilder.class);
	//让默认的stopword set为空
	static Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36,new HashSet<String>());
	static IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36,
			analyzer);
	public static Directory directory = new RAMDirectory();
	private static IndexBuilder indexBuilder;
	private static boolean isBuilding = false;

	/**
	 * todo 加注释
	 * @return
	 */
	public static synchronized IndexBuilder getInstance() {
		if (indexBuilder == null) {
			indexBuilder = new IndexBuilder();
		}
		return indexBuilder;
	}

	/**
	 * todo 加注释
	 * @return
	 * @throws Exception
	 */
	public IndexWriter getIndexWriter() throws Exception {
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36,
				analyzer);
		return new IndexWriter(directory, config);
	}

	/**
	 * 获取doclist
	 * @param folderPath
	 * @return
	 */
	private List<Document> getDocuments(String folderPath) {
		List<Document> docList = new ArrayList<Document>();
		File file = new File(folderPath);
		if (!file.exists() || !file.isDirectory()) {
			logger.error("没有找到project的路径");
			return null;
		}
		File[] categoryFiles=file.listFiles();
		for(File categoryFile:categoryFiles){
			if("edit".equalsIgnoreCase(categoryFile.getName())){
				File[] projectFiles = categoryFile.listFiles();
				if (projectFiles == null || projectFiles.length < 1)
					return null;
				// 为没一个用户创建document
				for (File managerRoot : projectFiles) {
					if (managerRoot.isDirectory()) {
						List<Document> userDocList = generateDocForUser(managerRoot,true);
						if (userDocList == null || userDocList.size() < 1)
							continue;
						docList.addAll(userDocList);
					}
				}
			}
			else if("publish".equalsIgnoreCase(categoryFile.getName())||"template".equalsIgnoreCase(categoryFile.getName())){
				List<Document> userDocList = generateDocForUser(categoryFile.getAbsoluteFile(),false);
				if (userDocList == null || userDocList.size() < 1)
					continue;
				docList.addAll(userDocList);
			}
//			else if("template".equalsIgnoreCase(categoryFile.getName())){
//				
//			}
		}
		return docList;
	}

	/**
	 * todo 加注释
	 * @param projectFile
	 * @return
	 */
	private Program getProInfo(File projectFile) {
		String proXml = projectFile.getAbsolutePath() + File.separator
				+ "Project.xml";
		File proFile=new File(proXml);
		if(!proFile.exists()){
			logger.error("节目文件不存在："+proXml);
			return null;
		}
		Program program = File2DocumentUtils.parseProgramXml(proXml);
		program.setId(Integer.parseInt(projectFile.getName().trim()));
		return program;
	}

	/**
	 * todo 加注释
	 * @param managerRoot
	 * @param editFlag
	 * @return
	 */
	private List<Document> generateDocForUser(File managerRoot,boolean editFlag) {
		List<Document> userDocList = new ArrayList<Document>(0);
		String username = managerRoot.getName();
		String rootPath = managerRoot.getAbsolutePath();
		File file = new File(rootPath);
		if (!file.exists() || !file.isDirectory()) {
			logger.error("没有找到project的路径:" + username);
			return null;
		}
		File[] projectFiles = file.listFiles();
		if (projectFiles == null || projectFiles.length < 1)
			return null;
		for (File projectFile : projectFiles) {
			if (!projectFile.isDirectory()) {
				continue;
			}
			Program program = getProInfo(projectFile);
			if(null==program){
				continue;
			}
			if(!editFlag){
				program.setIsEdit("0");
			}
//			program.setCreatename(username);
			// 为节目生成Document
			userDocList.add(File2DocumentUtils.generateDocument(program));
		}
		return userDocList;
	}

	/**
	 * todo 加注释
	 * @param docList
	 * @throws Exception
	 */
	private void generateIndex(List<Document> docList) throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		if(docList!=null){
			indexWriter.addDocuments(docList);
		}
		indexWriter.close();
	}

	/**
	 * todo 加注释
	 */
	public void buildIndex() {
		logger.info("开始生成节目索引");
		List<Document> docList = getDocuments(SearchPathTools.projectPathRelative);
		try {
			generateIndex(docList);
			logger.info("完成生成节目索引");
		} catch (Exception e) {
			logger.error("生成index错误", e);
		}
	}

	/**
	 * todo 加注释
	 * @param savepath
	 */
	public synchronized void updateIndex(String savepath) {
		Document document=null;
		File file=new File(savepath);
		savepath=savepath.replace("\\\\", "/");
		//String rootpath=SearchPathTools.projectPathRelative.replace("\\\\", "/");
		String rootpath= ServletActionContext.getServletContext().getContextPath()+"/msgResource/";
		String name=StringUtils.substringAfter(savepath,rootpath);
		if(file.exists()){
			document=File2DocumentUtils.getDocument(savepath);
			logger.info("开始更新索引："+document.get("id")+"********");
//			IndexBuilder.getInstance().update(document,name);
			//必须更新query中的indexreader
			new IndexController().increaseSemaphore(name,rootpath,JNotify.FILE_MODIFIED);
			logger.info("完成更新索引："+document.get("id")+"********");
		}
	}

	/**
	 * todo 加注释
	 * @param document
	 * @param name
	 */
	public synchronized void update(Document document,String name) {
		try {
			IndexWriter indexWriter = getIndexWriter();
			BooleanQuery query = new BooleanQuery();
			String docId=document.get("id");
			Analyzer analyzer = IndexBuilder.analyzer;
			if(name.startsWith("edit")){
				query.add(new TermQuery(new Term("isEdit", "1")), Occur.MUST);
			}
			else if(name.startsWith("publish")){
				query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
			}
			else if(name.startsWith("template")){
				query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
			}
//			query.add(new TermQuery(new Term("id", docId)), Occur.MUST);
//			indexWriter.updateDocument(new Term("id", docId),
//					document);
			//先删除
			query.add(new TermQuery(new Term("id", docId)), Occur.MUST);
			logger.info("更新过程删除索引");
			indexWriter.deleteDocuments(query);
			//后添加
			logger.info("更新过程添加索引");
			indexWriter.addDocument(document);
			indexWriter.commit();
			indexWriter.optimize();
			indexWriter.close();
			Query.updateIndexSearcher();
		} catch (Exception e) {
			logger.error("lucene更新索引失败", e);
		}
	}

	/**
	 * todo 加注释
	 * @param document
	 */
	public synchronized void add(Document document) {
		try {
			logger.info("新增索引:"+document.get("name"));
			IndexWriter indexWriter = getIndexWriter();
			indexWriter.addDocument(document);
			indexWriter.commit();
			indexWriter.optimize();
			indexWriter.close();
		} catch (Exception e) {
			logger.error("lucene增加索引失败", e);
		}
	}

	/**
	 * 实时删除索引
	 * @param docId
	 * @param name
	 */
	public synchronized void delete(String docId,String name) {
		logger.info("删除索引:"+name);
		try {
			IndexWriter indexWriter = getIndexWriter();

			BooleanQuery query = new BooleanQuery();
			// BooleanQuery booleanQuery = new BooleanQuery();
			Analyzer analyzer = IndexBuilder.analyzer;
			if(name.startsWith("edit")){
				query.add(new TermQuery(new Term("isEdit", "1")), Occur.MUST);
			}
			else if(name.startsWith("publish")){
				query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
			}
			else if(name.startsWith("template")){
				query.add(new TermQuery(new Term("isEdit", "0")), Occur.MUST);
			}
			query.add(new TermQuery(new Term("id", docId)), Occur.MUST);
			indexWriter.deleteDocuments(query);
			indexWriter.commit();
			indexWriter.optimize();
			indexWriter.close();
		} catch (Exception e) {
			logger.error("lucene删除索引失败", e);
		}
	}
}
