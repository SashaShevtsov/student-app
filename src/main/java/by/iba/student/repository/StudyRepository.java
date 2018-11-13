package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Study;

public class StudyRepository extends EntityRepository{
	public StudyRepository(List<Study> studies) {
		super(new ArrayList<Entity>(studies));
	}
}
