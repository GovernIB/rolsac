package es.caib.rolsac.back2.util;


public class ParseUtil {

	public static Long parseLong(String vLong) {
    	try {
    		Long l = Long.parseLong(vLong);
    		return l;
    	} catch (NumberFormatException nfe) {
    		return null;
    	}		
	}
	
	public static Integer parseInt(String vInt) {
		try {
			Integer i = Integer.parseInt(vInt);
			return i;
    	} catch (NumberFormatException nfe) {
    		return null;
    	}		
	}
}
