package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByComunCriteria extends ByStringCriteria {

	public ProcedimentByComunCriteria(final String i18nAlias) {
		super(i18nAlias + ".comun");
	}

}
