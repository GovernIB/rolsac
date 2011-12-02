package es.caib.rolsac.utils;

public class ExceptionUtils {
	
	public static String getStackTrace(Exception ex) {
		return org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
	}
}
