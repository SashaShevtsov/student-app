package by.iba.student.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Professor;

public class ProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 4940208984412603268L;
	
    private final static List<Professor> PROFESSORS = new ArrayList<Professor>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("professors", PROFESSORS);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/JSP/professors.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String secondName = req.getParameter("secondName");
		String fatherName = req.getParameter("fatherName");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date birthDate;
		try {
			birthDate = dateFormat.parse(req.getParameter("birthDate"));
			PROFESSORS.add(new Professor(firstName, secondName, fatherName, birthDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		doGet(req, resp);
	}

}
