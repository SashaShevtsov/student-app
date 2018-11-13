package by.iba.student.common;

import java.util.Date;

public class Mark extends Entity {
	private Date date;
	private int mark;
	private String comments;
	
	public Mark() {}

	public Mark(Date date, int mark, String comments) {
		this.date = date;
		this.mark = mark;
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
