package es.caib.rolsac.api.v2.taxa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceGateway {
	
	TaxaWSSoapBindingStub stub;

	public TaxaQueryServiceGateway() {
		try {
			stub = new TaxaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_TAXA)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
    public TramitDTO obtenirTramit(long id) throws RemoteException {
    	return stub.obtenirTramit(id);
    }

	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new TaxaWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_TAXA),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}