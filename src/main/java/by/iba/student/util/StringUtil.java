package by.iba.student.util;

public class StringUtil {

	public static String trim(String str) {
		return str!=null ? str.trim() : null;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
    }
}
