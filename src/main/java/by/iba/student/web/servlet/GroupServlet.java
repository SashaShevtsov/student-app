package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Group;

public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 3189551722384317888L;
	
    private final static List<Group> GROUPS = new ArrayList<Group>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("groups", GROUPS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/groups.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int groupNumber = Integer.parseInt(req.getParameter("groupNumber"));
		GROUPS.add(new Group(groupNumber));
		System.out.println(String.format("Group number: %s", groupNumber));
		doGet(req, resp);
	}
}
