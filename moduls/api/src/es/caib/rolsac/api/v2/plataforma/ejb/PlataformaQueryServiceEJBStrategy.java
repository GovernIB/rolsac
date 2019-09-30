package es.caib.rolsac.api.v2.plataforma.ejb;

import es.caib.rolsac.api.v2.plataforma.PlataformaQueryServiceStrategy;

public class PlataformaQueryServiceEJBStrategy implements PlataformaQueryServiceStrategy {

	private PlataformaQueryServiceDelegate plataformaQueryServiceDelegate;

	public void setPlataformaQueryServiceDelegate(final PlataformaQueryServiceDelegate plataformaQueryServiceDelegate) {
		this.plataformaQueryServiceDelegate = plataformaQueryServiceDelegate;
	}

	@Override
	public void setUrl(final String url) {
		// No es necesario en EJB
	}

}
