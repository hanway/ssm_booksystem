package com.hanwei.test;

import java.io.File;

public class Test {
	
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

	/**
     * @param strs��Ҫ���ҵ��ַ�������
     * @param s��Ҫ���ҵ��ַ����ַ���
     * @return true �����ڸ�������
     * false��������
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
