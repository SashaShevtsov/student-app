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

}
