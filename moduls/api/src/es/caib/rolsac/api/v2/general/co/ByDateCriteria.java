package es.caib.rolsac.api.v2.general.co;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.ibit.rol.sac.model.Periodo;

import es.caib.rolsac.api.v2.general.PeriodoUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public abstract class ByDateCriteria implements CriteriaObject {

    // "dd/MM/yyyy HH:mm:ss"
    public final static SimpleDateFormat DATE_CRITERIA_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    private String field;
    private Periodo periodo;

    protected ByDateCriteria(String field) {
        this.field = field;
    }

    /**
     * Use cases: "dd/MM/yyyy", "dd/MM/yyyy-dd/MM/yyyy", "dd/MM/yyyy-", "-dd/MM/yyyy"
     * 
     * @param criteria
     * @throws CriteriaObjectParseException
     */
    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        String input = criteria.trim();
        String[] fechas = input.split("-");
        try {
            if (fechas.length < 1 || fechas.length > 2 || (fechas.length == 2 && input.endsWith("-"))) {
                throw new ParseException(criteria, 0); 
            }
            
            if (input.startsWith("-")) {
                periodo = crearPeriodoHasta(fechas[1].trim());
            } else if (input.endsWith("-")) {
                periodo = crearPeriodoDesde(fechas[0].trim());
            } else if (fechas.length == 1) {
                periodo = crearPeriodo(fechas[0].trim());
            } else {
                periodo = crearPeriodo(fechas[0].trim(), fechas[1].trim());
            }

        } catch (ParseException e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.addRestriction(new Restriction(field, OPERATION.DATE, periodo));
    }

    
    private Periodo crearPeriodoDesde(String fecha) throws ParseException {
        Periodo periodo = new Periodo();
        periodo.setFechaInicio(DATE_CRITERIA_FORMATTER.parse(fecha));
        return periodo;
    }
    
    private Periodo crearPeriodoHasta(String fecha) throws ParseException {
        Periodo periodo = new Periodo();
        periodo.setFechaFin(DATE_CRITERIA_FORMATTER.parse(fecha));
        return periodo;
    }
    
    private Periodo crearPeriodo(String fecha) throws ParseException {
        Periodo periodo = crearPeriodoDesde(fecha);
        periodo.setFechaFin(PeriodoUtil.getNextDay(DATE_CRITERIA_FORMATTER.parse(fecha)));
        return periodo;
    }
    
    private Periodo crearPeriodo(String fechaInicio, String fechaFin) throws ParseException {
        Periodo periodo = crearPeriodoDesde(fechaInicio);
        periodo.setFechaFin(PeriodoUtil.getNextDay(DATE_CRITERIA_FORMATTER.parse(fechaFin)));
        return periodo;
    }

}
