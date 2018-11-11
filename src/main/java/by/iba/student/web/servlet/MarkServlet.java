package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Mark;

public class MarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1417473330873566449L;

    private final static List<Mark> MARKS = new ArrayList<Mark>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("marks", MARKS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/marks.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mark = Integer.parseInt(req.getParameter("mark"));
		String comments = req.getParameter("comments");
		MARKS.add(new Mark(new Date(),mark, comments));
		doGet(req, resp);
	}
	
}
