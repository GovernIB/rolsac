package es.caib.rolsac.api.v2.agrupacioFetVital.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class AgrupacioFetVitalQueryServiceGateway {

	AgrupacioFetVitalWSSoapBindingStub stub;

	public AgrupacioFetVitalQueryServiceGateway() {
		try {
			stub = new AgrupacioFetVitalWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_AGRUPACIO_FET_VITAL)),
					null);
		} catch (AxisFault af) {
			af.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new AgrupacioFetVitalWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_AGRUPACIO_FET_VITAL),
						null
						);
			}
		} catch (AxisFault af) {
			af.printStackTrace();
		} catch (MalformedURLException mue) {
			mue.printStackTrace();			
		}
	}

	public ArxiuDTO getFotografia(long idFoto) throws RemoteException {
		ArxiuDTO adto = null;
		adto = stub.getFotografia(idFoto);

		return adto;
	}

	public ArxiuDTO getIcona(long idIcona) throws RemoteException {
		ArxiuDTO adto = null;
		adto = stub.getIcona(idIcona);

		return adto;
	}

	public ArxiuDTO getIconaGran(long idIconaGran) throws RemoteException {
		ArxiuDTO adto = null;
		adto = stub.getIconaGran(idIconaGran);

		return adto;
	}

	public int getNumFetsVitals(long id) throws RemoteException {
		int numFetsVitals = 0;
		numFetsVitals = stub.getNumFetsVitals(id);

		return numFetsVitals;
	}

	public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic)
			throws RemoteException {
		return stub.obtenirPublicObjectiu(idPublic);
	}

	public List<FetVitalDTO> llistarFetsVitals(long id,
			FetVitalCriteria fetVitalCriteria) throws RemoteException, APIException {

		Object[] tmpLlista = null;
		List<FetVitalDTO> llistaFetsVitals = null;

		tmpLlista = stub.llistarFetsVitals(id, fetVitalCriteria);
		llistaFetsVitals = new ArrayList<FetVitalDTO>(Arrays.asList(tmpLlista)
				.size());

		for (Object o : tmpLlista) {
			FetVitalDTO fvdto = (FetVitalDTO) DTOUtil.object2DTO(o,
					FetVitalDTO.class);
			llistaFetsVitals.add(fvdto);
		}

		return llistaFetsVitals;
	}

	

}