package com.yash.controller;

import java.io.IOException;

import com.yash.dao.AdminDao;
import com.yash.dao.impl.AdminDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AdminDao adminDao = new AdminDaoImpl();
		boolean check = adminDao.verifyAdmin(username, password);
		System.out.println(check);
		if (check) {
			 String role = adminDao.identifyRole(username);
			 switch(role) {
			 case "superAdmin":
				 response.sendRedirect("superAdmin.jsp");
				 break;
			 case "vendorManagement":
				 response.sendRedirect("vendorManagement.jsp");
				 break;
			 case "customerOrderManagement":
				 response.sendRedirect("customerManagement.jsp");
				 break;
			 case "customerSupport":
				 response.sendRedirect("customerSupport.jsp");
				 break;
			 default:
				 response.sendRedirect("login.jsp?error=Incorrect_credentials");
				 
			 }
		}else {
            response.sendRedirect("Login.jsp?error=Incorrect_credentials");
	
	}
	}
}
