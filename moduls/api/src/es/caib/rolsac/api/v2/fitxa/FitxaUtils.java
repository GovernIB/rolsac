package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.co.FitxaByActivoCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;

public class FitxaUtils {
    
    public static void parseActiu(List<CriteriaObject> criteris, FitxaCriteria fc, String alias) {
    	
        if (fc.getActiu() != null) {
        	
        	FitxaByActivoCriteria c = new FitxaByActivoCriteria(alias);
            c.parseCriteria(BasicUtils.booleanToString(fc.getActiu()));
            criteris.add(c);
            fc.setActiu(null); // Para que no lo parsee BasicUtils.
            
        }
        
    }
    
}
