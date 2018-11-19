package by.iba.student.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.iba.student.common.User;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -7442218461937315863L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		if("admin".equals(name)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", new User(name,password, "admin"));
			resp.sendRedirect("/student-app/students-page");
		} else {
			doGet(req, resp);
		}
	}

}
