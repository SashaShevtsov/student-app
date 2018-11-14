package by.iba.student.repository;

import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.User;

public class UserRepository extends EntityRepository<User>{
	public UserRepository(List<User> users) {
		super(new ArrayList<User>(users));
	}
}
