package by.iba.student.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Student;
import by.iba.student.repository.StudentRepository;
import by.iba.student.repository.StudentRepositoryDb;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private StudentRepositoryDb studentRepository;
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		this.studentRepository = (StudentRepositoryDb)sc.getAttribute("studentRepository");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("students", studentRepository.findAll());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/students.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String secondName = req.getParameter("secondName");
//		studentRepository.create(new Student(firstName, secondName));
		System.out.println(String.format("First name: %s, Second name: %s", firstName, secondName));
		doGet(req, resp);
	}
	
}
