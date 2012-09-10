package es.caib.rolsac.api.v2.document.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceGateway {
	
	DocumentWSSoapBindingStub stub;
	DocumentQueryServiceEJBRemoteServiceLocator locator;
	
	public DocumentQueryServiceGateway() {

		try {
			
			locator = new DocumentQueryServiceEJBRemoteServiceLocator();

			stub = new DocumentWSSoapBindingStub(new URL(
					locator.getDocumentWSAddress()), null);
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public FitxaDTO obtenirFitxa(long id) throws RemoteException {
    	return stub.obtenirFitxa(id);
    }	
    
	public ProcedimentDTO obtenirProcediment(long id) throws RemoteException {
    	return stub.obtenirProcediment(id);
    }    

	public ArxiuDTO obtenirArxiu(long id) throws RemoteException {
    	return stub.obtenirArxiu(id);    			
    }
}
