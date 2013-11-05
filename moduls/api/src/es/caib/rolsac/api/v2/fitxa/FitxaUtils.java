package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.co.FitxaByActivoCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;

public class FitxaUtils {
    
    
	public static void parseActiu(List<CriteriaObject> criteris, FitxaCriteria fitxaCriteria, String alias, QueryBuilder qb) {

		if (fitxaCriteria.getActiu() != null) {

			FitxaByActivoCriteria c = new FitxaByActivoCriteria(alias);
			c.parseCriteria(BasicUtils.booleanToString(fitxaCriteria.getActiu()));
			c.extendCriteria(qb);
			criteris.add(c);
			fitxaCriteria.setActiu(null);

		}

	}
    
}
