package by.iba.student.filter;

import by.iba.student.common.Student;

public class StudentFilter extends Student{
	private String groupId;
	
	public StudentFilter() {}
	
	public StudentFilter(String firstName, String secondName, String groupId) {
		super(firstName, secondName);
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
