package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Group;

public class GroupRepository extends EntityRepository{
	public GroupRepository(List<Group> groups) {
		super(new ArrayList<Entity>(groups));
	}
}
