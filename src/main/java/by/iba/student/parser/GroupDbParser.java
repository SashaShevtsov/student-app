package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.iba.student.common.Group;
import by.iba.student.filter.GroupFilter;
import by.iba.student.util.StringUtil;

public class GroupDbParser extends EntityDbParser<Group>{
	public GroupDbParser() {
		super("GROUP");
		sqlCreate += " (GROUP_NUMBER) VALUES ";
	}

	@Override
	public String getSqlFindById(String id) {
		return sqlFindAll + " WHERE GROUP_NUMBER='"+id+"'";
	}
	
	@Override
	public String getSqlFindAllByFilter(Group filter) {
		GroupFilter groupFilter = (GroupFilter) filter;
		return sqlFindAll + " WHERE GROUP_NUMBER LIKE '"+filter.getId()+"%'";
	}
	
	@Override
	public String getSqlCreate(Group group) {
		return sqlCreate + "('" + group.getId() + "')";
	}
	
	@Override
	public Group parse(ResultSet resultSet) throws SQLException {
		Group group = new Group();
		group.setId(StringUtil.trim(resultSet.getString("GROUP_NUMBER")));
		return group;
	}
}
