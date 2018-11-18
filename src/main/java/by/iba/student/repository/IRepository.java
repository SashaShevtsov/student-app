package by.iba.student.repository;

import java.util.List;

import by.iba.student.common.Entity;

public interface IRepository<T extends Entity> {
	public List<T> findAll(T filter);
	public T findById(String id);
	public T create(T entity);
	public void remove(String id);
	public T update(T entity);
}
