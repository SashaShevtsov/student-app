package by.iba.student.writer;

import java.io.IOException;
import java.util.List;

import by.iba.student.common.Entity;

public abstract class EntityWriter<T extends Entity> {
	public final String path;

	public EntityWriter(String path) {
		this.path = path;
	}
	
	public abstract void write(List<T> entities) throws IOException;
}
