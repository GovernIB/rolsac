package es.caib.rolsac.api.v2.taxa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceGateway {
	
	TaxaWSSoapBindingStub stub;
	TaxaQueryServiceEJBRemoteServiceLocator locator;

	public TaxaQueryServiceGateway() {
		try {
			locator = new TaxaQueryServiceEJBRemoteServiceLocator();

			stub = new TaxaWSSoapBindingStub(new URL(
					locator.getTaxaWSAddress()), null);

		} catch (RemoteException re) {
		} catch (MalformedURLException mue) {
		}
	}
	
    public TramitDTO obtenirTramit(long id) throws RemoteException {
    	return stub.obtenirTramit(id);
    }

}
