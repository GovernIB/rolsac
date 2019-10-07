package es.caib.rolsac.api.v2.enllactelematico.ejb;

import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoQueryServiceStrategy;

public class EnllacTelematicoQueryServiceEJBStrategy implements EnllacTelematicoQueryServiceStrategy {

	EnllacTelematicoQueryServiceDelegate enllacTelematicoQueryServiceDelegate;

	public void setEnllacTelematicoQueryServiceDelegate(
			final EnllacTelematicoQueryServiceDelegate enllacQueryServiceDelegate) {
		this.enllacTelematicoQueryServiceDelegate = enllacQueryServiceDelegate;
	}

	@Override
	public void setUrl(final String url) {
		// No es necesario en EJB.
	}

}
