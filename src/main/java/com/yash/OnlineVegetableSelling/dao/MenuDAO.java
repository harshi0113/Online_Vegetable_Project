package com.yash.OnlineVegetableSelling.dao;



import java.util.List;

import com.yash.OnlineVegetableSelling.domain.Menu;

public interface MenuDAO {
    Menu create(Menu menu);
    Menu read(int menuId);
    Menu update(Menu menu);
    boolean delete(int menuId);
    List<Menu> readAll();
}
