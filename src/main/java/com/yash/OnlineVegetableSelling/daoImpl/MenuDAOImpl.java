package com.yash.OnlineVegetableSelling.daoImpl;

import com.yash.OnlineVegetableSelling.dao.MenuDAO;
import com.yash.OnlineVegetableSelling.domain.Menu;
import com.yash.OnlineVegetableSelling.util.DBUtil;

import java.sql.*; 
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {

    @Override
    public Menu create(Menu menu) {
        String sql = "INSERT INTO menus (menu_name) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, menu.getMenuName());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating menu failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    menu.setMenuId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating menu failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public Menu read(int menuId) {
        String sql = "SELECT * FROM menus WHERE menu_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("menu_id"));
                menu.setMenuName(rs.getString("menu_name"));
                System.out.println(menu);
                System.out.println("helloooooooooooooooooooo");
                return menu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Menu update(Menu menu) {
        String sql = "UPDATE menus SET menu_name = ? WHERE menu_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, menu.getMenuName());
            pstmt.setInt(2, menu.getMenuId());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating menu failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public boolean delete(int menuId) {
        String sql = "DELETE FROM menus WHERE menu_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, menuId);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //data database se aa rha h
    @Override
    public List<Menu> readAll() {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menus";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("menu_id"));
                menu.setMenuName(rs.getString("menu_name"));
                menus.add(menu);
               
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
}