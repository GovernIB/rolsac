package es.caib.rolsac.api.v2.documentTramit.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceGateway {
	
	DocumentTramitWSSoapBindingStub stub;
	DocumentTramitQueryServiceEJBRemoteServiceLocator locator;
	
	public DocumentTramitQueryServiceGateway() {

		try {
			
			locator = new DocumentTramitQueryServiceEJBRemoteServiceLocator();

			stub = new DocumentTramitWSSoapBindingStub(new URL(
					locator.getDocumentTramitWSAddress()), null);
			
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

    public ArxiuDTO obtenirArxiu(long idArxiu) throws RemoteException {
    	return stub.obtenirArxiu(idArxiu);
    }
		
}
