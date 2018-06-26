package com.hanwei.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * ��ȡ��ǰʱ����ַ�����ʽ yyyy-MM-dd HH:mm:ss
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
