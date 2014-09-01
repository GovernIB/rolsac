package es.caib.rolsac.api.v2.usuari.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class UsuariQueryServiceGateway {

	UsuariWSSoapBindingStub stub;
	
	public UsuariQueryServiceGateway() {

		try {
			
			stub = new UsuariWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_USUARI)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException, APIException {
		
    	Object[] tmpLlista = null;
    	List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
    	
		tmpLlista = stub.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			UnitatAdministrativaDTO afvdto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);
			llistaUnitatsAdministratives.add(afvdto);
		}
    	
		return llistaUnitatsAdministratives;
	}

	public int getNumUnitatsAdministratives(long id) throws RemoteException {
		return stub.getNumUnitatsAdministratives(id);
	}
}
