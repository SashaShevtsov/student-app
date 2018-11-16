package by.iba.student.web.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.iba.student.common.Entity;
import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.reader.EntityFileReader;
import by.iba.student.reader.GroupLineMapper;
import by.iba.student.reader.StudentLineMapper;
import by.iba.student.reader.StudentReader;
import by.iba.student.repository.EntityRepository;
import by.iba.student.repository.GroupRepository;
import by.iba.student.repository.StudentRepository;
import by.iba.student.writer.StudentWriter;

public class ApplicationContextListener implements ServletContextListener {
	private StudentRepository studentRepository;
	private EntityRepository professorRepository;
	private EntityRepository studyRepository;
	private GroupRepository groupRepository;
	private EntityRepository markRepository;
	private EntityRepository userRepository;
	
	private String getFilePath(ServletContext sc, String param) {
		return sc.getInitParameter(param);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String filePath = getFilePath(sc, "group.file.path");
		List<Group> groups = new EntityFileReader<Group>(filePath, new GroupLineMapper()).read();
		this.groupRepository = new GroupRepository(groups);
		
		filePath = getFilePath(sc, "student.file.path");
		List<Student> students = new EntityFileReader<Student>(filePath, new StudentLineMapper(groupRepository)).read();
		this.studentRepository = new StudentRepository(students);
		
		sce.getServletContext().setAttribute("groupRepository", groupRepository);
		sce.getServletContext().setAttribute("studentRepository", studentRepository);
		sce.getServletContext().setAttribute("objectMapper", new ObjectMapper());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			String filePath = getFilePath(sc, "student.file.path");
			List<Student> students = studentRepository.findAll();
			new StudentWriter(filePath).write(students);
		} catch (IOException e) {
			System.out.println("Can't save students");
		}
		
	}

}
