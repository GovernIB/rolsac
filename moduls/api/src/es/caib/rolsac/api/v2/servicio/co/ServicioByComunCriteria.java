package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByComunCriteria extends ByStringCriteria {

	public ServicioByComunCriteria(final String i18nAlias) {
		super(i18nAlias + ".comun");
	}

}
