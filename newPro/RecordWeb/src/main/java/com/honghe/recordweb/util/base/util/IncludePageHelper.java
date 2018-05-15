package com.honghe.recordweb.util.base.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

public class IncludePageHelper {
	private static final Logger logger = Logger
			.getLogger(IncludePageHelper.class);
	public static String getEasyuiTheme(HttpServletRequest request){
		//默认的主题，我们可以使用除官方支持以外的其他扩展主题
		String easyuiTheme = "default";
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		if (cookieMap.containsKey("easyuiTheme")) {
			Cookie cookie = (Cookie) cookieMap.get("easyuiTheme");
			easyuiTheme = cookie.getValue();
		}
		logger.info("来访客户端的主题是："+easyuiTheme);
		return easyuiTheme;
	}
	
	public static String getJqueryVersion(HttpServletRequest request,
			HttpSession session) {
		String jqueryVersion = null;
		String userAgent = request.getHeader("User-Agent");
		logger.info("来访客户端的浏览器信息是："+userAgent);
		// version of IE less than 9.0
		if (StringUtils.indexOfIgnoreCase(userAgent, "MSIE") > -1) {
			boolean isIe6 = (StringUtils.indexOfIgnoreCase(userAgent, "MSIE 6") > -1);
			boolean isIe7 = (StringUtils.indexOfIgnoreCase(userAgent, "MSIE 7") > -1);
			boolean isIe8 = (StringUtils.indexOfIgnoreCase(userAgent, "MSIE 8") > -1);
			if (isIe6 || isIe7 || isIe8) {
				jqueryVersion = ConfigUtil.get("jqueryOldVersion");
			}
			else {
				jqueryVersion = ConfigUtil.get("jqueryOldVersion");
			}
		} else {
			jqueryVersion = ConfigUtil.get("jqueryNewVersion");
		}
		logger.info("来访客户端选择的jquery版本是"+jqueryVersion);
		return jqueryVersion;
	}
	
	public static  String getEasyuiVersion(){ 
		return ConfigUtil.get("easyuiVersion");
	}
	
	//除了zh_cn和zh_tw,pt_br,sv_SE外，其余都是按照语言取的
	public static String getEasyuiLocale(HttpServletRequest request,HttpSession session){
		String localeLanUrl = null;
		ActionContext actionContext = ActionContext.getContext();
		String defaultContury = actionContext.getLocale().getCountry();
		String defaultLan = actionContext.getLocale().getLanguage();// zh
		String defaultI18nLan = null;
		String localeLan = null;
		if (defaultLan.equalsIgnoreCase("zh")
				|| defaultLan.equalsIgnoreCase("pt")
				|| defaultLan.equalsIgnoreCase("sv")) {
			defaultI18nLan = defaultLan + "_" + defaultContury;// CN
		} else {
			defaultI18nLan = defaultLan.toLowerCase();
		}
		localeLanUrl = (String) session.getAttribute("request_locale");// zh_CN

		if (StringUtils.isEmpty(localeLanUrl)) {
			localeLan = defaultI18nLan;
		} else {
			if (localeLanUrl.startsWith("zh") || localeLan.startsWith("pt")
					|| localeLan.startsWith("sv")) {
				localeLan = localeLanUrl;
			} else {
				localeLan = StringUtils.substringBefore(localeLanUrl, "_");
			}
		}
		logger.info("来访客户端选择的本地化easyui是：" + localeLan);
		return localeLan;
	}
}
