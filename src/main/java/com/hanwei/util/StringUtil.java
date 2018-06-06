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
	 * �˷�����������������һ����Ҫ���ҵ��ַ������飬�ڶ�����Ҫ���ҵ��ַ����ַ���
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
		String[] strs = { "doc", "docx", "pdf", "jpg", "jpeg", "png" };// �����ַ�������
		if (!isHave(strs, suffix)) {// �����Լ�����ĺ���isHave����������򷵻�true,���򷵻�false
			System.out.println("by����");// ��ӡ���
		}

	}
}
