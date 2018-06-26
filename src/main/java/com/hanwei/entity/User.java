package com.hanwei.entity;

public class User {
	
	private Integer id;//id
	private String username;//用户名
	private String password;//密码
	private String role;//角色
	private String lastaccesstime;//最后登录时间
	private String lastaccessip;//最后登录IP
	
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
