package es.caib.rolsac.api.v2.procediment.co;

import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByTextosCriteria implements CriteriaObject {

    // El mismo que el HQL_TRADUCCIONES_ALIAS de los EJBs.
    private static final String I18N_ALIAS = "trad";   
    private static final String I18N_PREFIX = "t_";
    
    /* 
     * De ProcedimentCriteria.java y ProcedimientCriteria.java.
     * Los campos que empizan por I18N_PREFIX son traducibles.
     */
    private static final String[] TEXT_FIELDS = {
        "dirElectronica", "responsable", "tramite", "url", "signatura",
        I18N_PREFIX + "destinatarios",
        I18N_PREFIX + "lugar",
        I18N_PREFIX + "nombre", 
        I18N_PREFIX + "notificacion",
        I18N_PREFIX + "observaciones",
        I18N_PREFIX + "plazos",
        I18N_PREFIX + "recursos", 
        I18N_PREFIX + "requisitos",
        I18N_PREFIX + "resolucion", 
        I18N_PREFIX + "resultat", 
        I18N_PREFIX + "resumen",
        I18N_PREFIX + "silencio"
    };
    
    private String alias;
    private String text;
    private boolean likeFlag;

    public ProcedimentByTextosCriteria(String alias) {
        this.alias = alias;
    }

    public void parseCriteria(String criteria) {
        text = criteria;
        likeFlag = text.contains("%");
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.openParenthesis(LOGIC.AND);
        
        OPERATION operation = likeFlag ? OPERATION.LIKE : OPERATION.EQ;
        List<Restriction> restrictions = new ArrayList<Restriction>(TEXT_FIELDS.length);
        for (String textField: TEXT_FIELDS) {
            restrictions.add(new Restriction(LOGIC.OR, getTextField(textField), operation, text));
        }
        qb.addGroupedRestrictions(restrictions);

        qb.closeParenthesis();
    }
    
    private String getTextField(String textField) {
        if (textField.startsWith(I18N_PREFIX)) {
            return I18N_ALIAS + "." + textField.substring(I18N_PREFIX.length());
        } else {
            return alias + "." + textField;
        }
    }

}
