package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Professor;

public class ProfessorRepository extends EntityRepository<Professor>{
	public ProfessorRepository(List<Professor> professors) {
		super(new ArrayList<Professor>(professors));
	}
}
