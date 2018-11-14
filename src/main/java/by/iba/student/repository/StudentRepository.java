package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Student;

public class StudentRepository extends EntityRepository<Student> {
	public StudentRepository(List<Student> students) {
		super(new ArrayList<Student>(students));
	}
}
