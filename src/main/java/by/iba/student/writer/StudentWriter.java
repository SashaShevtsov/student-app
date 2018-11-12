package by.iba.student.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import by.iba.student.common.Student;

public class StudentWriter {
	public final String path;

	public StudentWriter(String path) {
		super();
		this.path = path;
	}
	
	public void write(List<Student> students) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			if(students!=null) {
				for(Student st: students) {
					String line = String.format("%s;%s;%s",
							st.getId(),
							st.getFirstName(),
							st.getSecondName());
					bw.write(line);
					bw.newLine();
				}
			}
		}
	}
}
