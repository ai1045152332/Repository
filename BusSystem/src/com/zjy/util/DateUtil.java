package com.zjy.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String dateFormat(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
}
