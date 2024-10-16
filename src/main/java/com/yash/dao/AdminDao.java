package com.yash.dao;

public interface AdminDao {
	boolean verifyAdmin(String username, String password);
	String identifyRole(String username);
}
