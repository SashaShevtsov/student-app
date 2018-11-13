package by.iba.student.common;

public class Group extends Entity{
	private int groupNumber;

	public Group() {}

	public Group(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
}
