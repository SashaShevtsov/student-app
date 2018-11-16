package by.iba.student.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import by.iba.student.common.Entity;
import by.iba.student.common.Student;

public class StudentWriter extends EntityWriter<Student>{
	
	public StudentWriter(String path) {
		super(path);
	}
	
	@Override
	public void write(List<Student> students) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			if(students!=null) {
				for(Entity st: students) {
					Student student = (Student)st;
					String line = String.format("%s;%s;%s;%s",
							student.getId(),
							student.getFirstName(),
							student.getSecondName(),
							student.getGroup().getId());
					bw.write(line);
					bw.newLine();
				}
			}
		}
	}

}
