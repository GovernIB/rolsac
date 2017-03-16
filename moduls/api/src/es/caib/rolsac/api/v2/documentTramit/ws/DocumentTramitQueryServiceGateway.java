package es.caib.rolsac.api.v2.documentTramit.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceGateway {

	DocumentTramitWSSoapBindingStub stub;

	public DocumentTramitQueryServiceGateway() {

		try {

			stub = new DocumentTramitWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_DOCUMENT_TRAMIT)),
					null);
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		} 
	}
	
	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new DocumentTramitWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_DOCUMENT_TRAMIT),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public TramitDTO obtenirTramit(long id) throws RemoteException {
		return stub.obtenirTramit(id);
	}

	public ArxiuDTO obtenirArxiu(long idArxiu) throws RemoteException {
		return stub.obtenirArxiu(idArxiu);
	}

	public CatalegDocumentsDTO obtenirCatalegDocuments(long id)
			throws RemoteException {
		return stub.obtenirCatalegDocuments(id);
	}

	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(long id)
			throws RemoteException {
		return stub.obtenirExcepcioDocumentacio(id);
	}

	
	
}