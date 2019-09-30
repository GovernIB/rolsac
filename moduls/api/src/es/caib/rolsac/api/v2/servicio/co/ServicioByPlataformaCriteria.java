package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByPlataformaCriteria extends ByLongCriteria {

	public ServicioByPlataformaCriteria(final String entityAlias) {
		super(entityAlias + ".plataforma.id");
	}

}
