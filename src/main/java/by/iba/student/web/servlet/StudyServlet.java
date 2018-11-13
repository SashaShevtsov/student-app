package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Study;

public class StudyServlet extends HttpServlet {
	private static final long serialVersionUID = -5549727798241902042L;
	
    private final static List<Study> STUDIES = new ArrayList<Study>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("studies", STUDIES);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/studies.jsp");	
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		int hours = Integer.parseInt(req.getParameter("hours"));
		STUDIES.add(new Study(name, hours));
		System.out.println(String.format("Study name: %s, Hours: %s", name, hours));
		doGet(req, resp);
	}
	
}
