package com.honghe.recordweb.service.frontend.news;


import com.honghe.recordweb.util.base.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

public class HighlightHelper {
	/**
	 * todo 加注释
	 * @param keyWords
	 * @param value
	 * @param stringBuffer
	 */
	public static void highlight(String keyWords,String value,StringBuffer stringBuffer){
		if(StringUtil.isEmpty(value)){
			return;
		}
		if(value.contains(keyWords)){
			String firstPart=StringUtils.substringBefore(value, keyWords);
			String lastPart=StringUtils.substringAfter(value, keyWords);
			stringBuffer.append(firstPart);
			stringBuffer.append("<font color=\"red\">");
			stringBuffer.append(keyWords);
			stringBuffer.append("</font>");
			highlight(keyWords,lastPart,stringBuffer);
		}
		else {
			stringBuffer.append(value);
		}
	}
}
