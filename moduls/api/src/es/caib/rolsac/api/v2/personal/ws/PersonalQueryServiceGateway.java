package es.caib.rolsac.api.v2.personal.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import localhost.sacws_api.services.PersonalWS.PersonalWSSoapBindingStub;

import org.apache.axis.AxisFault;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceGateway {

	PersonalWSSoapBindingStub stub;
	
	public PersonalQueryServiceGateway() {

		try {
			stub = new PersonalWSSoapBindingStub(new URL(
					"https://localhost:8443/sacws-api/services/PersonalWS"),
					null);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA)
			throws RemoteException {
		return stub.obtenirUnitatAdministrativa(idUA);
	}	
	
}
