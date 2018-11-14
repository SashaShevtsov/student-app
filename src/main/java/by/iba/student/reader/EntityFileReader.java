package by.iba.student.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class EntityFileReader<T> implements EntityReader<T> {
	
	private final String path;
	
	private final LineMapper<T> mapper;
	
	public EntityFileReader(String path, LineMapper<T> mapper) {
		this.path = path;
		this.mapper = mapper;
	}
	
	@Override
	public List<T> read() {
		List<T> items = new ArrayList<T>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while((line = br.readLine())!= null) {
				T item = mapper.mapLine(line);
				items.add(item);
			}
		} catch (Exception ex) {
			 throw new RuntimeException(ex);
		} finally {
			if(br!=null) {
				try {
					br.close();
				} catch (Exception e) {
					//ignore
				}
			}
				
		}
		return items;
	}
	
	
	
}
