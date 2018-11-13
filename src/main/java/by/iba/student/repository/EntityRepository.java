package by.iba.student.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import by.iba.student.common.Entity;

public abstract class EntityRepository {
    private final Map<String, Entity> entities = new LinkedHashMap<>();
	
	public EntityRepository(List<Entity> entities) {
		if(entities!=null) {
			for(Entity ent: entities)
				this.entities.put(ent.getId(), ent);
		}
	}
	
	public List<Entity> findAll(){
		return new ArrayList<>(entities.values());
	}
	
	public Entity create(Entity entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);
		entities.put(id, entity);
		return entity;
	}
	
	public void remove (String id) {
		entities.remove(id);
	}
	
	public Entity update(Entity entity) {
		entities.put(entity.getId(), entity);
		return entity;
	}
}
