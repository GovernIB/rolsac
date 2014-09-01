package es.caib.rolsac.api.v2.idioma.ws;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;

public class IdiomaQueryServiceGateway {
	
	IdiomaWSSoapBindingStub stub;
		
	public IdiomaQueryServiceGateway() {
		
		try {
			stub = new IdiomaWSSoapBindingStub(
				new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_IDIOMA)),
				null
			);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		
	}
	
}
