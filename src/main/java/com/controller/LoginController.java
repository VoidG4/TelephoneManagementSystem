package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.LoginDao;
import com.model.Login;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	static final long serialVersionUID = 1L;
	static String adminUsername;
	LoginDao dao;
	
	public LoginController() {
		super();
		dao = new LoginDao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("loginusername");
        String password = request.getParameter("loginpassword");
        String property = request.getParameter("loginproperty");
        
        Login newLogin = new Login();
        newLogin.setUsername(username);
        newLogin.setPassword(password);
        newLogin.setProperty(property);
		
		int success = dao.findUser(newLogin.getUsername(), newLogin.getPassword(), newLogin.getProperty());
		request.setAttribute("err", dao.giveErr());
		
		HttpSession session = request.getSession();
		session.setAttribute("username", adminUsername = newLogin.getUsername());
		session.setAttribute("property", newLogin.getProperty());
		
		if (success == 0) {
	        response.sendRedirect("adminmenu.jsp");
	    } else if (success == 2) {
	        response.sendRedirect("clientmenu.jsp");
	    } else if (success == 3) {
	        response.sendRedirect("sellermenu.jsp");
	    } else {
	        response.sendRedirect("errorlogin.jsp");
	    }
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public static String getAdmin() {
		if (adminUsername == null) return "n/a";
		return adminUsername;
	}
}
