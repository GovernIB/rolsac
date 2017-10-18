package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByTextosCriteria;

public class FitxaByTextosCriteria extends ByTextosCriteria {

    // El mismo que el HQL_TRADUCCIONES_ALIAS de los EJBs.
    private static final String I18N_ALIAS = "trad";   
    private static final String I18N_PREFIX = "t_";
    
    /* 
     * De FitxaCriteria.java y FitxaCriteria.java.
     * Los campos que empizan por I18N_PREFIX son traducibles.
     */
    private static final String[] TEXT_FIELDS = {
    	I18N_PREFIX +"urlVideo", 
    	I18N_PREFIX +"urlForo", 
    	"foro_tema", "info", "responsable",
        I18N_PREFIX + "titulo",
        I18N_PREFIX + "descAbr",
        I18N_PREFIX + "descripcion", 
        I18N_PREFIX + "url"
    };
    
    public FitxaByTextosCriteria(String alias) {
        super(alias, TEXT_FIELDS, I18N_ALIAS, I18N_PREFIX);
    }

}
