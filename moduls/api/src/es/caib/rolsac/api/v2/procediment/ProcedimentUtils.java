package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.procediment.co.ProcedimentByActivoCriteria;

public class ProcedimentUtils {
    
    public static void parseActiu(List<CriteriaObject> criteris, ProcedimentCriteria pc, String alias) {
        if (pc.getActiu() != null) {
            ProcedimentByActivoCriteria c = new ProcedimentByActivoCriteria(alias);
            c.parseCriteria(BasicUtils.booleanToString(pc.getActiu()));
            criteris.add(c);
            pc.setActiu(null); // Para que no lo parsee BasicUtils.
        }
    }
    
}
