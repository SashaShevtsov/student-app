package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import by.iba.student.common.Student;

public class StudentRepository {
	
	private final Map<String, Student> students = new ConcurrentHashMap<>();
	
	public StudentRepository(List<Student> students) {
		if(students!=null) {
			for(Student st: students)
				this.students.put(st.getId(), st);
		}
	}
	
	public List<Student> findAll(){
		return new ArrayList<>(students.values());
	}
	
	public Student create(Student student) {
		String id = UUID.randomUUID().toString();
		student.setId(id);
		students.put(id, student);
		return student;
	}
	
	public void remove (String id) {
		students.remove(id);
	}
	
	public Student update(Student student) {
		students.put(student.getId(), student);
		return student;
	}

}
