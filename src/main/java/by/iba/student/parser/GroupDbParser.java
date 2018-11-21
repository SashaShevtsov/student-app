package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Group;
import by.iba.student.filter.GroupFilter;
import by.iba.student.util.StringUtil;

public class GroupDbParser extends EntityDbParser<Group>{
	public GroupDbParser() {
		super("GROUP");
		sqlRemove += " WHERE GROUP_NUMBER=?";
	}

	@Override
	protected void formParamsToAdd(Group group, List<String> fieldsToAdd, List<Object> params) {
		checkParamToAdd("GROUP_NUMBER", group.getId(), fieldsToAdd, params);
	}
	
	@Override
	protected void setProperId(String id, List<Object> params) {
		params.add(id);
	}
	
	@Override
	public String getSqlFindById(String id, List<Object> params) {
		setProperId(id, params);
		return sqlFindAll + " WHERE GROUP_NUMBER=?";
	}
	
	@Override
	public String getSqlFindAllByFilter(Group filter, List<Object> params) {
		GroupFilter groupFilter = (GroupFilter) filter;
		return sqlFindAll + " WHERE " +
		        SqlHelper.addLike(params, "GROUP_NUMBER", groupFilter.getId(), "AND") +
		        "1=1";
	}
	
	@Override
	public String getSqlCreate(Group group, List<Object> params) {
		List<String> fieldsToAdd = new ArrayList<>();
		formParamsToAdd(group, fieldsToAdd, params);
		return sqlCreate + SqlHelper.addCreate(fieldsToAdd);
	}
	
	@Override
	public String getSqlRemove(String id, List<Object> params) {
		setProperId(id, params);
		return sqlRemove;
	}
	
	@Override
	public Group parse(ResultSet resultSet) throws SQLException {
		Group group = new Group();
		group.setId(StringUtil.trim(resultSet.getString("GROUP_NUMBER")));
		return group;
	}
}
