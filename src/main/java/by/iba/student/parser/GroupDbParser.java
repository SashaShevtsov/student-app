package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.filter.GroupFilter;
import by.iba.student.filter.StudentFilter;
import by.iba.student.util.StringUtil;

public class GroupDbParser extends EntityDbParser<Group>{
	public GroupDbParser() {
		super("GROUP", "GROUP_NUMBER");
		sqlUpdate += " GROUP_NUMBER=? WHERE GROUP_NUMBER=?";
	}

	@Override
	protected void formParamsToAdd(Group group, List<String> fieldsToAdd, List<Object> params) {
		checkParamToAdd("GROUP_NUMBER", group.getId(), fieldsToAdd, params);
	}
	
	@Override
	protected void formFilterParamsToAdd(Group entityFilter, List<String> fieldsToAdd, List<Object> params) {
		GroupFilter groupFilter = (GroupFilter) entityFilter;
		checkParamToAdd("GROUP_NUMBER", groupFilter.getId(), fieldsToAdd, params);
	}
	
	@Override
	protected void setProperId(String id, List<Object> params) {
		params.add(id);
	}
	
	@Override
	public Group parse(ResultSet resultSet) throws SQLException {
		Group group = new Group();
		group.setId(StringUtil.trim(resultSet.getString("GROUP_NUMBER")));
		return group;
	}
}
