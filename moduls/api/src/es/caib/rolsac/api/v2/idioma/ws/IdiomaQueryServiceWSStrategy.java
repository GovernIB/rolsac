package es.caib.rolsac.api.v2.idioma.ws;

import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceStrategy;

public class IdiomaQueryServiceWSStrategy implements IdiomaQueryServiceStrategy {

	IdiomaQueryServiceGateway gateway;

	public void setGateway(IdiomaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
}
