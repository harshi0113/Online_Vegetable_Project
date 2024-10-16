package com.yash.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yash.dao.AdminDao;
import com.yash.dbUtil.JDBCutil;

public class AdminDaoImpl implements AdminDao {
	JDBCutil jdbcUtil;
	
	public AdminDaoImpl(){
		jdbcUtil = new JDBCutil();
	}
	public boolean verifyAdmin(String username, String enteredPassword) {
		String query = "Select password from admininfo where username=?";
		
		System.out.println("I am in verifying...");
		try (PreparedStatement pst = jdbcUtil.createPreparedStatement(query)) {
	        pst.setString(1, username);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	System.out.println(rs.getString(1));
	                return rs.getString(1).equals(enteredPassword); 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		
		return false;
	}
	
	
	public String identifyRole(String username) {
		String query = "Select role from roles where id=(select role_id from admininfo where username=?)";
		
		try (PreparedStatement pst = jdbcUtil.createPreparedStatement(query)) {
	        pst.setString(1, username);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString(1); 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return "role not found";
	}

}
