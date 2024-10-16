package com.yash.OnlineVegetableSelling.serviceImple;




import java.util.List;

import com.yash.OnlineVegetableSelling.dao.MenuDAO;
import com.yash.OnlineVegetableSelling.daoImpl.MenuDAOImpl;
import com.yash.OnlineVegetableSelling.domain.Menu;
import com.yash.OnlineVegetableSelling.service.MenuService;

public class MenuServiceImpl implements MenuService {
    private static MenuServiceImpl instance;
    private MenuDAO menuDAO;

    private MenuServiceImpl() {
        menuDAO = new MenuDAOImpl();
    }

    public static synchronized MenuServiceImpl getInstance() {
        if (instance == null) {
            instance = new MenuServiceImpl();
        }
        return instance;
    }

    @Override
    public Menu addMenu(String menuName) {
        Menu menu = new Menu(menuName);
        return menuDAO.create(menu);
    }

    @Override
    public Menu updateMenu(int menuId, String menuName) {
        Menu menu = menuDAO.read(menuId);
        if (menu != null) {
            menu.setMenuName(menuName);
            return menuDAO.update(menu);
        }
        return null;
    }

    @Override
    public boolean deleteMenu(int menuId) {
        return menuDAO.delete(menuId);
    }

    @Override
    public Menu getMenu(int menuId) {
        return menuDAO.read(menuId);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuDAO.readAll();
    }
}