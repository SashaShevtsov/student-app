package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.filter.StudentFilter;
import by.iba.student.repository.EntityRepositoryDb;
import by.iba.student.util.StringUtil;

public class StudentDbParser extends EntityDbParser<Student> {
	private EntityRepositoryDb<Group> groupRepository;
	
	public StudentDbParser(EntityRepositoryDb<Group> groupRepository) {
		super("STUDENT");
		this.groupRepository = groupRepository;
		sqlCreate +=" (FIRST_NAME, SECOND_NAME) VALUES ";
	}
	
	@Override
	public String getSqlFindById(String id) {
		return sqlFindAll + " WHERE STUDENT_ID="+id;
	}
	
	@Override
	public String getSqlFindAllByFilter(Student filter, List<Object> params) {
		StudentFilter studentFilter = (StudentFilter) filter;
		return sqlFindAll + " WHERE "+
				SqlHelper.addLike(params, "FIRST_NAME", studentFilter.getFirstName(), "AND")+
				SqlHelper.addLike(params, "SECOND_NAME", studentFilter.getSecondName(), "AND")+
				SqlHelper.addLike(params, "GROUP_NUMBER", studentFilter.getGroupId(), "AND")+
				"1=1";
	}
	
	@Override
	public String getSqlCreate(Student student) {
		return sqlCreate + "('" + student.getFirstName() + "', '" + student.getSecondName() + "')";
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
