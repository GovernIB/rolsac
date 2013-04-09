package es.caib.rolsac.api.v2.general;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.ibit.rol.sac.model.Periodo;

public class PeriodoUtil {

    public static Periodo crearPeriodoMes() {
        return crearPeriodoMes(new Date());
    }

    public static Periodo crearPeriodoMes(Date fecha) {
        Calendar calendario = obtenerCalendario(fecha);
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        Periodo periodo = new Periodo();
        periodo.setFechaInicio(calendario.getTime());
        calendario.roll(Calendar.DAY_OF_MONTH, -1);
        periodo.setFechaFin(calendario.getTime());
        return periodo;
    }

    public static List crearListaMeses(Date fechaInicio, Date fechaFin) {
        Calendar calendario = obtenerCalendario(fechaInicio);
        List listaPeriodos = new ArrayList();
        Periodo periodoMes = crearPeriodoMes(calendario.getTime());
        listaPeriodos.add(periodoMes.clone());
        while (!periodoMes.contains(fechaFin)) {
            calendario.add(Calendar.MONTH, 1);
            periodoMes = crearPeriodoMes(calendario.getTime());
            listaPeriodos.add(periodoMes.clone());
        }
        return listaPeriodos;
    }

    public static Periodo crearPeriodoAnual() {
        Calendar calendario = obtenerCalendario();
        Periodo periodo = new Periodo();
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        periodo.setFechaFin(calendario.getTime());
        calendario.add(Calendar.MONTH, -11);
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMinimum(Calendar.DAY_OF_MONTH));
        periodo.setFechaInicio(calendario.getTime());
        return periodo;
    }

    public static Periodo crearPeriodoSemestral() {
        Calendar calendario = obtenerCalendario();
        Periodo periodo = new Periodo();
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        periodo.setFechaFin(calendario.getTime());
        calendario.add(Calendar.MONTH, -5);
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMinimum(Calendar.DAY_OF_MONTH));
        periodo.setFechaInicio(calendario.getTime());
        return periodo;
    }

    public static Periodo crearPeriodoTrimestral() {
        Calendar calendario = obtenerCalendario();
        Periodo periodo = new Periodo();
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        periodo.setFechaFin(calendario.getTime());
        calendario.add(Calendar.MONTH, -2);
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMinimum(Calendar.DAY_OF_MONTH));
        periodo.setFechaInicio(calendario.getTime());
        return periodo;
    }

    private static Calendar obtenerCalendario() {
        Calendar calendario = new GregorianCalendar();
        limpiarHora(calendario);
        return calendario;
    }

    private static Calendar obtenerCalendario(Date fecha) {
        Calendar calendario = new GregorianCalendar();
        calendario.setTime(fecha);
        limpiarHora(calendario);
        return calendario;
    }

    private static void limpiarHora(Calendar calendario) {
        calendario.clear(Calendar.MILLISECOND);
        calendario.clear(Calendar.SECOND);
        calendario.clear(Calendar.MINUTE);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
    }

    public static Date getNextDay() {
        return getNextDay(new Date());
    }
    
    public static Date getNextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}