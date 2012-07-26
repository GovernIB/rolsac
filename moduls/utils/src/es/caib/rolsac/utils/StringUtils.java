package es.caib.rolsac.utils;

public class StringUtils {
	
	//Checks if a String is empty ("") or null.
	public static boolean vacio(String str) {
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}
	
	//Checks if a String is not empty ("") and not null.
	public static boolean relleno(String str) {
		return org.apache.commons.lang.StringUtils.isNotEmpty(str);
	}
	
}
