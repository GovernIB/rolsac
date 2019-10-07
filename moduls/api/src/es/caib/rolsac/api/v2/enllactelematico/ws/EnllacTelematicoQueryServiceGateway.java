package es.caib.rolsac.api.v2.enllactelematico.ws;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;

public class EnllacTelematicoQueryServiceGateway {

	EnllacTelematicoWSSoapBindingStub stub;

	public EnllacTelematicoQueryServiceGateway() {

		try {
			stub = new EnllacTelematicoWSSoapBindingStub(
					new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_ENLLAC)), null);

		} catch (final AxisFault e) {
			e.printStackTrace();
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		} catch (final APIException e) {
			e.printStackTrace();
		}
	}

	public void setUrl(final String url) {
		try {
			if (url != null && !url.isEmpty()) {
				stub = new EnllacTelematicoWSSoapBindingStub(new URL(url + ConfiguracioServeis.NOM_SERVEI_ENLLAC),
						null);
			}
		} catch (final AxisFault e) {
			e.printStackTrace();
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
