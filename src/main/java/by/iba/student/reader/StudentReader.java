package by.iba.student.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.iba.student.common.Student;

public class StudentReader {
	private final String path;
	
	public StudentReader(String path) {
		this.path = path;
	}
	
	public List<Student> read() throws IOException{
		List<Student> students = new ArrayList<Student>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while((line = br.readLine())!= null) {
				String[] data = line.split(";");
				Student student = new Student(data[1], data[2]);
				student.setId(data[0]);
				students.add(student);
			}
		} finally {
			if(br!=null)
				br.close();
		}
		
		return students;
	}
}
