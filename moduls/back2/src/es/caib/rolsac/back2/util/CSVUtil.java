package es.caib.rolsac.back2.util;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

/***
 * Clase utilidad para exportar hacia CSV.
 * 
 * @author slromero
 *
 */
public class CSVUtil {

	/** Caracter de separación. **/
	public final static String CARACTER_SEPARACION_CSV=";";
	
	/** Caracter de separación. **/
	public final static String CARACTER_SALTOLINEA_CSV="\n";
	
	/**
	 * Borra los caracteres que pueden molestar al convertir a CSV.
	 * @param valor
	 * @return
	 */
	public static String limpiar(final String valor) {
		String resultado;
		if (valor == null) {
			resultado = CSVUtil.CARACTER_SEPARACION_CSV;
		} else {
			resultado = valor.trim().replaceAll(";", ",").replaceAll("\n", " ").replace("\r", "")+CSVUtil.CARACTER_SEPARACION_CSV;
		}
		return resultado;
	}
	/**
	 * Borra los caracteres que pueden molestar al convertir a CSV.
	 * @param valor
	 * @return
	 */
	public static String limpiar(final Long valor) {
		String resultado;
		if (valor == null) {
			resultado = CSVUtil.CARACTER_SEPARACION_CSV;
		} else {
			resultado =  limpiar(valor.toString());
		}
		return resultado;
	}
	
	/**
	 * Borra los caracteres que pueden molestar al convertir a CSV.
	 * @param valor
	 * @return
	 */
	public static String limpiar(final Integer valor) {
		String resultado;
		if (valor == null) {
			resultado = CSVUtil.CARACTER_SEPARACION_CSV;
		} else {
			resultado =  limpiar(valor.toString());
		}
		return resultado;
	}
	
	/**
	 * Borra los caracteres que pueden molestar al convertir a CSV.
	 * @param valor
	 * @return
	 */
	public static String limpiar(final boolean valor) {
		String resultado;
		if (valor == true) {
			resultado = "Si"+CSVUtil.CARACTER_SEPARACION_CSV;
		} else {
			resultado = "No"+CSVUtil.CARACTER_SEPARACION_CSV;
		}
		return resultado;
	}
	
	/**
	 * Borra los caracteres que pueden molestar al convertir a CSV.
	 * @param valor
	 * @return
	 */
	public static String limpiar(final Date valor) {
		String resultado;
		if (valor == null) {
			resultado = CSVUtil.CARACTER_SEPARACION_CSV;
		} else {
			resultado =  limpiar(getStringFromDate(valor));
		}
		return resultado;
	}
	
	/**
	 * Convierte una fecha en formato string . El formato de fecha es
	 * "DD-MM-YYYY hh:mi", siendo:
	 * <ul>
	 * <li>yyyy el año.</li>
	 * <li>mm el mes.</li>
	 * <li>dd el día.</li>
	 * <li>hh las horas del día.</li>
	 * <li>mi los minutos del día.</li>
	 * </ul>
	 *
	 * @param date
	 *            Fecha para convertir
	 * @return Texto de la fecha en formato solr.
	 */
	public static String getStringFromDate(final Date date) {
		final StringBuffer fecha = new StringBuffer();
		if (date != null) {
			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			//DD-MM-YYYY
			fecha.append(lpad(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), 2));
			fecha.append("-");
			fecha.append(lpad(String.valueOf(calendar.get(Calendar.MONTH) + 1), 2));
			fecha.append("-");
			fecha.append(lpad(String.valueOf(calendar.get(Calendar.YEAR)), 4));
			
			fecha.append(" ");
			
			//HH:MI
			fecha.append(lpad(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)), 2));
			fecha.append(":");
			fecha.append(lpad(String.valueOf(calendar.get(Calendar.MINUTE)), 2));
			
			
		}
		return fecha.toString();
	}
	

	/**
	 * Devuelve a partir de un texto, aumenta con 0 hasta el tamaño acordado.
	 *
	 * @param i
	 * @param j
	 * @return El texto en formato ldap.
	 */
	private static String lpad(String i, final int j) {
		while (i.length() < j) {
			i = "0" + i;
		}
		return i;
	}
	
	/**
	 * Obtiene el nombre de la UA a partir de las traducciones.
	 * @param ua
	 * @return
	 */
	public static String getNombreUA(UnidadAdministrativa ua) {
		String valor = "";
		if (ua != null) {
			TraduccionUA trad = (TraduccionUA) ua.getTraduccion("ca");
			if (trad == null) {
				 trad = (TraduccionUA) ua.getTraduccion("es");
			}
			if (trad != null) {
				valor = trad.getNombre();
			}
		}
		
		return valor;
	}
	
	/**
	 * Devuelve el objeto CSV a través del response. 
	 * 
	 * @param response
	 * @param archivoCSV
	 */
	public static void mostrarCSV(HttpServletResponse response, String archivoCSV) throws Exception {
			/*
			response.reset();
			response.setContentType("text/csv" );
			response.setHeader("Content-Disposition", "attachment; filename=\"Llistat.csv\"");
	        response.addHeader("cache-response-directive", "no-cache");
			response.setContentLength( archivoCSV.getBytes().length );
			response.getOutputStream().write( archivoCSV.getBytes() );
			*/
			response.setContentType("application/csv; charset=UTF-8");//"application/force-download");
			response.setContentLength((int)archivoCSV.length());
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition","attachment; filename=\"llistat.csv\"");//fileName);
			//response.getOutputStream().print(archivoCSV);
			//response.getOutputStream().print("\n");
			String encoding = "ISO-8859-1"; // encoding =  "UTF-8"; // encoding = "CP1252";
			response.getOutputStream().write(archivoCSV.getBytes(encoding));
			
			response.getOutputStream().flush();
		}

}
