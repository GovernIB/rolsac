package es.caib.rolsac.api.v2.excepcioDocumentacio.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;

public class ExcepcioDocumentacioQueryServiceGateway {

	ExcepcioDocumentacioWSSoapBindingStub stub;
	
	public ExcepcioDocumentacioQueryServiceGateway() {
		try {
			stub = new ExcepcioDocumentacioWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
								.getUrlServei(ConfiguracioServeis.NOM_SERVEI_EXCEPCIO_DOCUMENTACIO)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
	}	
	
	public int getNumCatalegsDocuments(long id) throws RemoteException {
		return stub.getNumCatalegsDocuments(id);
	}

	public int getNumDocumentsTramit(long id) throws RemoteException {
		return stub.getNumDocumentsTramit(id);
	}
	
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id,
			CatalegDocumentsCriteria catalegDocumentCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<CatalegDocumentsDTO> llistaCatalegDocuments = null;
		
		tmpLlista = stub.llistarCatalegsDocuments(id, catalegDocumentCriteria);
		llistaCatalegDocuments = new ArrayList<CatalegDocumentsDTO>(Arrays.asList(tmpLlista).size());
		
		for (Object o : tmpLlista) {
			CatalegDocumentsDTO catalegDocumentsDTO = (CatalegDocumentsDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);
			llistaCatalegDocuments.add(catalegDocumentsDTO);
		}
		
		return llistaCatalegDocuments;
		
	}

	public List<DocumentTramitDTO> llistarDocumentsTramit(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<DocumentTramitDTO> llistaDocumentsTramit = null;

		tmpLlista = stub.llistarDocumentsTramits(id, documentTramitCriteria);
		llistaDocumentsTramit = new ArrayList<DocumentTramitDTO>(Arrays.asList(tmpLlista).size());

		for (Object o : tmpLlista) {
			DocumentTramitDTO documentTramitDTO = (DocumentTramitDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);
			llistaDocumentsTramit.add(documentTramitDTO);
		}

		return llistaDocumentsTramit;
		
	}	
}
