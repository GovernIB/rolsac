package es.caib.rolsac.utils;

import org.apache.commons.logging.Log;

public class LogUtils {
	
	public static void logException(Log log, Throwable t) {
		log.error("", t);
	}
	
	public static void logException(Log log, String msg, Throwable t) {
		log.error(msg, t);
	}

}
