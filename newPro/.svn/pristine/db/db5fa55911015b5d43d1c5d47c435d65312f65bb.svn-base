package com.honghe.recordweb.service.frontend.news;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.honghe.recordweb.action.frontend.news.entity.Program;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class File2DocumentUtils {
	static Logger logger = Logger.getLogger(File2DocumentUtils.class);

	/**
	 * todo 加注释
	 * @param savePath
	 * @return
	 */
	public static Program parseProgramXml(String savePath){
		 SAXReader sax = new SAXReader();
		 FileInputStream inputStream=null;
		try { 
			inputStream=new FileInputStream(savePath);
			org.dom4j.Document xmlDoc = sax.read(inputStream); 
	    	Program program = new Program();
	        Element root = xmlDoc.getRootElement();//根节点  
	        Element namElement=root.element("name");
	        program.setPrid(namElement.getTextTrim());
	        
	        Element createElement=root.element("username");
	        program.setCreatename(createElement==null?"":createElement.getTextTrim());
	        
	        Element curIdx = root.element("curIdx");
	        
	        program.setCurIdx(curIdx.getText());
	        Element w = root.element("w");
	        program.setW(w.getText());
	        
	        Element h= root.element("h");
	        program.setH(h.getText());
	        
	        Element updateTime= root.element("updateTime");
	        program.setUpdateTime(updateTime.getText());
	        
	        Element state = root.element("state");
	        program.setState(state.getText());
	        
	        Element theme = root.element("theme");
	        program.setTheme(theme.getText());
	        
	        Element note = root.element("note");
	        program.setNote(note.getText());
	        
	        program.setId(Integer.parseInt(getDocumentId(savePath)));
	        
	        Element isEdit = root.element("isEdit");
	        program.setIsEdit(isEdit.getTextTrim().trim());
	        
	        Element pageSequence = root.element("pageSequence");
	        
	        List<String> pageList = new ArrayList<String>();
	       
	        if(pageSequence!=null){
				Iterator<?> iterator = pageSequence.elementIterator();
				while(iterator.hasNext()){
					Element pageElement = (Element) iterator.next();
					pageList.add(pageElement.getText());
				}
			}
	        program.setPageList(pageList);
	        return program;
	    } catch (Exception e) {  
	        logger.error("解析文件错误:",e);
	    } 
		finally{
			try {
				if(null!=inputStream){
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("读文件失败", e);
			}
		}
	    return null;
	}

	/**
	 * todo 加注释
	 * @param program
	 * @return
	 */
	public static Document generateDocument(Program program){
		Document document= new Document();
		document.add(new Field("name", program.getPrid(), Store.YES,
				Index.ANALYZED));
		document.add(new Field("title", program.getPrid(), Store.YES,
				Index.ANALYZED));
		document.add(new Field("createname", program.getCreatename(), Store.YES,
				Index.ANALYZED));
		document.add(new Field("id", program.getId()+"", Store.YES,
				Index.NOT_ANALYZED));
//		System.out.println(program.getId()+"");
		document.add(new Field("note", program.getNote(), Store.YES,
				Index.ANALYZED));
		document.add(new Field("theme", program.getTheme(), Store.YES,
				Index.ANALYZED));
		document.add(new Field("w", program.getW(), Store.YES,
				Index.NOT_ANALYZED));
		document.add(new Field("h", program.getH(), Store.YES,
				Index.NOT_ANALYZED));
		if(Integer.parseInt(program.getW())>Integer.parseInt(program.getH())){
			document.add(new Field("resolution", program.getW()+"x"+program.getH(), Store.YES,
					Index.NOT_ANALYZED));
		}
		else {
			document.add(new Field("resolution", program.getH()+"x"+program.getW(), Store.YES,
					Index.NOT_ANALYZED));
		}
		document.add(new Field("state", program.getState(),Store.YES,Index.NOT_ANALYZED));
		document.add(new Field("isEdit", program.getIsEdit().trim(), Store.YES,
				Index.NOT_ANALYZED));
		document.add(new Field("updateTime", program.getUpdateTime(), Store.YES,
				Index.ANALYZED));
		if(Integer.parseInt(program.getW())>Integer.parseInt(program.getH())){
			document.add(new Field("showcategory", "横屏", Store.YES,
					Index.ANALYZED));
		}
		else {
			document.add(new Field("showcategory", "竖屏", Store.YES,
					Index.ANALYZED));
		}
		return document;
	}

	/**
	 * todo 加注释
	 * @param path
	 * @return
	 */
	public static String getDocumentId(String path){
		path=path.replaceAll("\\\\","/");
		String[] pathArr=path.split("/");
		return pathArr[pathArr.length-2];
	}

	/**
	 * todo 加注释
	 * @param rootPath
	 * @return
	 */
	public static Document getDocument(String rootPath) {
		Program program=parseProgramXml(rootPath);
		Document document=generateDocument(program);
		return document;
	}

	/**
	 * todo 加注释
	 * @param rootPath
	 * @param name
	 * @return
	 */
	public static Document getDocument(String rootPath,String name) {
		Program program=parseProgramXml(rootPath);
		if(name.startsWith("publish")||name.startsWith("template"))
			program.setIsEdit("0");
		Document document=generateDocument(program);
		return document;
	}

}
