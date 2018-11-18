package by.iba.student.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.parser.GroupDbParser;
import by.iba.student.parser.StudentDbParser;
import by.iba.student.repository.EntityRepositoryDb;

public class ApplicationContextListener implements ServletContextListener {
	private EntityRepositoryDb<Student> studentRepository;
	private EntityRepositoryDb<Group> groupRepository;
	
	private String getFilePath(ServletContext sc, String param) {
		return sc.getInitParameter(param);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		groupRepository = new EntityRepositoryDb<>(dataSource(), new GroupDbParser());
		studentRepository = new EntityRepositoryDb<>(dataSource(), new StudentDbParser(groupRepository));
		
		sce.getServletContext().setAttribute("groupRepository", groupRepository);
		sce.getServletContext().setAttribute("studentRepository", studentRepository);
		sce.getServletContext().setAttribute("objectMapper", new ObjectMapper());
	}
	
	private DataSource dataSource() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/StudentsDB");
			return ds;
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
