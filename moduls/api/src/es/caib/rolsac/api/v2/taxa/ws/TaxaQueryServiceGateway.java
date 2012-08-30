package es.caib.rolsac.api.v2.taxa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import localhost.sacws_api.services.TaxaWS.TaxaWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceGateway {
	
	TaxaWSSoapBindingStub stub;

	public TaxaQueryServiceGateway() {

		try {
			stub = new TaxaWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/TaxaWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public TramitDTO obtenirTramit(long id) throws RemoteException {
    	return stub.obtenirTramit(id);
    }

}
