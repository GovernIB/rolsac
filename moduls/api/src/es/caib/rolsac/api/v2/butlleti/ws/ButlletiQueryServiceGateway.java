package es.caib.rolsac.api.v2.butlleti.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceGateway {

	ButlletiWSSoapBindingStub stub;
	ButlletiQueryServiceEJBRemoteServiceLocator locator;

	public ButlletiQueryServiceGateway() {
		try {
			locator = new ButlletiQueryServiceEJBRemoteServiceLocator();

			stub = new ButlletiWSSoapBindingStub(new URL(
					locator.getButlletiWSAddress()), null);

		} catch (RemoteException re) {
		} catch (MalformedURLException mue) {
		}
	}
	
	public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws RemoteException {
		return stub.getNumNormatives(id, tipus.ordinal() );
	}

	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws RemoteException {
		
		Object[] tmpLlista = null;
		List<NormativaDTO> llistaNormatives = null;
		
		tmpLlista = stub.llistarNormatives(id, normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>( Arrays.asList(tmpLlista).size() );
			
		for ( Object o : tmpLlista ) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o, NormativaDTO.class);
			llistaNormatives.add(ndto);
		}
		
		return llistaNormatives;
	}		
}
