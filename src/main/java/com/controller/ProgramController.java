package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProgramDao;
import com.dao.SystemDao;
import com.model.Program;

@WebServlet("/program")
public class ProgramController extends HttpServlet{
	static final long serialVersionUID = 1L;
	ProgramDao dao;
	SystemDao systemDao;
	
	public ProgramController() {
		super();
		dao = new ProgramDao();
		systemDao = new SystemDao();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		String name = request.getParameter("name");
		
		if(!action.equals("deleteProgram")) {
			int speechTime = Integer.parseInt(request.getParameter("speechTime")); 
			int smsNumber = Integer.parseInt(request.getParameter("smsNumber"));
			int dataNumber = Integer.parseInt(request.getParameter("dataNumber"));
			float cost = Float.parseFloat(request.getParameter("cost"));
			String description = request.getParameter("description");
			int duration = Integer.parseInt(request.getParameter("duration"));
			
			Program newProgram= new Program();
			newProgram.setNameOfProgram(name);
			newProgram.setSpeechTime(speechTime);
			newProgram.setSmsNumber(smsNumber);
			newProgram.setDataNumber(dataNumber);
			newProgram.setCost(cost);
			newProgram.setDescription(description);
			newProgram.setDuration(duration);
			
			int success = dao.addUpdateProgram(newProgram, action);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
				if (action.equals("addProgram"))
					response.sendRedirect("addprogramsuccess.html");
				else 
					response.sendRedirect("updateprogramsuccess.html");
			} else {
			    response.sendRedirect("addsellererror.jsp");
			}
		} else {
			int success = dao.deleteProgram(name);
			request.setAttribute("err", dao.giveErr());
			
			if (success == 0) {
				response.sendRedirect("deleteprogramsuccess.html");
			} else {
			    response.sendRedirect("addsellererror.jsp");
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
