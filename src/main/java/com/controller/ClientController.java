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

import com.dao.ClientDao;
import com.dao.SystemDao;
import com.model.Bill;
import com.model.Call;
import com.model.Client;
import com.model.PhoneNumber;
import com.model.Program;

@WebServlet("/client")
public class ClientController extends HttpServlet{
	static final long serialVersionUID = 1L;
	ClientDao dao;
	SystemDao systemDao;
	
	public ClientController() {
		super();
		dao = new ClientDao();
		systemDao = new SystemDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if(action.equals("addClient")) {
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

			Client newClient = new Client(request.getParameter("afm"));
			PhoneNumber newPhoneNumber = new PhoneNumber(request.getParameter("number"));
			newClient.setUsername(username);
			newClient.setPassword(password);
			newClient.setEmail(request.getParameter("email"));
			newClient.setPhoneNumber(newPhoneNumber);
			newClient.setName(request.getParameter("name"));
			newClient.setSurname(request.getParameter("surname"));
			newClient.setSellerUsername(request.getParameter("sellerUsername"));
			newClient.setSalt(salt);
			
			int success = dao.addClient(newClient);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
			    response.sendRedirect("addclientsuccess.html");
			} else {
			    response.sendRedirect("addclienterror.jsp");
			}
		} else if (action.equals("deleteClient")){
			String username = request.getParameter("username");
			int success = dao.deleteClient(username);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
				response.sendRedirect("deleteclientsuccess.html");
			} else {
			    response.sendRedirect("addsellererror.jsp");
			}
		} else if (action.equals("issueBill")) {
			String number = request.getParameter("number");
			List<Program> programs = new ArrayList<>();
			
			int success = dao.displayClientsPrograms(number);
			request.setAttribute("err", dao.giveErr());
			
			programs = dao.getPrograms();
			request.setAttribute("programs", programs);
			
			if(success == 0) {
		        request.getRequestDispatcher("/calculatebill.jsp").forward(request, response);
			} else {
				response.sendRedirect("displayprogramserror.jsp");
			}
		} else if (action.equals("displayBill")) {
			List<Bill> bills = new ArrayList<>();
			String username = request.getParameter("username");
			
			int success = dao.displayBill(username);
			request.setAttribute("err", dao.giveErr());
			
			bills = dao.getBills();
			
			request.setAttribute("bills", bills);
			
			if(success == 0) {
		        request.getRequestDispatcher("/displaybills.jsp").forward(request, response);
			} else {
				response.sendRedirect("error.jsp");
			}
		} else if (action.equals("callHistory")) {
			List<Call> calls = new ArrayList<>();
			String username = request.getParameter("username");
			
			int success = dao.callHistory(username);
			request.setAttribute("err", dao.giveErr());
			
			calls = dao.getCalls();
			
			request.setAttribute("calls", calls);
			
			if(success == 0) {
		        request.getRequestDispatcher("/callhistory.jsp").forward(request, response);
			} else {
				response.sendRedirect("error.jsp");
			}
		} else if (action.equals("payBills")) {
			List<Bill> bills = new ArrayList<>();
			String username = request.getParameter("username");
			
			int success = dao.displayBill(username);
			request.setAttribute("err", dao.giveErr());
			
			bills = dao.getBills();
			
			request.setAttribute("bills", bills);
			
			if(success == 0) {
		        request.getRequestDispatcher("/paybill.jsp").forward(request, response);
			} else {
				response.sendRedirect("error.jsp");
			}
		} else if (action.equals("deleteBill")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			int success = dao.deleteBill(id);
			request.setAttribute("err", dao.giveErr());
			
			if(success == 0) {
		        request.getRequestDispatcher("/paybillsuccess.html").forward(request, response);
			} else {
				response.sendRedirect("error.jsp");
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
