package es.caib.rolsac.api.v2.enllactelematico.ws;

import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoQueryServiceStrategy;

public class EnllacTelematicoQueryServiceWSStrategy implements EnllacTelematicoQueryServiceStrategy {

	EnllacTelematicoQueryServiceGateway gateway;

	public void setGateway(final EnllacTelematicoQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public void setUrl(final String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}
}
