package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Student;

public class StudentRepository extends EntityRepository<Student> {
	public StudentRepository(List<Student> students) {
		super(new ArrayList<Student>(students));
	}
	
	public List<Student> findAllByFilter(String filterName){
		List<Student> allStudents = super.findAll();
		List<Student> filterStudents = new ArrayList<Student>();
		for (Student st: allStudents) {
			if(st.getFirstName().startsWith(filterName))
				filterStudents.add(st);
		}
		
		return filterStudents;
	}
}
