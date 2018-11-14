package by.iba.student.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import by.iba.student.repository.EntityRepository;
import by.iba.student.repository.GroupRepository;
import by.iba.student.repository.StudentRepository;
import by.iba.student.repository.StudentRepositoryDb;

public class ApplicationContextListener implements ServletContextListener {
	private StudentRepository studentRepository;
	private EntityRepository professorRepository;
	private EntityRepository studyRepository;
	private GroupRepository groupRepository;
	private EntityRepository markRepository;
	private EntityRepository userRepository;
	
	private static String getFilePath(ServletContext sc, String key) {
		return sc.getInitParameter(key);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		ServletContext sc = sce.getServletContext();
//		String filePath = getFilePath(sc, "group.file.path");
//		List<Group> groups = new EntityFileReader<Group>(filePath, new GroupLineMapper()).read();
//		this.groupRepository = new GroupRepository(groups);
//		
//		filePath = getFilePath(sc, "student.file.path");
//		List<Student> students = new EntityFileReader<Student>(filePath, new StudentLineMapper(groupRepository)).read();
//		this.studentRepository = new StudentRepository(students);
		DataSource dataSource = dataSource();
		StudentRepositoryDb studentRepositoryDb = new StudentRepositoryDb(dataSource);
		
		sce.getServletContext().setAttribute("studentRepository", studentRepositoryDb);
		sce.getServletContext().setAttribute("groupRepository", groupRepository);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		try {
//			String filePath = getStudentFilePath(sce);
//			List<Entity> students = studentRepository.findAll();
//			new StudentWriter(filePath).write(students);
//		} catch (IOException e) {
//			System.out.println("Can't save students");
//		}
		
	}
	
	private DataSource dataSource() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
			return ds;
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
