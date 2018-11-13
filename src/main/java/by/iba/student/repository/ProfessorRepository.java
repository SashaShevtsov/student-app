package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Professor;

public class ProfessorRepository extends EntityRepository{
	public ProfessorRepository(List<Professor> professors) {
		super(new ArrayList<Entity>(professors));
	}
}
