package es.caib.rolsac.api.v2.procediment.co;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ibit.rol.sac.model.Validacion;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByActivoCriteria implements CriteriaObject {

    private String alias;
    private boolean activo;

    public ProcedimentByActivoCriteria(String alias) {
        this.alias = alias + ".";
    }

    public void parseCriteria(String criteria) {
        activo = BasicUtils.stringToBoolean(criteria);
    }

    public void extendCriteria(QueryBuilder qb) {
        if (activo) {
            addProcedimientosActivos(qb);
        } else {
            addProcedimientosCaducados(qb);
        }
    }
    
    /**
     * SELECT DISTINCT p FROM ProcedimientoLocal AS p, p.traducciones AS trad WHERE INDEX(trad) = :ca AND (
     *   p.validacion = :p.validacion 
     *   AND (p.fechaCaducidad >= :p.fechaCaducidad OR p.fechaCaducidad is null) 
     *   AND (p.fechaPublicacion <= :p.fechaPublicacion OR p.fechaPublicacion is null) )
     *        Parameters:
     *            key: ca, value: ca
     *            key: p.fechaCaducidad, value: Thu May 31 00:00:00 ACT 2012
     *            key: p.validacion, value: 1
     *            key: p.fechaPublicacion, value: Thu May 31 00:00:00 ACT 2012
     * @param qb
     */
    private void addProcedimientosActivos(QueryBuilder qb) {
        Date fecha = BasicUtils.getNextDay();
        
        qb.openParenthesis(LOGIC.AND);
        
        qb.addRestriction(new Restriction(alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
        
        List<Restriction> restrictions = new ArrayList<Restriction>(2);
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.GE, fecha));
        restrictions.add(new Restriction(LOGIC.OR, alias + "fechaCaducidad", OPERATION.NULL));
        qb.addGroupedRestrictions(restrictions);
        
        restrictions = new ArrayList<Restriction>(2);
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.LE, fecha));
        restrictions.add(new Restriction(LOGIC.OR, alias + "fechaPublicacion", OPERATION.NULL));
        qb.addGroupedRestrictions(restrictions);

        qb.closeParenthesis();
    }

    /**
     * SELECT DISTINCT p FROM ProcedimientoLocal AS p, p.traducciones AS trad WHERE INDEX(trad) = :ca AND (  
     *   p.validacion != :p.validacion 
     *   OR (p.validacion = :p.validacion AND p.fechaCaducidad < :p.fechaCaducidad) 
     *   OR (p.validacion = :p.validacion AND p.fechaCaducidad is null AND p.fechaPublicacion > :p.fechaPublicacion) 
     *   OR (p.validacion = :p.validacion AND p.fechaCaducidad >= :p.fechaCaducidad AND p.fechaPublicacion > :p.fechaPublicacion) )
     *       Parameters:
     *           key: ca, value: ca
     *           key: p.fechaCaducidad, value: Thu May 31 00:00:00 ACT 2012
     *           key: p.validacion, value: 1
     *           key: p.fechaPublicacion, value: Thu May 31 00:00:00 ACT 2012
     * @param qb
     */
    private void addProcedimientosCaducados(QueryBuilder qb) {
        Date fecha = BasicUtils.getNextDay();
        
        qb.openParenthesis(LOGIC.AND);
        
        qb.addRestriction(new Restriction(alias + "validacion", OPERATION.NEQ, Validacion.PUBLICA));

        List<Restriction> restrictions = new ArrayList<Restriction>(2);
        restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.LT, fecha));
        qb.addGroupedRestrictions(restrictions);
        
        restrictions = new ArrayList<Restriction>(2);
        restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.NULL));
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.GT, fecha));
        qb.addGroupedRestrictions(restrictions);
        
        restrictions = new ArrayList<Restriction>(2);
        restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.GE, fecha));
        restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.GT, fecha));
        qb.addGroupedRestrictions(restrictions);
        
        qb.closeParenthesis();
    }

}
