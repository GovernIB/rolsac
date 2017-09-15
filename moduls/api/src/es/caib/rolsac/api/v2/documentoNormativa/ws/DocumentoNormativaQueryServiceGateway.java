package es.caib.rolsac.api.v2.documentoNormativa.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;

public class DocumentoNormativaQueryServiceGateway {

	DocumentoNormativaWSSoapBindingStub stub;

	public DocumentoNormativaQueryServiceGateway() {

		try {

			stub = new DocumentoNormativaWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_DOCUMENTO_NORMATIVA)),
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
				stub = new DocumentoNormativaWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_DOCUMENTO_NORMATIVA),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public NormativaDTO obtenirNormativa(long id) throws RemoteException {
		return stub.obtenirNormativa(id);
	}

	public ArxiuDTO obtenirArxiu(long idArxiu) throws RemoteException {
		return stub.obtenirArxiu(idArxiu);
	}

	
	
}