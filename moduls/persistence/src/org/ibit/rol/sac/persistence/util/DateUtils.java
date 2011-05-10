package org.ibit.rol.sac.persistence.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utilidades de fechas.
 */
public class DateUtils {

    /**
     * Obtiene la fecha actual, con precisi&oacute;n de dias.
     * Fijada a les 00:00:00 000ms.
     * @return Fecha de hoy a media noche.
     */
    public static java.util.Date today() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
    
    
    public static  java.util.Date HoyHora() {
    	 									
		 
    	 Calendar calendar = new GregorianCalendar();
        
    	   return calendar.getTime();
         
    }
    
    
	/**
	 * Calcula si dos fechas son iguales. No importan horas y minutos.
	 * En caso de que sean iguales retorna true
	 * @param fecha1
	 * @param fecha2
	 * @return boolean
	 */
	public static boolean fechasIguales(Date fecha1, Date fecha2) {
		boolean retorno=false;
		
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		
		//si alguna fecha es nula  >>> salimos indicando false.
		if ((fecha1==null) || (fecha2==null)) {
			return false;
		}

		c1.setTime(fecha1);
		c2.setTime(fecha2);
		if ( (c1.get( Calendar.YEAR  )	==c2.get( Calendar.YEAR  )) &&
			 (c1.get( Calendar.MONTH  )	==c2.get( Calendar.MONTH  )) &&
			 (c1.get( Calendar.DATE  )	==c2.get( Calendar.DATE  ))
				) 
			retorno=true;
		else 
			retorno = false;	
		return retorno;
	}

	public static String formatearddMMyyyy (Date fecha) {
		SimpleDateFormat fmt = new SimpleDateFormat ("dd/MM/yyyy");											
		return fmt.format (fecha);
	}

	
	public static String formatearddMMyyyyHHmm (Date fecha) {
		SimpleDateFormat fmt = new SimpleDateFormat ("dd/MM/yyyy HH:mm");											
		return fmt.format (fecha);
	}
}
