package com.hanwei.entity;

public class User {
	
	private Integer id;//id
	private String username;//�û���
	private String password;//����
	private String role;//��ɫ
	private String lastaccesstime;//����¼ʱ��
	private String lastaccessip;//����¼IP
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastaccesstime() {
		return lastaccesstime;
	}
	public void setLastaccesstime(String lastaccesstime) {
		this.lastaccesstime = lastaccesstime;
	}
	public String getLastaccessip() {
		return lastaccessip;
	}
	public void setLastaccessip(String lastaccessip) {
		this.lastaccessip = lastaccessip;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
