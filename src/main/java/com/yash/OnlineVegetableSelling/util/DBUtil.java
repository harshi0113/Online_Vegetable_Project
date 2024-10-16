package com.yash.OnlineVegetableSelling.util;



import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/vegetable_delivery";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}