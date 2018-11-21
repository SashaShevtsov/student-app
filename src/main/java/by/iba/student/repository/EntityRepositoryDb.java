package by.iba.student.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import by.iba.student.common.Entity;
import by.iba.student.parser.EntityDbParser;

public class EntityRepositoryDb<T extends Entity> implements IRepository<T> {

	private final DataSource dataSource;
	private final EntityDbParser<T> entityDbParser;
	
	public EntityRepositoryDb(DataSource dataSource, EntityDbParser<T> entityDbParser) {
		this.dataSource = dataSource;
		this.entityDbParser = entityDbParser;
	}
	
	private void setParams(PreparedStatement statement, List<Object> params) throws SQLException {
		for(int i=0; i < params.size(); i++) {
			statement.setObject(i+1, params.get(i));
		}
	}
	
	@Override
	public List<T> findAll(T filter) {
		List<T> entities = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			List<Object> params = new ArrayList<>();
			String sqlFindAll = entityDbParser.getSqlFindAllByFilter(filter, params);
			PreparedStatement statement = conn.prepareStatement(sqlFindAll);
			setParams(statement, params);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				entities.add(entityDbParser.parse(rs));
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return entities;
	}
	
	@Override
	public T findById(String id) {
		T entity = null;
		try (Connection conn = dataSource.getConnection()) {
			List<Object> params = new ArrayList<>();
			String sqlFindById = entityDbParser.getSqlFindById(id, params);
			PreparedStatement statement = conn.prepareStatement(sqlFindById);
			setParams(statement, params);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				entity = entityDbParser.parse(rs);
			}
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return entity;
	}

	@Override
	public T create(T entity) {
		try (Connection conn = dataSource.getConnection()) {
			List<Object> params = new ArrayList<>();
			String sqlCreate = entityDbParser.getSqlCreate(entity, params);
			PreparedStatement statement = conn.prepareStatement(sqlCreate);
			setParams(statement, params);
			int updateCount = statement.executeUpdate();			
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return entity;
	}

	@Override
	public void remove(String id) {
		try (Connection conn = dataSource.getConnection()) {
			List<Object> params = new ArrayList<>();
			String sqlRemove = entityDbParser.getSqlRemove(id, params);
			PreparedStatement statement = conn.prepareStatement(sqlRemove);
			setParams(statement, params);
			int updateCount = statement.executeUpdate();			
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public T update(T entity) {
		
		return null;
	}

}
