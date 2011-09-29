package es.caib.rolsac.back2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATETIME_FORMAT = DATE_FORMAT + " HH:mm:ss";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat sdtf = new SimpleDateFormat(DATETIME_FORMAT);
	
	
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
			return sdf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
		return null;
	}

}
