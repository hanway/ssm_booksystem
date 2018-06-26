package com.hanwei.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取当前时间的字符串格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDate() {
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		String date = getNowDate();
		System.out.println(date);
	}
}
