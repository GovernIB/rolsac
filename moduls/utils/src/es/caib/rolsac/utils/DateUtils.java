package es.caib.rolsac.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public static String formatDate(final Date date) {
		try {
			return sdf.format(date);
		} catch (final NullPointerException e) {
			return null;
		}
	}

	public static String formatDatetime(final Date datetime) {
		try {
			return sdtf.format(datetime);
		} catch (final NullPointerException e) {
			return null;
		}
	}

	public static String formatDateSimpleTime(final Date datetime) {
		try {
			return sdstf.format(datetime);
		} catch (final NullPointerException e) {
			return null;
		}
	}

	public static Date parseDate(final String text) {
		try {
			return sdf.parse(text);
		} catch (final ParseException e) {
			// log.error(ExceptionUtils.getStackTrace(e));
		} catch (final NullPointerException e) {
		}
		return null;
	}

	public static Date parseDatetime(final String text) {
		try {
			return sdtf.parse(text);
		} catch (final ParseException e) {
			// log.error(ExceptionUtils.getStackTrace(e));
		} catch (final NullPointerException e) {
		}
		return null;
	}

	public static Date parseDateSimpleTime(final String text) {
		try {
			return sdstf.parse(text);
		} catch (final ParseException e) {
			// log.error(ExceptionUtils.getStackTrace(e));
		} catch (final NullPointerException e) {
		}
		return null;
	}

	/**
	 * Devuelve el dia en formato oracle "dd/MM/yyyy" restandole a hoy los dias que
	 * se pasen.
	 *
	 * @param days
	 * @return
	 */
	public static String getFechaOracle(final int days) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1 * days);
		final StringBuilder fecha = new StringBuilder();
		final int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			fecha.append("0");
		}
		fecha.append(day);
		fecha.append("/");
		final int mes = cal.get(Calendar.MONTH);
		if (mes < 10) {
			fecha.append("0");
		}
		fecha.append(mes);
		fecha.append("/");
		fecha.append(cal.get(Calendar.YEAR));
		return fecha.toString();
	}
}
