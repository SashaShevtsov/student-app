package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Student;
import by.iba.student.util.StringUtil;

public abstract class EntityDbParser<T extends Entity> {
	private final String dbName = "BEGANSS";
	private String tableName;
	private String idColumn;
	
    protected String sqlFindAll;
    protected String sqlCreate;
    protected String sqlRemove;
    protected String sqlUpdate;
    
    
    public EntityDbParser(String tableName, String idColumn) {
    	this.tableName = tableName;
    	this.idColumn = idColumn;
    	sqlFindAll = "SELECT * FROM " + dbName + "." + tableName;
    	sqlCreate = "INSERT INTO " + dbName + "." + tableName;
    	sqlRemove = "DELETE FROM " + dbName + "." + tableName + 
    			" WHERE " + idColumn + "=?";
    	sqlUpdate = "UPDATE " + dbName + "." + tableName + " SET ";
    }
    
    protected void setProperId(String id, List<Object> params) {
		params.add(Integer.valueOf(id));
	}
    
    protected void checkParamToAdd(String field, String value, List<String> fieldsToAdd,  List<Object> params) {
		if(!StringUtil.isEmpty(value)) {
			fieldsToAdd.add(field);
			params.add(value);
		}
	}
    
    public String getSqlFindAllByFilter(T entityFilter, List<Object> params) {
    	List<String> fieldsToAdd = new ArrayList<>();
    	formFilterParamsToAdd(entityFilter, fieldsToAdd, params);
    	for(Object param: params) {
			param+="%";
		}
    	return sqlFindAll + SqlHelper.addLikes(fieldsToAdd);
    }
    
    public String getSqlFindById(String id, List<Object> params) {
    	setProperId(id, params);
		return sqlFindAll + " WHERE " + idColumn + "=?";
    }
    
    public String getSqlCreate(T entity, List<Object> params) {
    	List<String> fieldsToAdd = new ArrayList<>();
		formParamsToAdd(entity, fieldsToAdd, params);
		return sqlCreate + SqlHelper.addCreate(fieldsToAdd);
    }
    
    public String getSqlRemove(String id, List<Object> params) {
		setProperId(id, params);
		return sqlRemove;
	}
    
    public String getSqlUpdate(T entity,  List<Object> params) {
    	List<String> fieldsToAdd = new ArrayList<>();
		formParamsToAdd(entity, fieldsToAdd, params);
		setProperId(entity.getId(), params);
		return sqlUpdate;
    }
    
    protected abstract void formParamsToAdd(T entity, List<String> fieldsToAdd, List<Object> params); 
    protected abstract void formFilterParamsToAdd(T entityFilter, List<String> fieldsToAdd, List<Object> params);
    public abstract T parse(ResultSet resultSet) throws SQLException;
}
