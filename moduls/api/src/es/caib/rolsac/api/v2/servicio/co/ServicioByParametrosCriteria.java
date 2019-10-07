package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByParametrosCriteria extends ByLongCriteria {

	public ServicioByParametrosCriteria(final String entityAlias) {
		super(entityAlias + ".parametros");
	}

}
