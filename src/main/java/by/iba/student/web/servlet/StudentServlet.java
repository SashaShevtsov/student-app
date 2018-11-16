package by.iba.student.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.common.Student;
import by.iba.student.repository.GroupRepository;
import by.iba.student.repository.StudentRepository;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private GroupRepository groupRepository;
	private StudentRepository studentRepository;

	
	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		this.studentRepository = (StudentRepository)sc.getAttribute("studentRepository");
		this.groupRepository = (GroupRepository)sc.getAttribute("groupRepository");
		this.mapper = (ObjectMapper)sc.getAttribute("objectMapper");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json");
		List<Student> students = studentRepository.findAll();
		
		
		PrintWriter pw = resp.getWriter();
		pw.print(mapper.writeValueAsString(students));
		pw.close();
	}
	
	private static String toJson(Student student) {
		String json = "{" +
		    "\"id\": \"" + student.getId()+"\", " +
		    "\"firstName\": \"" + student.getFirstName()+"\", " +
		    "\"secondName\": \"" + student.getSecondName()+"\"" +
		"}";
		return json;
	}
	
	private static String toJson(List<Student> students) {
		String json = "[";
		if(students!=null) {
			boolean firstItem = true;
			for(Student student: students) {
				if(firstItem) {
					firstItem = false;
				} else {
					json+=",";
				}
				json+=toJson(student);
			}
		}
		json+="]";
		return json;

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String secondName = req.getParameter("secondName");
		studentRepository.create(new Student(firstName, secondName));
		System.out.println(String.format("First name: %s, Second name: %s", firstName, secondName));
		doGet(req, resp);
	}
	
}
