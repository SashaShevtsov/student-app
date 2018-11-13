package by.iba.student.web.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.iba.student.common.Entity;
import by.iba.student.common.Student;
import by.iba.student.reader.StudentReader;
import by.iba.student.repository.EntityRepository;
import by.iba.student.repository.StudentRepository;
import by.iba.student.writer.StudentWriter;

public class ApplicationContextListener implements ServletContextListener {
	private EntityRepository studentRepository;
	private EntityRepository professorRepository;
	private EntityRepository studyRepository;
	private EntityRepository groupRepository;
	private EntityRepository markRepository;
	private EntityRepository userRepository;
	
	private String getStudentFilePath(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		return sc.getInitParameter("student.file.path");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			String studentFilePath = getStudentFilePath(sce);
			List<Student> students = new StudentReader(studentFilePath).read();
			this.studentRepository = new StudentRepository(students);
			
			sce.getServletContext().setAttribute("studentRepository", studentRepository);
		} catch (IOException e) {
			throw new RuntimeException("Can't read students", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			String filePath = getStudentFilePath(sce);
			List<Entity> students = studentRepository.findAll();
			new StudentWriter(filePath).write(students);
		} catch (IOException e) {
			System.out.println("Can't save students");
		}
		
	}

}
