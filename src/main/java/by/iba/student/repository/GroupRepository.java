package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Group;

public class GroupRepository extends EntityRepository<Group>{
	public GroupRepository(List<Group> groups) {
		super(new ArrayList<Group>(groups));
	}
}
