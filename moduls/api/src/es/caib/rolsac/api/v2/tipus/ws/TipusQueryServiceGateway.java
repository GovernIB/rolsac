package es.caib.rolsac.api.v2.tipus.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class TipusQueryServiceGateway {

	TipusWSSoapBindingStub stub;
	TipusQueryServiceEJBRemoteServiceLocator locator;
	
	public TipusQueryServiceGateway() {

		try {
			stub = new TipusWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_TIPUS)),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
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
