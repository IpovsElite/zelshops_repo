package com.ipovselite;

public interface UserDAO {
	public User getUser(int id);
	public User isValid(String username,String password);
	public boolean changePassword(String op,String np);
}
