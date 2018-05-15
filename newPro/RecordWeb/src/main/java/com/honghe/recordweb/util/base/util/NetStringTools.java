package com.honghe.recordweb.util.base.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class NetStringTools {
	
	public static String FindMatcher(String content,String regextag){
        Pattern p = Pattern.compile(regextag);
        Matcher m = p.matcher(content);
        if(m.find()){
        	String res = m.group();
        	return res;
        }
        return null;   
    }
	
	public static String filterFigure(String content,String regextag){
		Pattern p = Pattern.compile(regextag);
        Matcher m = p.matcher(content);
        if(m.find()){
        	String res = m.group();
        	return res.replaceAll(regextag, "");
        }
        return content;
	}
	
	public static String regexMatcher(String content,String regextag,String replace){
        Pattern p = Pattern.compile(regextag);
        Matcher m = p.matcher(content);
        if(m.find()){
//        	String res = m.group();
//        	System.out.println(res);
        	return content.replaceAll(regextag, replace);
        }
        return null;   
    }
	
	public static String regexMatcherContent(String content,String regextag,String replace){
		Pattern p = Pattern.compile(regextag);
        Matcher m = p.matcher(content);
        if(m.find()){
        	String res = m.group();
        	return res.replace(replace, "");
        }
        return null;  
	}
	
	 public static String replaceBlank(String str) {
		 String dest = "";
	     if (str!=null) {
	    	 Pattern p = Pattern.compile("\t|\r|\n");
	         Matcher m = p.matcher(str);
	         dest = m.replaceAll("");
	     }
	     return dest;
	}
	
	public static String appendRegex(String content,String regextag,String replace){
        Pattern p = Pattern.compile(regextag);
        Matcher m = p.matcher(content);
        if(m.find()){
        	String res = m.group();
//        	System.out.println("res : "+res);
//        	System.out.println(regextag);
//        	System.out.println(replace);
//        	System.out.println("content : "+content);
        	String result = content.replace(res, replace);
//        	System.out.println("result : "+result);
        	return result;
        }
        return null;   
    }
	
	public static String convertFormat(List<String> tags, String split){
		String result = null;
		for(int i = 0; i < tags.size(); i++){
			if(result == null){
				result = tags.get(i);
			} 
            else
            	result = split+tags.get(i);
//                result = (new StringBuilder(String.valueOf(result))).append(split).append((String)tags.get(i)).toString();
		}
		return result;
	}
	
	public static List<String> RegexMatcherList(String content, String Regextag, String replaceRegexTag, boolean isTranscod){
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(Regextag);
        for(Matcher m = p.matcher(content); m.find();)
            if(isTranscod){
            	result.add(unescape(m.group().replaceAll(replaceRegexTag, "").replace("\\", "%")));
            }
            else{
            	result.add(m.group().replaceAll(replaceRegexTag, ""));
            }
        return result;
    }
	
	public static String Html_ConvertHtmlUnicodeToChinese(String oriStr){
        String regex = "&#[0-9]{4,5};";
        Pattern pt = Pattern.compile(regex, 2);
        Matcher mt = pt.matcher(oriStr);
        StringBuffer sb = new StringBuffer();
        for(boolean result = mt.find(); result; result = mt.find()){
            String item = mt.group();
            String iNum = item.substring(2, item.length() - 1);
            int num = String_ConvertToInt(iNum, 0);
            if(num > 4000 && num < 65500)
                try{
                    mt.appendReplacement(sb, ""+num);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
        }

        mt.appendTail(sb);
        return sb.toString();
    }
	
	public static int String_ConvertToInt(String intString, int defaultExceptionValue){
        int num = 0;
        if(intString != null)
            try{
                num = Integer.parseInt(intString.trim());
            }
            catch(Exception ex){
                num = defaultExceptionValue;
            }
        return num;
    }
	
	public static String getRedirectLocation(String content){
        String regex = "location.replace.*?\\)";
        String replace_regex = "location.replace\\('|'\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        String location = null;
        if(matcher.find())
            location = matcher.group().replaceAll(replace_regex, "");
        return location;
    }
	
	public static String unicode2Chinese(String src,boolean flag){
		//if src = "\u53d1\u5e03\u5668",鐩存帴杩斿洖
		if(flag){
			return src;
		}
		//if src = "\\u53d1\\u5e03\\u5668",澶勭悊鍚庤繑鍥�
//		src = "\\u53d1\\u5e03\\u5668";
		
		String result = "";
		for(int i=0;i<src.length()/6;i++){
//			String s = src.substring(src.indexOf("\\u",i*6)+2, src.indexOf("\\u",i*6)+6);
			String str = src.substring(i*6+2,i*6+6);
			int in = Integer.parseInt(str, 16);
			char ch = (char) in;
			String sStr = String.valueOf(ch);
			result = result+sStr;
		}
		return result;
	}
	
	public static String chinese2Unicode(String src){
		char[] str = src.toCharArray();
		String result = "";
		for(int i=0;i<str.length;i++){
			int b = str[i];
			result=result+"\\u"+Integer.toHexString(b);
		}
		return result;
	}
	
	public static String escape(String src){
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for(int i = 0; i < src.length(); i++){
            char j = src.charAt(i);
            if(Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if(j < '\u0100'){
                tmp.append("%");
                if(j < '\020')
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else{
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }
	
	 public static String unescape(String src){
		 if(src == null) return src;
		 StringBuffer tmp = new StringBuffer();
		 tmp.ensureCapacity(src.length());
		 int lastPos = 0;
		 int pos = 0;
		 while(lastPos < src.length()) {
			 pos = src.indexOf("%u", lastPos);
			 if(pos == lastPos){
				 if(src.charAt(pos + 1) == 'u'){
					 char ch;
					 try{
						 ch = (char)Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					 }
					 catch(Exception ex){
						 ch = (char)Integer.parseInt("A", 16);
					 }
					 tmp.append(ch);
					 lastPos = pos + 6;
				 } 
				 else{
					char ch;
					try{
						ch = (char)Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					}
					catch(Exception ex){
						ch = (char)Integer.parseInt("A", 16);
					}
					tmp.append(ch);
                 	lastPos = pos + 3;
				 }
			 } else if(pos == -1){
        		 tmp.append(src.substring(lastPos));
        		 lastPos = src.length();
         }
         else{
             tmp.append(src.substring(lastPos, pos));
             lastPos = pos;
         }
		 }
		 return tmp.toString();
	 }

	 public static void main(String args[]) throws UnsupportedEncodingException{
		 String a = URLDecoder.decode("%2C", "GBK");
		 System.out.println("ss : "+a);
//		
//		System.out.println(chinese2Unicode("鎴戜互鎴戣鑽愯僵杈�"));
		
//		String mytext1 = URLEncoder.encode("://","utf-8");
//		System.out.println(mytext1);
//		String mytext2 = URLDecoder.decode("http://comment.ifeng.com/view.php?doc_url=http%3A%2F%2Fnews.ifeng.com%2Fsociety%2F1%2Fdetail_2012_10%2F31%2F18676004_0.shtml&doc_name=%E5%85%AC%E5%8F%B8%E4%B8%BA%E6%96%B9%E4%BE%BF%E5%89%8D%E5%91%98%E5%B7%A5%E4%B9%B0%E6%88%BF%E5%BC%80%E5%81%87%E9%AB%98%E8%96%AA%E8%AF%81%E6%98%8E+%E5%8F%8D%E8%A2%AB%E8%B5%B7%E8%AF%89%E8%A6%81%E6%B1%82%E5%85%91%E7%8E%B0&ishot=yes","utf-8");
//		System.out.println(mytext2);
//		 String aaa = "%u6da81.7%";
//		 String aaa = "\u62b1\u6b49\uff0c\u6b64\u5fae\u535a\u5df2\u88ab\u4f5c\u8005\u5220\u9664\u3002\u67e5\u770b\u5e2e\u52a9\uff1ahttp://t.cn/zWSudZc";
//		 System.out.println(unescape(aaa));
//		 
//		 String ccc = "bb<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/af/cry.gif\" title=\"[琛癩\" alt=\"[琛癩\" type=\"face\" />vvv";
//		 ccc =  NetStringTools.RegexMatcher(ccc,"<img src=.*? />", "|");
//		 System.out.println(ccc.indexOf("<img src="));
//		 System.out.println(ccc);
		 
		 String ccc = "丁丁<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_org.gif\" title=\"[围观]\" alt=\"[围观]\" type=\"face\" /><img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/f2/wg_org.gif\" title=\"[围观]\" alt=\"[围观]\" type=\"face\" />sfdefdfdfdf";
		 System.out.println(ccc.indexOf("<img src="));
		 String cc = NetStringTools.regexMatcher(ccc, ".*?(<img src=.*?/>)+.*?", "<img src=.*?/>");
		 System.out.println(cc);
		 
		 String aaa = "<em rel=\"@mjaykong\">最神奇的心理学</em>: 韩剧有三宝：车祸、癌症、治不好；谎言有三宝：天长、地久、爱到老；型男有三宝：亲亲，抱抱，再推倒；周董有三宝：哎呦，不错，这个屌；大学生有三宝：复制、贴上、过就好；大学男生有三宝：联谊、泡妞、学妹好；大学女生有三宝：化妆、打扮、穿得少；更多精彩爆笑段子敬请收听【<em rel=\"@henlei\">其实很累了</em>";
		 aaa = aaa.replaceAll("<em rel=.*?>", "");
		 aaa= aaa.replaceAll("</em>", "");
		 
		 String ddds = "<img class='crs dn' crs='http://mat1.gtimg.com/www/mb/images/face/20.gif' title='偷笑'>加多宝妹子。您老学药剂学。哈哈";
		 System.out.println(ddds.replaceAll("<img class=.*?>", ""));
		 
		 
		String res = URLDecoder.decode(URLDecoder.decode("%2B", "UTF-8"), "UTF-8");
		System.out.println("res = " +res+"-");
		
		String aab = URLEncoder.encode(URLEncoder.encode("+", "UTF-8"), "UTF-8");
		System.out.println(aab);
		
		System.out.println(unicode2Chinese("\u8f6c\u53d1",true));
		System.out.println(unicode2Chinese("\u8f93\u5165\u53c2\u6570\u9519\u8bef",true));
	 }
}
