package by.iba.student.reader;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.repository.EntityRepository;
import by.iba.student.repository.GroupRepository;

public class StudentLineMapper implements LineMapper<Student>{
	
	private EntityRepository<Group> repository;
	
	public StudentLineMapper(EntityRepository<Group> repository){
		this.repository = repository;
	}
	
	@Override
	public Student mapLine(String line) {
		String[] data = line.split(";");
		Student student = new Student(data[1], data[2]);
		student.setId(data[0]);
		String groupId = data[3];
		Group group = repository.findOne(groupId);
		student.setGroup(group);
		return student;
	}

}
