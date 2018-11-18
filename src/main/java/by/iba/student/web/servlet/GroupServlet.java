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
import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.EntityRepositoryDb;

public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 3189551722384317888L;
	
	private EntityRepositoryDb<Group> groupRepository;
	private ObjectMapper mapper;
	
	@Override
	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		this.groupRepository = (EntityRepositoryDb<Group>)sc.getAttribute("groupRepository");
		this.mapper = (ObjectMapper)sc.getAttribute("objectMapper");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupFilter filter = new GroupFilter(req.getParameter("groupId"));
		resp.setContentType("application/json");
		List<Group> students = groupRepository.findAll(filter);
		
		PrintWriter pw = resp.getWriter();
		pw.print(mapper.writeValueAsString(students));
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String groupId = req.getParameter("groupId");
		groupRepository.create(new Group(groupId));
		System.out.println(String.format("Group id: %s", groupId));
		doGet(req, resp);
	}
}
