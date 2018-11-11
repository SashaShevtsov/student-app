package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.User;

public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 3805580279868224835L;
	
    private final static List<User> USERS = new ArrayList<User>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("users", USERS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/students.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		USERS.add(new User(userName, password, role));
		doGet(req, resp);
	}

}
