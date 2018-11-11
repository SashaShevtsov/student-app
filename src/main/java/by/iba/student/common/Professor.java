package by.iba.student.common;

import java.util.Date;

public class Professor {
	private String firstName;
	private String secondName;
	private String fatherName;
	
	private Date birthDate;

	public Professor() {
		super();
	}

	public Professor(String firstName, String secondName, String fatherName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.fatherName = fatherName;
		this.birthDate = birthDate;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
