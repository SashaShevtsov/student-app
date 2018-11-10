package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Student;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private final static List<Student> STUDENTS = new ArrayList<Student>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("students", STUDENTS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/test.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String secondName = req.getParameter("secondName");
		STUDENTS.add(new Student(firstName, secondName));
		System.out.println(String.format("First name: %s, Second name: %s", firstName, secondName));
		doGet(req, resp);
	}
}
