package com.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SellerDao;
import com.dao.SystemDao;
import com.model.Program;
import com.model.Seller;

@WebServlet("/seller")
public class SellerController extends HttpServlet{
	static final long serialVersionUID = 1L;
	SellerDao dao;
	SystemDao systemDao;
	public SellerController() {
		super();
		dao = new SellerDao();
		systemDao = new SystemDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		if (action.equals("addseller")){
			
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
			
			Seller newSeller = new Seller();
			newSeller.setUsername(username);
			newSeller.setPassword(password);
			newSeller.setEmail(request.getParameter("email"));
			newSeller.setName(request.getParameter("name"));
			newSeller.setSurname(request.getParameter("surname"));
			newSeller.setAdminUsername(request.getParameter("adminUsername"));
			newSeller.setSalt(salt);
			
			int success = dao.addSeller(newSeller);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
			    response.sendRedirect("addsellersuccess.html");
			} else {
			    response.sendRedirect("addsellererror.jsp");
			}
		} else if (action.equals("displayPrograms") || action.equals("programClient")) {
			List<Program> programs = new ArrayList<>();
			
			int success = dao.displayPrograms();
			request.setAttribute("err", dao.giveErr());
			
			programs = dao.getPrograms();
			request.setAttribute("programs", programs);
			
			if (action.equals("displayPrograms")) {
				if(success == 0) {
			        request.getRequestDispatcher("/displayprograms.jsp").forward(request, response);
				} else {
					response.sendRedirect("displayprogramserror.jsp");
				}
			} else {
				if(success == 0) {
			        request.getRequestDispatcher("/matchprogram.jsp").forward(request, response);
				} else {
					response.sendRedirect("displayprogramserror.jsp");
				}
			}
		} else if (action.equals("addProgramClient")) {
			String number = request.getParameter("number");
			int program = Integer.parseInt(request.getParameter("program"));
			
			int success = dao.MatchClientProgram(number, program);
			request.setAttribute("err", dao.giveErr());

			if(success == 0) {
				response.sendRedirect("matchprogramsuccess.html");
			} else {
				response.sendRedirect("displayprogramserror.jsp");
			}
		} else if (action.equals("deleteSeller")) {
			String username = request.getParameter("username");
			
			int success = dao.deleteSeller(username);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
				response.sendRedirect("deletesellersuccess.html");
			} else {
			    response.sendRedirect("addsellererror.jsp");
			}
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
