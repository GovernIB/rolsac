/**
 * User: jhorrach
 * Date: Feb 6, 2004
 * Time: 1:01:25 PM
 */
package org.ibit.rol.sac.back.form;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

/**
 * Extensi�n de un ActionForm para representar el formulario de fecha
 */
public class DataForm extends ActionForm {

   private Calendar calendari = null;
   private String field = null;

   public DataForm() {
      calendari = new GregorianCalendar();
      calendari.setFirstDayOfWeek(Calendar.MONDAY);
   }

   /* M�todes get i set */
   public int getAny() { return calendari.get(Calendar.YEAR); }
   public void setAny(int any) { calendari.set(Calendar.YEAR, any); }

   public int getMes() { return calendari.get(Calendar.MONTH); }
   public void setMes(int mes) {
	   // Si es dia 31 y mantenemos el dia 31 en un mes que tiene 30 dias , 
	   // automaticamente cambiara al mes siguiente dia 1
	   calendari.set(Calendar.DAY_OF_MONTH, 1);
	   calendari.set(Calendar.MONTH, mes); 
}

   public int getDia() { return calendari.get(Calendar.DAY_OF_MONTH);  }
   public void setDia(int dia) { 	   calendari.set(Calendar.DAY_OF_MONTH, dia);  }

   public String getField() { return field; }
   public void setField(String field) { this.field = field; }

   /* Gets d'informaci� adicional */
   public Date getData() { return calendari.getTime(); }

   public int getFirstDayPos() {
      // Backup del dia
      int diaActual = calendari.get(Calendar.DAY_OF_MONTH);

      // Fixam el primer dia del mes i obtenim la posicio
      int primerDia = calendari.getActualMinimum(Calendar.DAY_OF_MONTH);
      calendari.set(Calendar.DAY_OF_MONTH, primerDia);

      int firstDayPos = calendari.get(Calendar.DAY_OF_WEEK);
      // Restauram el dia
      calendari.set(Calendar.DAY_OF_MONTH, diaActual);

      // Els EUA i el diumenge.
      firstDayPos = ( (firstDayPos == 1) ? (7) : (firstDayPos - 1) );
      return firstDayPos;
   }

   public int getLastDay() {
      return calendari.getActualMaximum(Calendar.DAY_OF_MONTH);
   }

   /* Sobreescriure m�todes de ActionForm */
   public void reset(ActionMapping mapping, HttpServletRequest request) {
      calendari = new GregorianCalendar();
      calendari.setFirstDayOfWeek(Calendar.MONDAY);
      field = null;
   }

   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

      ActionErrors errors = new ActionErrors();
      if (field == null) {
         errors.add("field", new ActionError("error.field") );
      }

      return errors;
   }
}