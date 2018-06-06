package com.hanwei.util;

import java.io.File;

public class StringUtil {

	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
	 * @param strs
	 * @param s
	 * @return
	 */
	public static boolean isHave(String[] strs, String s) {
		int i = strs.length;
		while (i-- > 0) {
			if (strs[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		File file = new File("HelloWorld.java");
		String fileName = file.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(suffix);
		String[] strs = { "doc", "docx", "pdf", "jpg", "jpeg", "png" };// 定义字符串数组
		if (!isHave(strs, suffix)) {// 调用自己定义的函数isHave，如果包含则返回true,否则返回false
			System.out.println("by包含");// 打印结果
		}

	}
}
