package es.caib.rolsac.api.v2.tipus.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.TipusWS.TipusWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class TipusQueryServiceGateway {

	TipusWSSoapBindingStub stub;

	public TipusQueryServiceGateway() {

		try {
			stub = new TipusWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/TipusWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<NormativaDTO> llistarNormatives(Long id,
			NormativaCriteria normativaCriteria) throws RemoteException {
		
    	Object[] tmpLlista = null;
    	List<NormativaDTO> llistaNormatives = null;
    	
		tmpLlista = stub.llistarNormatives(id, normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o, NormativaDTO.class);
			llistaNormatives.add(ndto);
		}
    	
		return llistaNormatives;
	}

	public int getNumNormatives(Long id, TIPUS_NORMATIVA totes)
			throws RemoteException {
			return stub.getNumNormatives(id, totes.ordinal());		
	}
}
