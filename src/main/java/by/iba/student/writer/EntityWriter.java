package by.iba.student.writer;

import java.io.IOException;
import java.util.List;

import by.iba.student.common.Entity;

public abstract class EntityWriter {
	public final String path;

	public EntityWriter(String path) {
		this.path = path;
	}
	
	public abstract void write(List<Entity> entities) throws IOException;
}
