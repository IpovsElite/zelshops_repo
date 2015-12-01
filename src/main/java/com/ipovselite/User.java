package com.ipovselite;

public class User {
	int id;
	String username;
	String password;
	Access access;
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
	public Access getAccess() {
		return access;
	}
	public void setAccess(Access access) {
		this.access = access;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
