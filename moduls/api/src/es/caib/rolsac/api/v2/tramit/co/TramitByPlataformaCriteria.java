package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByPlataformaCriteria extends ByLongCriteria {

	public TramitByPlataformaCriteria(final String entityAlias) {
		super(entityAlias + ".plataforma.id");
	}

}
