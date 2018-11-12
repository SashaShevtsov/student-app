package by.iba.student.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Student;
import by.iba.student.reader.StudentReader;
import by.iba.student.repository.StudentRepository;
import by.iba.student.writer.StudentWriter;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private final String path = "D:\\workspace_project\\student-app\\src\\main\\java\\resourses\\students.csv";
	
	private StudentRepository studentRepository;
	
	@Override
	public void init() throws ServletException {
		try {
			List<Student> students = new StudentReader(path).read();
			this.studentRepository = new StudentRepository(students);
		} catch(IOException e) {
			throw new ServletException(e);
		}
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
		studentRepository.create(new Student(firstName, secondName));
		System.out.println(String.format("First name: %s, Second name: %s", firstName, secondName));
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		try {
			List<Student> students = studentRepository.findAll();
			new StudentWriter(path).write(students);
		} catch (IOException e) {
			System.out.println("Can't save students");
		}
	}
     
}
