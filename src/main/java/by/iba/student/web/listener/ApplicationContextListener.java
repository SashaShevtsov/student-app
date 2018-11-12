package by.iba.student.web.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.iba.student.common.Student;
import by.iba.student.reader.StudentReader;
import by.iba.student.repository.StudentRepository;
import by.iba.student.writer.StudentWriter;

public class ApplicationContextListener implements ServletContextListener {

	private StudentRepository studentRepository;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			String filePath = sc.getInitParameter("student.file.path");
			List<Student> students = new StudentReader(filePath).read();
			this.studentRepository = new StudentRepository(students);
			
			sc.setAttribute("studentRepository", studentRepository);
		} catch (IOException e) {
			throw new RuntimeException("Can't read students", e);
		}
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ServletContext sc = sce.getServletContext();
			String filePath = sc.getInitParameter("student.file.path");
			List<Student> students = studentRepository.findAll();
			new StudentWriter(filePath).write(students);
		} catch (IOException e) {
			System.out.println("Can't save students");
		}
		
	}

}
