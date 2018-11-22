package by.iba.student.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.filter.StudentFilter;
import by.iba.student.repository.EntityRepositoryDb;

public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 6345194112526801506L;
	
	private EntityRepositoryDb<Group> groupRepository;
	private EntityRepositoryDb<Student> studentRepository;

	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		this.studentRepository = (EntityRepositoryDb<Student>)sc.getAttribute("studentRepository");
		this.groupRepository = (EntityRepositoryDb<Group>)sc.getAttribute("groupRepository");
		this.mapper = (ObjectMapper)sc.getAttribute("objectMapper");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentFilter filter = new StudentFilter(req.getParameter("filterFirstName"),
				req.getParameter("filterSecondName"), req.getParameter("filterGroup")); 
		resp.setContentType("application/json");
		List<Student> students = studentRepository.findAll(filter);
		
		PrintWriter pw = resp.getWriter();
		pw.print(mapper.writeValueAsString(students));
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String secondName = req.getParameter("secondName");
		if(groupRepository.findById("fak")==null)
			groupRepository.create(new Group("fak"));
		studentRepository.create(new Student(firstName, secondName, "fak"));
		System.out.println(String.format("First name: %s, Second name: %s", firstName, secondName));
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathToId = req.getPathInfo();
	    String id = pathToId.substring(1, pathToId.length());
	    studentRepository.remove(id);   
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newFirstName = req.getParameter("newFirstName");
		String newSecondName = req.getParameter("newSecondName");
		String newGroupId = req.getParameter("newGroupId");
		Student newStudent = new Student(newFirstName, newSecondName, newGroupId);
		
		String pathToId = req.getPathInfo();
	    String id = pathToId.substring(1, pathToId.length()).split("?")[0];
	    newStudent.setId(id);
	    
	    studentRepository.update(newStudent);
	    
	}
	
}
