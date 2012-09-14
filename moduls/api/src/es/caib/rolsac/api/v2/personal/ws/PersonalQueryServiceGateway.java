package es.caib.rolsac.api.v2.personal.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceGateway {

	PersonalWSSoapBindingStub stub;
	
	public PersonalQueryServiceGateway() {

		try {
			stub = new PersonalWSSoapBindingStub(
					new URL(
							ConfiguracioServeis
									.getUrlServei(ConfiguracioServeis.NOM_SERVEI_PERSONAL)),
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
	
	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA)
			throws RemoteException {
		return stub.obtenirUnitatAdministrativa(idUA);
	}	
	
}
