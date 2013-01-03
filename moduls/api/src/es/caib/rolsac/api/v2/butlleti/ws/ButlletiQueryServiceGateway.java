package es.caib.rolsac.api.v2.butlleti.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceGateway {

	ButlletiWSSoapBindingStub stub;

	public ButlletiQueryServiceGateway() {
		try {
			stub = new ButlletiWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_BUTLLETI)),
					null);
		} catch (RemoteException re) {
			re.printStackTrace();			
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
	}

	public int getNumNormatives(long id, TIPUS_NORMATIVA tipus)
			throws RemoteException {
		return stub.getNumNormatives(id, tipus.ordinal());
	}

	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<NormativaDTO> llistaNormatives = null;

		tmpLlista = stub.llistarNormatives(id, normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o,
					NormativaDTO.class);
			llistaNormatives.add(ndto);
		}

		return llistaNormatives;
	}
}
