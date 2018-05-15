package com.honghe.recordweb.service.frontend.news;

import com.honghe.recordweb.action.frontend.news.entity.Program;
import org.apache.lucene.document.Document;

public class Document2Program {
	/**
	 * todo 加注释
	 * @param entry
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public static Program convert(HighlightComponent entry,String username) throws Exception{
		Document document=entry.getDocument();
		Program program=new Program();
		program.setCreatename(document.get("createname"));
		program.setCurIdx("");
		//program.setEditstate("");
		//program.setH(document.get("h"));
		program.setId(Integer.parseInt(document.get("id")));
		//program.setName(entry.getHightlight());
		//program.setNote(document.get("note"));
		//program.setTitle(document.get("title"));
		//program.setPageList(null);
		program.setPrid(document.get("id"));
		//program.setState(document.get("state"));
		//program.setTheme(document.get("theme"));
		//program.setIsEdit(document.get("isEdit"));
		//String thumbPath=new ProjectProgramManagerImpl().getDependThumb(document.get("createname"),document.get("state"),document.get("id"));
		//if(null==thumbPath)
		//	return null;
		//program.setThumb(thumbPath);
		//program.setUpdateTime(document.get("updateTime"));
		//program.setW(document.get("w"));
		return program;
	}
}
