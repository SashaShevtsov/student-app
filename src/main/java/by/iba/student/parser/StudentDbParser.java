package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.filter.StudentFilter;
import by.iba.student.repository.EntityRepositoryDb;
import by.iba.student.util.StringUtil;

public class StudentDbParser extends EntityDbParser<Student> {
	private EntityRepositoryDb<Group> groupRepository;
	
	public StudentDbParser(EntityRepositoryDb<Group> groupRepository) {
		super("STUDENT", "STUDENT_ID");
		this.groupRepository = groupRepository;
		sqlUpdate += " FIRST_NAME=?, SECOND_NAME=?, GROUP_NUMBER=? WHERE STUDENT_ID=?";
	}
	
	protected void formParamsToAdd(Student student, List<String> fieldsToAdd, List<Object> params) {
		checkParamToAdd("FIRST_NAME", student.getFirstName(), fieldsToAdd, params);
		checkParamToAdd("SECOND_NAME", student.getSecondName(), fieldsToAdd, params);
		checkParamToAdd("GROUP_NUMBER", student.getGroup().getId(), fieldsToAdd, params);
	}
	
	@Override
	protected void formFilterParamsToAdd(Student entityFilter, List<String> fieldsToAdd, List<Object> params) {
		StudentFilter studentFilter = (StudentFilter) entityFilter;
		checkParamToAdd("FIRST_NAME", studentFilter.getFirstName(), fieldsToAdd, params);
		checkParamToAdd("SECOND_NAME", studentFilter.getSecondName(), fieldsToAdd, params);
		checkParamToAdd("GROUP_NUMBER", studentFilter.getGroupId(), fieldsToAdd, params);
	}
	
	@Override
	public Student parse(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getString("STUDENT_ID"));
		student.setFirstName(StringUtil.trim(resultSet.getString("FIRST_NAME")));
		student.setSecondName(StringUtil.trim(resultSet.getString("SECOND_NAME")));
		String groupId = StringUtil.trim(resultSet.getString("GROUP_NUMBER"));
		if (!StringUtil.isEmpty(groupId)) {
			student.setGroup(groupRepository.findById(groupId));
		}
		return student;
	}
}
