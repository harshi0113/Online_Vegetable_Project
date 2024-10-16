package com.yash.OnlineVegetableSelling.domain;

public class Menu {
    private int menuId;
    private String menuName;

    // Default constructor
    public Menu() {
    }

    // Constructor with menuName
    public Menu(String menuName) {
        this.menuName = menuName;
    }

    // Getter for menuId
    public int getMenuId() {
        return menuId;
    }

    // Setter for menuId
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    // Getter for menuName
    public String getMenuName() {
        return menuName;
    }

    // Setter for menuName
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}