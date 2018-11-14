package by.iba.student.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import by.iba.student.common.Entity;

public abstract class EntityRepository<T extends Entity> {
    private final Map<String, T> entities = new LinkedHashMap<>();
	
	public EntityRepository(List<T> entities) {
		if(entities!=null) {
			for(T ent: entities)
				this.entities.put(ent.getId(), ent);
		}
	}
	
	public List<T> findAll(){
		return new ArrayList<>(entities.values());
	}
	
	public T findOne(String id) {
		return entities.get(id);
	}
	
	public T create(T entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);
		entities.put(id, entity);
		return entity;
	}
	
	public void remove (String id) {
		entities.remove(id);
	}
	
	public T update(T entity) {
		entities.put(entity.getId(), entity);
		return entity;
	}
}
