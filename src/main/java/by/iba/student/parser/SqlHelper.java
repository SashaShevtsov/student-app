package by.iba.student.parser;

import java.util.List;

import by.iba.student.util.StringUtil;

public class SqlHelper {
	
	public static String addLikes(List<String> fieldsToAdd) {
		String sql = " WHERE ";
		for(String field: fieldsToAdd){
			sql += field + " LIKE ? AND ";
		}
		sql+="1=1";
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
