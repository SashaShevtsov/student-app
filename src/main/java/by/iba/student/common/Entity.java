package by.iba.student.common;

public abstract class Entity {
	private String id;
	
	public Entity() {}
	
	public Entity(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
}
