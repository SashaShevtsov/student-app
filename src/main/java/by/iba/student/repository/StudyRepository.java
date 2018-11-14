package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Study;

public class StudyRepository extends EntityRepository<Study>{
	public StudyRepository(List<Study> studies) {
		super(new ArrayList<Study>(studies));
	}
}
