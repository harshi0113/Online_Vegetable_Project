package com.yash.OnlineVegetableSelling.contoller;



import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yash.OnlineVegetableSelling.domain.Menu;
import com.yash.OnlineVegetableSelling.service.MenuService;
import com.yash.OnlineVegetableSelling.serviceImple.MenuServiceImpl;


@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        super.init();
        menuService = MenuServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addMenu(request, response);
                break;
            case "update":
                updateMenu(request, response);
                break;
            case "delete":
                deleteMenu(request, response);
                break;
            default:
                response.getWriter().write("Invalid action");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("get".equals(action)) {
            getMenu(request, response);
        } else {
            getAllMenus(request, response);
        }
    }

    private void addMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String menuName = request.getParameter("menuName");
        Menu addedMenu = menuService.addMenu(menuName);
        response.getWriter().write("Menu added successfully: " + addedMenu);
        System.out.println(menuName);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        String menuName = request.getParameter("menuName");
        Menu updatedMenu = menuService.updateMenu(menuId, menuName);
        if (updatedMenu != null) {
            response.getWriter().write("Menu updated successfully: " + updatedMenu);
        } else {
            response.getWriter().write("Menu not found");
        }
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int menuId = Integer.parseInt(request.getParameter("menuId"));
            System.out.println("in delete servlet..................................");
            boolean deleted = menuService.deleteMenu(menuId);
            if (deleted) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("failure");
            }
        } catch (Exception e) {
            response.getWriter().write("error");
            System.out.println("Error deleting menu: " + e.getMessage());
        }
    }

    private void getMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        Menu menu = menuService.getMenu(menuId);
        if (menu != null) {
            response.getWriter().write(menu.toString());
        } else {
            response.getWriter().write("Menu not found");
        }
    }

    private void getAllMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Menu> menus = menuService.getAllMenus();
        StringBuilder sb = new StringBuilder();
        for (Menu menu : menus) {
            sb.append(menu.getMenuId()).append(",").append(menu.getMenuName()).append("\n");
        }
        response.setContentType("text/plain");
        response.getWriter().write(sb.toString());
    }
}