package es.caib.rolsac.api.v2.catalegDocuments.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;

public class CatalegDocumentsQueryServiceGateway {

	CatalegDocumentsWSSoapBindingStub stub;
	
	public CatalegDocumentsQueryServiceGateway() {
		try {
			stub = new CatalegDocumentsWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
								.getUrlServei(ConfiguracioServeis.NOM_SERVEI_CATALEG_DOCUMENTS)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
	}	
	
	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new CatalegDocumentsWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_CATALEG_DOCUMENTS),
						null
						);
			}
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} 
	}
	
	public int getNumDocumentsTramit(long id) throws RemoteException {
		return stub.getNumDocumentsTramit(id);
	}
	
	public List<DocumentTramitDTO> llistarDocumentsTramit(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<DocumentTramitDTO> llistaDocumentsTramit = null;

		tmpLlista = stub.llistarDocumentsTramits(id, documentTramitCriteria);
		llistaDocumentsTramit = new ArrayList<DocumentTramitDTO>(Arrays.asList(tmpLlista).size());

		for (Object o : tmpLlista) {
			DocumentTramitDTO ndto = (DocumentTramitDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);
			llistaDocumentsTramit.add(ndto);
		}

		return llistaDocumentsTramit;
		
	}

		
}