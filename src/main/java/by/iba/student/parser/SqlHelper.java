package by.iba.student.parser;

import java.util.List;

import by.iba.student.util.StringUtil;

public class SqlHelper {
	
	public static String addLike(List<Object> params, String field, String value, String operator) {
		String sql = "";
		if(!StringUtil.isEmpty(value)) {
			sql = field + " LIKE ? " + operator + " ";
			params.add(value + "%");
		}
		return sql;
	}
	
	public static String addCreate(List<String> fieldsToAdd) {
		if(fieldsToAdd.size() == 0)
			return "";
		String sql = " (";
		for(int i=0;i<fieldsToAdd.size();i++) {
			sql+=fieldsToAdd.get(i);
			if(i!=fieldsToAdd.size()-1)
				sql+=",";
		}
		sql+=") VALUES (?";
		for(int i=0;i<fieldsToAdd.size()-1;i++) {
			sql+=",?";
		}
		sql+=")";
		return sql;
	}

}
