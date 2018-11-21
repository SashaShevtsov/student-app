package by.iba.student.common;

public class Student extends Entity{
    private String firstName;
    private String secondName;
    private Group group;

    public Student() {}

    public Student(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}
    
    public Student(String firstName, String secondName, String groupId) {
    	this.firstName = firstName;
		this.secondName = secondName;
		setGroup(new Group(groupId));
    }
    
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
