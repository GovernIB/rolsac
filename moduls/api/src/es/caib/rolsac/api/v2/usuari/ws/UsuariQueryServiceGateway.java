package es.caib.rolsac.api.v2.usuari.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.UsuariWS.UsuariWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;


public class UsuariQueryServiceGateway {

	UsuariWSSoapBindingStub stub;

	public UsuariQueryServiceGateway() {

		try {
			stub = new UsuariWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/UsuariWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws RemoteException {
		
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
