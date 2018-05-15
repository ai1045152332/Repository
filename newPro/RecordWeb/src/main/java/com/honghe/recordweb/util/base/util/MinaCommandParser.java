package com.honghe.recordweb.util.base.util;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.helpers.DefaultHandler;

public class MinaCommandParser extends DefaultHandler {
	public static Map<String, String> getMsgBodyElement(String msgBody)
			throws DocumentException, UnsupportedEncodingException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new ByteArrayInputStream(msgBody.getBytes("UTF-8")));

		Element root = document.getRootElement();
		Map<String, String> msgElementsMap = new HashMap<String, String>(0);
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element messageBody = (Element) iterator.next();
			msgElementsMap.put(messageBody.getQName().getName(), messageBody.getTextTrim());
		}
		return msgElementsMap;
	}
	
	public static void main(String[] args) throws DocumentException, UnsupportedEncodingException {
		Map msgElementsMap=getMsgBodyElement("<root><screen-shot-file>截屏图片文件名</screen-shot-file></root>");
		System.out.println(msgElementsMap.get("screen-shot-file"));
	}
}
