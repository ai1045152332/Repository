/*
 * Created on 2005-5-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.shop.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bomb
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class StringUtil {

	public static String NEW_PAGE = "NEWPAGE";

	public static String TAG_START = "TAGSTART";

	public static String TAG_END = "TAGEND";

	public static String context = "";

	public static float toFloat(String s) {
		try {
			return Float.parseFloat(s);
		} catch (Exception e) {
			return 0;
		}
	}

	public static double toDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int StringToId(String strId) {
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (Exception e) {
		}
		return id;
	}

	public static int toInt(String strId) {
		int id = -1;
		try {
			id = Integer.parseInt(strId.trim());
		} catch (Exception e) {
		}
		return id;
	}
	public static int toInt2(String strId) {
		int id = 0;
		try {
			id = Integer.parseInt(strId.trim());
		} catch (Exception e) {
		}
		return id;
	}
	public static long toLong(String strId) {
		long id = -1;
		try {
			id = Long.parseLong(strId);
		} catch (Exception e) {
		}
		return id;
	}

	public static String convertNull(String s) {
		if (s == null) {
			return "";
		} else {
			return s;
		}
	}

	public static int StringToId(HttpServletRequest request, String name) {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter(name));
		} catch (Exception e) {
		}
		return id;
	}

	public static String getFileExt(String filePath) {
		if (filePath == null) {
			return null;
		}

		String path = filePath.trim();

		if (path.equals("")) {
			return null;
		}

		return path.substring(path.lastIndexOf('.') + 1, path.length());
	}

	public static String getUrlAction(String url) {
		if (url == null) {
			return null;
		}

		String action = url.trim();

		if (action.equals("")) {
			return null;
		}
		if (!action.substring(action.length() - 3).equals(".do"))
			return "";
		return action.substring(action.lastIndexOf('/') + 1,
				action.length() - 3);
	}

	public static String getShortAction(String fullname) {
		if (fullname == null) {
			return null;
		}

		String action = fullname.trim();

		if (action.equals("")) {
			return null;
		}

		return action.substring(action.lastIndexOf('/') + 1, action.length());
	}

	public static String getUrlPerm(String url) {
		if (url == null) {
			return null;
		}
		if (url.startsWith("/")) {
			int pos = url.indexOf("-");
			if (pos < 0)
				return null;

			return url.substring(1, pos);
		}
		return null;
	}

	public static String getStringId2(int id) {
		if (stringId2 == null) {
			stringId2 = new String[100];
			for (int i = 0; i < 10; i++) {
				stringId2[i] = "0" + String.valueOf(i);
			}
			for (int i = 10; i < 100; i++) {
				stringId2[i] = String.valueOf(i);
			}
		}
		return stringId2[id];
	}

	private static String[] stringId2 = null;

	public static String getCode(boolean needreply, int corpId, int userId) {
		String code = "02";
		if (needreply) {
			return code + getStringId2(corpId) + String.valueOf(userId);
		} else {
			return code + getStringId2(corpId);
		}
	}

	public static String dealParam(String param) {
		if (param == null) {
			return param;
		}

		// param = param.replaceAll("'", "\"");
		param = param.replace("\\", "\\\\");
		param = param.replace("'", "\\'");
		param = param.trim();
		return param;
	}

	public static String toSql(String src) {
		src = src.replace("\\", "\\\\");
		src = src.replace("'", "\\'");
		return src;
	}

	// 用于 like '??'
	public static String toSqlLike(String src) {
		src = src.replace("\\", "\\\\");
		src = src.replace("'", "\\'");
		src = src.replace("%", "\\%");
		src = src.replace("_", "\\_");
		return src;
	}

	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatDouble2(double d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat(float d) {
		DecimalFormat df = new DecimalFormat("##########0.##");
		return df.format(d);
	}

	public static String formatFloat2(float d) {
		DecimalFormat df = new DecimalFormat("##########0.###");
		return df.format(d);
	}

	public static String formatFloat3(float d) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(d);
	}

	public static String toWml(String src) {
		if (src == null)
			return "";

		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("\\$", "");
		src = src.replaceAll("¤", "O");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll("", "");
		src = src.replace("\"", "&#34;");

		return src;
	}

	public static String dealTag(String str) {
		if (str == null) {
			return null;
		}

		str = str.replaceAll(TAG_START, "<");
		str = str.replaceAll(TAG_END, ">");

		return str;
	}

	public static String dealLink(String link, HttpServletRequest request,
			HttpServletResponse response) {
		if (link == null) {
			return link;
		}

		String domain = request.getServerName();
		if (link.startsWith("/")) {
			link = "http://" + domain + link;
		}
		// link = link.replaceAll("&", "&amp;");

		return link;// response.encodeURL(link);
	}

	static Pattern p = Pattern.compile("\\[img\\]([^\\[]*)\\[img\\]");

	static String urlRegex = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][%]&[)][(]]*";

	public static String toHtml(String src) {
		if (src == null)
			return "";

		src = src.replaceAll("\r\n", "<br/>");
		src = src.replaceAll("\n", "<br/>");
		src = src.replaceAll(" ", "&nbsp;&nbsp;");
		src = src.replaceAll(urlRegex,
				"<a href=\"$0\" target=\"_blank\">$0</a>");
		Matcher m = p.matcher(src);
		while (m.find()) {
			String s = m.group(1);
			s = "<a href=\"" + s + "\" target=_blank>" + "<img src=\"" + s
					+ "\" width=\"400\" border=0 alt=\"点击查看大图\"/></a>";
			src = m.replaceFirst(s);
			m = p.matcher(src);
		}
		return src;
	}

	public static String array2String(String[] strs, String split) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(strs.length * 10);
		for (int i = 0; i < strs.length; i++) {
			if (i != 0) {
				builder.append(split);
			}
			builder.append(strs[i]);
		}
		return builder.toString();
	}

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}
		if ("".equals(s)) {
			return true;
		}
		return false;
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	public static boolean isNumeric(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMobile(String s) {
		if (s == null) {
			return false;
		}

		if (!s.startsWith("13") && !s.startsWith("15") && !s.startsWith("14")
				&& !s.startsWith("18")) {
			return false;
		}

		if (s.length() != 11) {
			return false;
		}

		try {
			Long.parseLong(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static String cutString(String s, int count) {
		if (s == null) {
			return s;
		}
		if (s.length() < count) {
			return s;
		}
		s = s.substring(0, count);
		return s;
	}

	public static String cutString(String s, int start, int end) {
		if (s == null) {
			return s;
		}
		if (s.length() < start || s.length() < end) {
			return s;
		}
		s = s.substring(start, end);
		return s;
	}

	// input 字符
	// index 需要截取的长度
	public static String getString(String input, int index) {
		int temp = 0; // 长度
		StringBuffer sb = new StringBuffer(""); // 构造一个字符串缓冲区，并将其内容初始化为指定的字符串内容。
		for (int i = 0; i < input.length(); i++) {
			// 获取每个字符
			String slice = input.substring(i, i + 1);// 循环分解字符串
			// substring()返回一个新的字符串，它是此字符串的一个子字符串。
			byte[] strByte = slice.getBytes();
			// getBytes()使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
			if (strByte.length == 1) {// 长度为1，则为英文字符
				sb.append(slice);

				if (++temp == index) {
					return sb.toString();
				}
			} else {// 长度为2，应为中文字符
				if (temp + 2 > index) {// 如果长度再加2，超过需要截取的长度
					return sb.toString();
				}
				if (temp + 2 == index) {// 如果长度再加2等于需要截取的长度,加上中文字符，返回
					return sb.append(slice).toString();
				} else {// 未超过截取字符，附加上中文字符
					sb.append(slice);
					temp += 2;
				}
			}
		}
		return sb.toString();
	}

	public static String getGetMethodName(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String getSetMethodName(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String convertNull(String s, String def) {
		if (isNull(s)) {
			return def;
		} else {
			return s;
		}
	}

	/**
	 * 作者：曹续
	 * 
	 * 创建时间：2009-9-3
	 * 
	 * 补位左填充
	 * 
	 * 参数及返回值说明：
	 * 
	 * @param input
	 * @param c
	 * @param length
	 * @return
	 */
	public static String padLeft(String input, char c, int length) {
		String output = input;
		while (output.length() < length) {
			output = c + output;
		}
		return output;
	}

	public static boolean toBoolean(String s) {
		boolean b = false;
		try {
			b = Boolean.parseBoolean(s);
		} catch (Exception e) {

		}
		return b;
	}

	// 异常信息输出转换
	public static String getExceptionInfo(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	/**
	 * 说明：查询字符串数据中，是否包含某字符串
	 */
	public static boolean hasStrArray(String[] array, String s) {
		boolean result = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(s)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * &lt; < 小于号 &gt; > 大于号 &amp; & 和 &apos; ' 单引号 &quot; " 双引号 功能:需要转化为xml
	 * 的特殊字符变化为 转义字符
	 * <p>
	 * 作者 李双 Dec 21, 2011 3:47:35 PM
	 * 
	 * @return
	 */
	public static String changStrToXml(String str) {
		String xml = "";
		if (str == null || str.length() < 1)
			return "";

		xml = str.replaceAll("<", "&lt;");
		xml = xml.replaceAll(">", "&gt;");
		xml = xml.replaceAll("&", "&&amp;");
		xml = xml.replaceAll("'", "&apos;");
		xml = xml.replaceAll("\"", "&quot;");

		return xml;
	}

	static Pattern pattern = Pattern.compile("\\d{1,9}");
	static Pattern pattern1 = Pattern.compile("\\[1-9]{1,9}");

	/**
	 * 
	 * 功能:转化成数字。
	 * <p>
	 * 作者 李双 May 15, 2012 3:51:39 PM
	 * 
	 * @param id
	 * @return
	 */
	public static int parstInt(String id) {
		if (id == null)
			return 0;
		Matcher m = pattern.matcher(id);
		if (m.matches()) {
			return Integer.parseInt(id);
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * 功能:转化成数字。 返回负数
	 * <p>
	 * 作者 李双 May 15, 2012 3:51:39 PM
	 * 
	 * @param id
	 * @return
	 */
	public static int parstBackMinus(String id) {
		if (id == null)
			return -1;
		Matcher m = pattern.matcher(id);
		if (m.matches()) {
			return Integer.parseInt(id);
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * 功能:将字符 json化
	 * <p>
	 * 作者 李双 Apr 12, 2012 5:04:58 PM
	 * 
	 * @param str
	 *            满足简单的json 基本 但 不带双引号 缺陷： 值里面含有 ,:}{ 将无法封装
	 * @return
	 */
	public static String toJsonStr(String str) {
		if (str == null || str.length() < 1)
			return null;
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char a = str.charAt(i);
			if (a == '{' || a == '}' || a == ':' || a == ',' || a == '['
					|| a == ']') {
				if (temp.length() > 0) {
					Matcher m = pattern1.matcher(temp.toString());
					if (m.matches() || temp.toString().equals("null")) { // 若是数字和
																			// null
																			// 将不加双引号
						sb.append(temp);
					} else {
						sb.append("\"").append(temp).append("\"");
					}
				}
				temp.delete(0, temp.length());
				sb.append(a);
				continue;
			}
			temp.append(a);
		}
		return sb.toString();
	}

	private final static Locale locale = Locale.CHINA;

	/**
	 * 
	 * 功能:变成小写
	 * <p>
	 * 作者 李双 Aug 30, 2012 1:44:22 PM
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		return str != null ? str.toLowerCase(locale) : "";
	}

	/**
	 * 
	 * 功能:去掉字符串前后的 符号
	 * <p>
	 * 作者 李双 Sep 13, 2012 3:19:53 PM
	 * 
	 * @param s
	 * @param symbol
	 * @return
	 */
	public static StringBuilder removeStartAndEndSymbol(StringBuilder s,
			char symbol) {
		if (s == null)
			return null;
		if (s.length() > 0 && s.charAt(0) == symbol) {
			s.deleteCharAt(0);
		}
		if (s.length() > 0 && s.charAt(s.length() - 1) == symbol) {
			s.deleteCharAt(s.length() - 1);
		}
		return s;
	}

	public static boolean inArray(int[] array, int value) {
		if (array != null && array.length > 0) {
			Arrays.sort(array);
			return Arrays.binarySearch(array, value) >= 0;
		}
		return false;
	}

	/**
	 * 获取百分比，小数点后留1位
	 * 
	 * @param num
	 * @return
	 */
	public static String getPersent(float num) {
		num = num * 100;
		String result = String.valueOf(num);
		if (result.length() - result.indexOf(".") > 3) {
			result = result.substring(0, result.indexOf(".") + 3);
		}
		return result + "%";
	}

	/**
	 * md5加密，登录时验证
	 * 
	 * @param str
	 * @return
	 */
	public static String getMd5String(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 获取任意位的随机字符串(0-9 a-z A-Z)
	 * 
	 * @param size
	 *            位数
	 * @return
	 */
	public static final String getRandomNum(int size) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	private static final String ALLCHAR2 = "0123456789";

	public static final String getVerificationCode() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(ALLCHAR2.charAt(random.nextInt(ALLCHAR2.length())));
		}
		return sb.toString();
	}

	public static String getPassword(String password, String salt) {
		return getMd5String(getMd5String(password) + salt);
	}
	
	/**
	 * 保留两位小数
	 * @param price
	 * @return
	 */
	public static double formatPrice(double price) {
		BigDecimal b = new BigDecimal(price);
		double f = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		return f;
	}
}
