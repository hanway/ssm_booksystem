package com.hanwei.test;

import java.io.File;

public class Test {
	
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

	/**
     * @param strs：要查找的字符串数组
     * @param s：要查找的字符或字符串
     * @return true 包含在该数组中
     * false：不包含
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
}
