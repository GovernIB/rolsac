package es.caib.rolsac.api.v2.general.co;

import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ByTextosCriteria implements CriteriaObject {

    
    /* 
     * Los campos que empizan por I18N_PREFIX son traducibles.
     */
    private String[] textFields = {};
    private String alias;

    // El mismo que el HQL_TRADUCCIONES_ALIAS de los EJBs.
    private String i18nAlias;   
    private String i18nPrefix;

    protected String text;

    private boolean likeFlag;

    public ByTextosCriteria(String alias, String[] textFields, String i18nAlias, String i18nPrefix ) {
        this.alias = alias;
        this.textFields = textFields;
        this.i18nAlias = i18nAlias;
        this.i18nPrefix = i18nPrefix;
    }

    public void parseCriteria(String criteria) {
        text = criteria;
        likeFlag = text.contains("%");
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.openParenthesis(LOGIC.AND);
        
        OPERATION operation = likeFlag ? OPERATION.LIKE : OPERATION.EQ;
        List<Restriction> restrictions = new ArrayList<Restriction>(textFields.length);
        for (String textField: textFields) {
            restrictions.add(new Restriction(LOGIC.OR, getTextField(textField), operation, text));
        }
        qb.addGroupedRestrictions(restrictions);

        qb.closeParenthesis();
    }
    
    private String getTextField(String textField) {
        if (textField.startsWith(i18nPrefix)) {
            return i18nAlias + "." + textField.substring(i18nPrefix.length());
        } else {
            return alias + "." + textField;
        }
    }

}
