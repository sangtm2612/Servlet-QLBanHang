package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	public static boolean validate(String regex, String val) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(val);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
