package com.honghe.recordweb.service.frontend.news;


import org.apache.lucene.document.Document;

public class HighlightComponent {
	private Document document;
	private String hightlight;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getHightlight() {
		return hightlight;
	}
	public void setHightlight(String hightlight) {
		this.hightlight = hightlight;
	}

	/**
	 * todo 加注释
	 * @param document
	 * @param hightlight
	 */
	public HighlightComponent(Document document,String hightlight) {
		this.document=document;
		this.hightlight=hightlight;		
	}
	
}
