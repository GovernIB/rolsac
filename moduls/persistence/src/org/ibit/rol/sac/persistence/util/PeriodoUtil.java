package org.ibit.rol.sac.persistence.util;

import org.ibit.rol.sac.model.Periodo;

import java.util.*;

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


}
