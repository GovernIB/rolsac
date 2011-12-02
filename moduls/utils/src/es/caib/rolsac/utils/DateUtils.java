package es.caib.rolsac.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {
	
	private static Log log = LogFactory.getLog(DateUtils.class);
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATETIME_FORMAT = DATE_FORMAT + " HH:mm:ss";
	public static final String DATE_SIMPLE_TIME_FORMAT = DATE_FORMAT + " HH:mm";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat sdtf = new SimpleDateFormat(DATETIME_FORMAT);
	private static final SimpleDateFormat sdstf = new SimpleDateFormat(DATE_SIMPLE_TIME_FORMAT);
	
	
	public static String formatDate(Date date) {
		try {
			return sdf.format(date);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static String formatDatetime(Date datetime) {
		try {
			return sdtf.format(datetime);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static String formatDateSimpleTime(Date datetime) {
		try {
			return sdstf.format(datetime);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static Date parseDate(String text) {
		try {
			return sdf.parse(text);
		} catch (ParseException e) {
			//log.error(ExceptionUtils.getStackTrace(e));
		} catch (NullPointerException e) {
		} 
		return null;
	}
	
	public static Date parseDatetime(String text) {
		try {
			return sdtf.parse(text);
		} catch (ParseException e) {
			//log.error(ExceptionUtils.getStackTrace(e));
		} catch (NullPointerException e) {
		}
		return null;
	}

	public static Date parseDateSimpleTime(String text) {
		try {
			return sdstf.parse(text);
		} catch (ParseException e) {
			//log.error(ExceptionUtils.getStackTrace(e));
		} catch (NullPointerException e) {
		}
		return null;
	}
}
