package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Student;
import by.iba.student.util.StringUtil;

public abstract class EntityDbParser<T extends Entity> {
	private String tableName;
	
    protected String sqlFindAll;
    protected String sqlCreate;
    protected String sqlRemove;
    protected String sqlUpdate;
    
    public EntityDbParser(String tableName) {
    	this.tableName = tableName;
    	sqlFindAll = "SELECT * FROM BEGANSS."+tableName;
    	sqlCreate = "INSERT INTO BEGANSS."+tableName;
    	sqlRemove = "DELETE FROM BEGANSS."+tableName;
    }
    
    protected void checkParamToAdd(String field, String value, List<String> fieldsToAdd,  List<Object> params) {
		if(!StringUtil.isEmpty(value)) {
			fieldsToAdd.add(field);
			params.add(value);
		}
	}
    
    protected abstract void formParamsToAdd(T entity, List<String> fieldsToAdd, List<Object> params); 
    protected abstract void setProperId(String id, List<Object> params);
    public abstract T parse(ResultSet resultSet) throws SQLException;
    public abstract String getSqlFindById(String id, List<Object> params);
    public abstract String getSqlCreate(T entity, List<Object> params);
    public abstract String getSqlRemove(String id, List<Object> params);
    public abstract String getSqlFindAllByFilter(T filter, List<Object> params);
}
