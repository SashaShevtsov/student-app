package by.iba.student.reader;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.repository.EntityRepository;

public class StudentLineMapper implements LineMapper<Student> {

	private EntityRepository<Group> groupRepository;
	
	public StudentLineMapper(EntityRepository<Group> groupRepository) {
		this.groupRepository = groupRepository;
	}
	
	@Override
	public Student mapLine(String line) {
		String[] data = line.split(";");
		Student student = new Student(data[1], data[2]);
		student.setId(data[0]);
		String groupId = data[3];
		Group group = groupRepository.findById(groupId);
		student.setGroup(group);
		return student;
	}
	
}
