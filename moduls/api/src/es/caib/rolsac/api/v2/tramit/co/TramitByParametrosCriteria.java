package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByParametrosCriteria extends ByStringCriteria {

	public TramitByParametrosCriteria(final String entityAlias) {
		super(entityAlias + ".parametros");
	}

}
