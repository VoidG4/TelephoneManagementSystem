package com.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdministratorDao;
import com.dao.SystemDao;
import com.model.Administrator;

@WebServlet("/administrator")
public class AdministratorController extends HttpServlet{
	static final long serialVersionUID = 1L;
	AdministratorDao dao;
	SystemDao systemDao;
	
	public AdministratorController() {
		super();
		dao = new AdministratorDao();
		systemDao = new SystemDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password=request.getParameter("password");
		String salt=systemDao.getAlphaNumericString(16);

		password = password + salt;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			password=systemDao.bytesToHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Administrator newAdministrator = new Administrator();
		newAdministrator.setUsername(username);
		newAdministrator.setPassword(password);
		newAdministrator.setEmail(request.getParameter("email"));
		newAdministrator.setName(request.getParameter("name"));
		newAdministrator.setSurname(request.getParameter("surname"));
		newAdministrator.setSalt(salt);
		
		int success = dao.addAdministrator(newAdministrator);
		request.setAttribute("err", dao.giveErr());
		
		if (success == 0) {
		    response.sendRedirect("register.html");
		} else {
		    response.sendRedirect("registererror.jsp");
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
