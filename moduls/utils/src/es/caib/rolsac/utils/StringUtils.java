package es.caib.rolsac.utils;

public class StringUtils {
	
	//Checks if a String is empty ("") or null.
	public static boolean isEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}
	
	//Checks if a String is whitespace, empty ("") or null.
	public static boolean isBlank(String str) {
		return org.apache.commons.lang.StringUtils.isBlank(str);
	}
	
	//Checks if a String is not empty (""), not null and not whitespace only.
	public static boolean isNotBlank(String str) {
		return org.apache.commons.lang.StringUtils.isNotBlank(str);
	}
	
	
	//Checks if a String is not empty ("") and not null.
	public static boolean isNotEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isNotEmpty(str);
	}
	
}
