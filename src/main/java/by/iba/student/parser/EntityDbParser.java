package by.iba.student.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.iba.student.common.Entity;

public abstract class EntityDbParser<T extends Entity> {
	private String tableName;
	
    public String sqlFindAll;
    public String sqlCreate;
    public String sqlRemove;
    public String sqlUpdate;
    
    public EntityDbParser(String tableName) {
    	this.tableName = tableName;
    	sqlFindAll = "SELECT * FROM BEGANSS."+tableName;
    	sqlCreate = "INSERT INTO BEGANSS."+tableName;
    }
    
    public abstract T parse(ResultSet resultSet) throws SQLException;
    public abstract String getSqlFindById(String id);
    public abstract String getSqlCreate(T entity);
    public abstract String getSqlFindAllByFilter(T filter, List<Object> params);
}
