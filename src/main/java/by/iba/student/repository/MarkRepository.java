package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Mark;

public class MarkRepository extends EntityRepository{
	public MarkRepository(List<Mark> marks) {
		super(new ArrayList<Entity>(marks));
	}
}
