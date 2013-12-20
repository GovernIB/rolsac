package es.caib.rolsac.api.v2.seccio.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceGateway {
	
	SeccioWSSoapBindingStub stub;

	public SeccioQueryServiceGateway() {
		try {
			stub = new SeccioWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_SECCIO)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public int getNumFilles(long id) throws RemoteException {
		return stub.getNumFilles(id);
	}

	public int getNumFitxes(long id) throws RemoteException {
		return stub.getNumFitxes(id);
	}

	public int getNumPares(long id) throws RemoteException {
		return stub.getNumPares(id);
	}

	public int getNumUnitatsAdministratives(long id) throws RemoteException {
		return stub.getNumUnitatsAdministratives(id);
	}

	public SeccioDTO obtenirPare(Long padre) throws RemoteException {
		return stub.obtenirPare(padre);
	}
	
	public List<SeccioDTO> llistarPares(long id) throws RemoteException {
		return Arrays.asList(stub.llistarPares(id));
	}

	public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria)
			throws RemoteException {
		return Arrays.asList(stub.llistarFilles(id, seccioCriteria));		
	}

	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<FitxaDTO> llistaFitxes = null;
		
		tmpLlista = stub.llistarFitxes(id, fitxaCriteria);
		llistaFitxes = new ArrayList<FitxaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);							
			llistaFitxes.add( fdto );
		}
			
		return llistaFitxes;		
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException, APIException {
		
		Object[] tmpLlista = null;
		List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
		
		tmpLlista = stub.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			UnitatAdministrativaDTO uadto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);							
			llistaUnitatsAdministratives.add( uadto );
		}
			
		return llistaUnitatsAdministratives;				
	}	
}