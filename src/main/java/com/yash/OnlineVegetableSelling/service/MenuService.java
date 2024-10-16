package com.yash.OnlineVegetableSelling.service;

 

import java.util.List;

import com.yash.OnlineVegetableSelling.domain.Menu;

public interface MenuService {
    Menu addMenu(String menuName);
    Menu updateMenu(int menuId, String menuName);
    boolean deleteMenu(int menuId);
    Menu getMenu(int menuId);
    List<Menu> getAllMenus();
}