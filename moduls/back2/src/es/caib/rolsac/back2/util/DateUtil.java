package es.caib.rolsac.back2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

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
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
		return null;
	}
	
	public static Date parseDatetime(String text) {
		try {
			return sdtf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
		return null;
	}

	public static Date parseDateSimpleTime(String text) {
		try {
			return sdstf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
		return null;
	}
}
