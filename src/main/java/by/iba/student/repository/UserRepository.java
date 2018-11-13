package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.User;

public class UserRepository extends EntityRepository{
	public UserRepository(List<User> users) {
		super(new ArrayList<Entity>(users));
	}
}
