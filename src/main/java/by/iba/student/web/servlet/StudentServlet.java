package by.iba.student.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	
	private String getIdFromPath(HttpServletRequest req) {
		String pathToId = req.getPathInfo();
	    return pathToId.substring(1, pathToId.length());
	}
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		this.studentRepository = (EntityRepositoryDb<Student>)sc.getAttribute("studentRepository");
		this.groupRepository = (EntityRepositoryDb<Group>)sc.getAttribute("groupRepository");
		this.mapper = (ObjectMapper)sc.getAttribute("objectMapper");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object result = null;
		resp.setContentType("application/json");
		if(req.getPathInfo()!=null) {
		    result = studentRepository.findById(getIdFromPath(req));
		} else {
			StudentFilter filter = new StudentFilter(req.getParameter("filterFirstName"),
					req.getParameter("filterSecondName"), req.getParameter("filterGroup")); 
			result = studentRepository.findAll(filter);
		}
		PrintWriter pw = resp.getWriter();
		pw.print(mapper.writeValueAsString(result));
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student student = mapper.readValue(req.getReader(), Student.class);
		Group fakeGroup = groupRepository.findById("fak");
		if(fakeGroup==null)
			fakeGroup = groupRepository.create(new Group("fak"));
		student.setGroup(fakeGroup);
		studentRepository.create(student);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    studentRepository.remove(getIdFromPath(req));   
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student student = mapper.readValue(req.getReader(), Student.class);
	    student.setId(getIdFromPath(req));
	    studentRepository.update(student);
	    
	}
	
}
