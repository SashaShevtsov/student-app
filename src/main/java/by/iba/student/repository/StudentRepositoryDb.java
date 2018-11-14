package by.iba.student.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.util.StringUtil;

public class StudentRepositoryDb {
	
	private final DataSource dataSource;
	
	public StudentRepositoryDb(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			Statement statement = conn.createStatement();
			
			String sql = 
					"SELECT "
					+ "* "
					+ "FROM BEGANSS.STUDENT";
			
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("STUDENT_ID"));
				student.setFirstName(StringUtil.trim(rs.getString("FIRST_NAME")));
				student.setSecondName(StringUtil.trim(rs.getString("SECOND_NAME")));
				
				String groupId = StringUtil.trim(rs.getString("GROUP_NUMBER"));
				if (!StringUtil.isEmpty(groupId)) {
					Group group = new Group();
					group.setGroupNumber(Integer.valueOf(groupId));
					student.setGroup(group);
				}
				students.add(student);
				
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return students;
		
	}
	
}
