package es.caib.rolsac.api.v2.fitxaUA.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceGateway {

	FitxaUAWSSoapBindingStub stub;
	
	public FitxaUAQueryServiceGateway() {

		try {
			stub = new FitxaUAWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_FITXA_UA)),
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
	
	public FitxaDTO obtenirFitxa(long idFitxa) throws RemoteException {
		return stub.obtenirFitxa(idFitxa);
	}

	public SeccioDTO obtenirSeccio(long idSeccio)  throws RemoteException {
		return stub.obtenirSeccio(idSeccio);
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			long idUnitatAdministrativa) throws RemoteException {
		return stub.obtenirUnitatAdministrativa(idUnitatAdministrativa);
	}	
}
