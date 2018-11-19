package by.iba.student.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	@Override
	public List<T> findAll(T filter) {
		List<T> entities = new ArrayList<>();
		try (Connection conn = dataSource.getConnection()) {
			List<Object> params = new ArrayList<>();
			String sqlFindAll = entityDbParser.getSqlFindAllByFilter(filter, params);
			PreparedStatement statement = conn.prepareStatement(sqlFindAll);
			for(int i=0; i < params.size(); i++) {
				statement.setObject(i+1, params.get(i));
			}
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
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(entityDbParser.getSqlFindById(id));
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
			Statement statement = conn.createStatement();
			int updateCount = statement.executeUpdate(entityDbParser.getSqlCreate(entity));			
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
		return entity;
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T update(T entity) {
		
		return null;
	}

}
