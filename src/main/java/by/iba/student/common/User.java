package by.iba.student.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends Entity{
	private String userName;
	private String password;
	private String role;
	
	public User() {}

	public User(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:db2://172.20.2.116:5035/DALLASB",
				 "BEGANSS",
				 "jun01jun");
		    statement = conn.createStatement();
		    String sql = "SELECT * from BEGANSS.STUDENT";
		    rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(String.format("id=%s, firstName=%s, secondName=%s, groupId=%s",
						rs.getInt("STUDENT_ID"),
						rs.getString("FIRST_NAME"),
						rs.getString("SECOND_NAME"),
						rs.getString("GROUP_NUMBER")));
			}
		} finally {
			if(conn!=null) {
				conn.close();
			}
		}
	}
}
