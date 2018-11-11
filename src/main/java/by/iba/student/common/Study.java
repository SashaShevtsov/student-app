package by.iba.student.common;

public class Study {
	private String name;
	
	private int hours;

	public Study() {
		super();
	}

	public Study(String name, int hours) {
		super();
		this.name = name;
		this.hours = hours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
