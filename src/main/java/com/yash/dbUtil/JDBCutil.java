package com.yash.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCutil {
	static final String URL = "jdbc:mysql://localhost:3306/admin";
	static final String USER = "root";
	static final String PASSWORD = "Password@2024";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
	Connection con = null;
	
	public Connection openConnection() {
		try {
			if(con==null || con.isClosed()) {
				
				con = DriverManager.getConnection(URL, USER, PASSWORD);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public PreparedStatement
    createPreparedStatement(String sql) {
        try {
            return openConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        
    }
	
	public Statement
    createStatement() {
        try {
            return openConnection().createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
	
	
	public void closePreparedStatement(PreparedStatement pstmt) {
        try {
            pstmt.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException(ex.getMessage());
        }
    }

    public ResultSet createResultSet(String query) {
        try {

            return openConnection().createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void closeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
        }
    }

    public void closeConnection() {
        try {
        	System.out.println("Connection closed");
            con.close();
        } catch (SQLException ex) {
        }
    }
}
