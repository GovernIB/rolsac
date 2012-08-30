package es.caib.rolsac.api.v2.butlleti.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.ButlletiWS.ButlletiWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceGateway {

	ButlletiWSSoapBindingStub stub;
	
	public ButlletiQueryServiceGateway() {
		try {
			stub = new ButlletiWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/ButlletiWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
