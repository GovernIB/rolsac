package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByPlantillaCriteria extends ByLongCriteria {

	public TramitByPlantillaCriteria(final String entityAlias) {
		super(entityAlias + ".plantilla.id");
	}

}
